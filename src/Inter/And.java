package Inter;

import Lexer.Token;

public class And extends Logical{
    public And(Token op, Expr expr1, Expr expr2) {
        super(op, expr1, expr2);
    }

    @Override
    public void jumping(int t, int f){
        int label = f != 0 ? f : newLabel();
        expr1.jumping(0, label);
        expr2.jumping(t, f);
        if (f == 0){
            emitLabel(label);
        }
    }
}
