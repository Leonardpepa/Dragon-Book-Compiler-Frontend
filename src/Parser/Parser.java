package Parser;

import Lexer.Lexer;
import Lexer.Token;
import Symbols.Env;

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






}
