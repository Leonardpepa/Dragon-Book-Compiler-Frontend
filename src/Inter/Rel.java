package Inter;

import Lexer.Token;
import Symbols.Array;
import Symbols.Type;

public class Rel extends Logical{
    public Rel(Token op, Expr expr1, Expr expr2) {
        super(op, expr1, expr2);
    }

    @Override
    public Type check(Type t1, Type t2) {
        if (t1 instanceof Array || t2 instanceof Array) {
            return null;
        }else if(t1 == t2){
            return Type.Bool;
        }
        return null;
    }

    @Override
    public void jumping(int t, int f) {
        Expr a = expr1.reduce();
        Expr b = expr2.reduce();

        String test = a.toString() + " " + op.toString() + " " + b.toString();

        emitJumps(test, t, f);
    }
}
