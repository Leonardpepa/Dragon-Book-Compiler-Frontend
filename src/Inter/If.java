package Inter;

import Symbols.Type;

public class If extends Stmt{
    public Expr expr;
    public  Stmt stmt;

    public If(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
        if (expr.type != Type.Bool){
            expr.error("boolean required in if^");
        }
    }

    @Override
    public void gen(int a, int b) {
        int label = newLabel();
        expr.jumping(0, a);
        emitLabel(label);
        stmt.gen(label, a);
    }
}
