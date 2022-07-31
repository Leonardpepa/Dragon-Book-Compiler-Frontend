package Inter;

import Symbols.Type;

public class Set extends Stmt{

    public Id id;
    public Expr expr;

    public Set(Id id, Expr expr){
        this.id = id;
        this.expr = expr;
        if (check(id.type, expr.type) == null){
            error("Type Errpr");
        }

    }
    public Type check(Type t1, Type t2){
        if (Type.numeric(t1) && Type.numeric(t2)) {
            return t2;
        }else if(t1 == Type.Bool && t2 == Type.Bool){
            return t2;
        }
        return null;
    }

    @Override
    public void gen(int b, int a) {
        emit(id.toString() + " = " + expr.gen().toString());
    }
}
