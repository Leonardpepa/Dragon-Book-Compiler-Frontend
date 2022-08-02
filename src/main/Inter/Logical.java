package main.Inter;

import main.Lexer.Token;
import main.Symbols.Type;

public class Logical extends Expr{

    public Expr expr1, expr2;

    public Logical(Token op, Expr expr1, Expr expr2) {
        super(op, null);
        this.expr1 = expr1;
        this.expr2 = expr2;

        type = check(expr1.type, expr2.type);
        if (type == null){
            error("Type Error");
        }
    }

    public Type check(Type t1,  Type t2){
        if (t1 == Type.Bool && t2 == Type.Bool){
            return Type.Bool;
        }
        return null;
    }

    @Override
    public Expr gen(){
        int f = newLabel();
        int a = newLabel();
        Temp temp = new Temp(type);
        this.jumping(0, f);
        emit(temp.toString() + " = true");
        emit("goto L" + a);
        emitLabel(f);
        emit(temp.toString() + " = false");
        emitLabel(a);
        return temp;
    }

    @Override
    public String toString() {
        return expr1.toString() + " " + op.toString() + " " + expr2.toString();
    }
}
