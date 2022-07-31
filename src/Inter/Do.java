package Inter;

import Symbols.Type;

public class Do extends Stmt{

    public Expr expr;
    public Stmt stmt;

    public Do(){
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
        int label = newLabel();
        stmt.gen(b, label);
        emitLabel(label);
        expr.jumping(b, 0);
    }
}
