package Parser;

import Inter.*;
import Lexer.*;
import Symbols.Array;
import Symbols.Env;
import Symbols.Type;


import java.io.IOException;

public class Parser {

    private Lexer lex;
    private Token look; // look ahead token

    public Env top = null; // current of top symbol table
    public int used = 0;
    public Parser(Lexer lex) throws IOException {
        this.lex = lex;
        move();
    }

    public void move() throws IOException {
        look = lex.scan();
    }

    public void error(String s){
        throw new Error("new line " + lex.line + " " + s);
    }

    public void match(int tag) throws IOException {
        if (look.tag == tag){
            move();
        }else{
            error("Syntax Error");
        }
    }


    // programm -> block
    public void program() throws IOException{
        Stmt stmt = block();
        // code gen
        int begin = stmt.newLabel();
        int after = stmt.newLabel();

        stmt.emitLabel(begin);
        stmt.gen(begin, after);
        stmt.emitLabel(after);
    }

    // block -> { decls stmts }
    private Stmt block() throws IOException {
        match('{');
        Env savedEnv = top;
        top = new Env(top);
        decls();
        Stmt s = stmts();
        match('}');
        top = savedEnv;
        return  s;
    }
    private void decls() throws IOException {
        while (look.tag == Tag.BASIC){
            Type p = type();
            Token tok = look;
            match(Tag.ID);
            match(';');
            Id id = new Id((Word) tok, p, used);
            top.put(tok, id);
            used += p.width;
        }

    }

    private Type type() throws IOException {
        Type p = (Type) look;
        match(Tag.BASIC);
        if (look.tag != '['){
            return p;
        }else {
            return dims(p);
        }

    }

    private Type dims(Type p) throws IOException {
        match('[');
        Token tok = look;
        match(Tag.NUM);
        match(']');
        if (look.tag == '['){
            p = dims(p);
        }
        return new Array(((Num)tok).value, p);
    }

    private Stmt stmts() throws IOException {
        if (look.tag == '}'){
            return Stmt.Null;
        }
        return new Seq(stmt(), stmts());
    }

    private Stmt stmt() throws IOException {
        Expr x;
        Stmt s, s1, s2;
        Stmt savedStmt;

        switch (look.tag){
            case ';':
                move();
                return Stmt.Null;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                x = bool();
                match(')');
                s1 = stmt();
                if (look.tag != Tag.ELSE){
                    return new If(x, s1);
                }
                match(Tag.ELSE);
                s2 = stmt();
                return new Else(x, s1, s2);
            case Tag.WHILE:
                While whilenode = new While();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = whilenode;
                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');
                s1 = stmt();
                whilenode.init(x, s1);
                Stmt.Enclosing = savedStmt;
                return whilenode;
            case Tag.DO:
                Do donode = new Do();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = donode;
                match(Tag.DO);
                s1 = stmt();
                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');
                match(';');
                donode.init(x, s1);
                Stmt.Enclosing = savedStmt;
                return donode;
                case Tag.BREAK:
                    match(Tag.BREAK);
                    match(';');
                    return new Break();
                case '{':
                    return block();
                default:
                    return assign();
        }
    }

    private Stmt assign() throws IOException {
        Stmt stmt;
        Token tok = look;
        match(Tag.ID);
        Id id = top.get(tok);
        if (id == null){
            error("Variable: " + id.toString() + " Undeclared");
        }
        if (look.tag == '='){
            move();
            stmt = new Set(id, bool());
        }else{
            Access x = offset(id);
            match('=');
            stmt = new SetElem(x, bool());
        }
        match(';');
        return stmt;
    }

    private Expr bool() throws IOException{
        Expr x = join();
        while (look.tag == Tag.OR){
                Token tok = look;
                move();
                x = new Or(tok, x, join());
        }
        return x;
    }

    private Expr join() throws IOException {
        Expr x = equality();
        while (look.tag == Tag.AND){
            Token tok = look;
            move();
            x = new And(tok, x, equality());
        }
        return x;
    }

    private Expr equality() throws IOException {
        Expr x = rel();
        while (look.tag == Tag.EQ || look.tag == Tag.NE){
            Token tok = look;
            move();
            x = new Rel(tok, x, rel());
        }
        return x;
    }

    private Expr rel() throws IOException {
        Expr x = expr();
        switch (look.tag){
            case '>':
            case '<':
            case Tag.LE:
            case Tag.GE:
                Token tok = look;
                move();
                x = new Rel(tok, x, expr());
            default:
                return x;
        }
    }

    private Expr expr() throws IOException {
        Expr x = term();
        while (look.tag == '+' || look.tag == '-'){
            Token tok = look;
            move();
            x = new Arith(tok, x, term());
        }
        return x;
    }

    private Expr term() throws IOException {
        Expr x = unary();
        while (look.tag == '*' || look.tag == '/'){
            Token tok = look;
            move();
            x = new Arith(tok, x, unary());
        }
        return x;
    }

    private Expr unary() throws IOException {
        if (look.tag == '-'){
            move();
            return new Unary(Word.minus, unary());
        }else if (look.tag == '!'){
            Token tok = look;
            move();
            return new Not(tok, unary());
        }
        return factor();
    }

    private Expr factor() throws IOException {
        Expr x = null;

        switch (look.tag){
            case '(':
                move();
                x = bool();
                match(')');
                return x;
            case Tag.NUM:
                x = new Constant(look, Type.Int);
                move();
                return x;
            case Tag.REAL:
                x = new Constant(look, Type.Float);
                move();
                return x;
            case Tag.FALSE:
                x = Constant.False;
                move();
                return x;
            case Tag.TRUE:
                x = Constant.True;
                move();
                return x;
            case Tag.ID:
                String s = look.toString();
                Id id = top.get(look);
                if (id == null){
                    error(s + " Undeclared");
                }
                move();
                if(look.tag != '['){
                    return id;
                }else {
                    return offset(id);
                }
            default:
                error("Syntax Error");
                return x;

        }
    }

    private Access offset(Id id) throws IOException {
        Expr i;
        Expr w;
        Expr t1;
        Expr t2;
        Expr loc;
        Type type = id.type;
        match('[');
        i = bool();
        match(']');
        type = ((Array)type).of;
        w = new Constant(type.width);
        t1 = new Arith(new Token('*'), i, w);
        loc = t1;

        while (look.tag == '['){
            match('[');
            i = bool();
            match(']');
            w = new Constant(type.width);
            t1 = new Arith(new Token('*'), i ,w);
            t2 = new Arith(new Token('+'), i ,w);
            loc = t2;
        }
        return new Access(id, loc, type);
    }

}
