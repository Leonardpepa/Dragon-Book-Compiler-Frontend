package Inter;

import Lexer.Token;
import Symbols.Type;

public class Id extends Expr{
    public int offset;

    public Id(Token op, Type type, int offset) {
        super(op, type);
        this.offset = offset;
    }


}
