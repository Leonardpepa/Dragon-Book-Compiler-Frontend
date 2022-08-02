package main.Inter;

import main.Lexer.Token;
import main.Symbols.Type;

public class Arith extends Op{
    public Expr expr1, expr2;

    public Arith(Token op, Expr expr1, Expr expr2) {
        super(op, null);
        this.expr1 = expr1;
        this.expr2 = expr2;
        type = Type.max(expr1.type, expr2.type);
        if (type == null){
            error("Type Error");
        }
    }

    @Override
    public Expr gen(){
        return new Arith(op, expr1.reduce(), expr2.reduce());
    }

    @Override
    public String toString() {
        return expr1.toString() + " " + op.toString() + " " + expr2.toString();
    }
}
