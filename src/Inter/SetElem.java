package Inter;

import Symbols.Array;
import Symbols.Type;

public class SetElem extends Stmt{

    public Id array;
    public Expr index;
    public Expr expr;

    public SetElem(Access x, Expr y){
        array = x.array;
        index = x.index;
        expr = y;
        if(check(x.type, expr.type) == null){
            error("Type Error");
        }
    }

    public Type check(Type t1, Type t2){
        if (t1 instanceof Array && t2 instanceof Array){
            return null;
        }else if (Type.numeric(t1) && Type.numeric(t2)){
            return t2;
        }
        return null;
    }

    @Override
    public void gen(int b, int a) {
        String s1 = index.reduce().toString();
        String s2 = expr.reduce().toString();

        emit(array.toString() + " [ " + s1 + " ] " + " = " + s2);
    }
}
