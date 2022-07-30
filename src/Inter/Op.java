package Inter;

import Lexer.Token;
import Symbols.Type;

public class Op extends Expr{
    public Op(Token op, Type type) {
        super(op, type);
    }

    @Override
    public Expr reduce(){
        Expr x = gen();
        Temp t = new Temp(type);
        emit(t.toString() + " = " + x.toString());
        return t;
    }

}
