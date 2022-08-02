package main.Inter;

import main.Symbols.Type;

public class While extends Stmt{

    public Expr expr;
    public Stmt stmt;

    public While(){
        expr = null;
        stmt = null;
    }

    public void init(Expr expr, Stmt stmt){
        this.expr = expr;
        this.stmt = stmt;

        if(expr.type != Type.Bool){
            expr.error("Boolean is required in while^");
        }
    }

    @Override
    public void gen(int b, int a) {
        after = a;
        expr.jumping(0, a);
        int label  = newLabel();
        emitLabel(label);
        stmt.gen(label, b);
        emit("goto L" + b);
    }
}
