package main.Inter;

import main.Lexer.Token;
import main.Symbols.Type;

public class Unary extends  Op{
    public Expr expr;


    public Unary(Token op, Expr expr) {
        super(op, null);
        this.expr = expr;
        type = Type.max(Type.Int, expr.type);
        if (type == null){
            error("Type Error");
        }
    }

    @Override
    public Expr gen(){
        return new Unary(op, expr.reduce());
    }

    @Override
    public String toString() {
        return op.toString() + " " + expr.toString();
    }
}
