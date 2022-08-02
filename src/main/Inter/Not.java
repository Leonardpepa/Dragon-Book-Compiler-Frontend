package main.Inter;

import main.Lexer.Token;

public class Not extends Logical{
    public Not(Token op, Expr expr2) {
        super(op, expr2, expr2);
    }

    @Override
    public void jumping(int t, int f){
        expr2.jumping(f, t);
    }

    @Override
    public String toString() {
        return op.toString() + " " + expr2.toString();
    }
}
