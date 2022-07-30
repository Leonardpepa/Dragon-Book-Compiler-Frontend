package Inter;

import Lexer.Token;

public class Id extends Expr{
    public int offset;

    public Id(Token op, Token type, int offset) {
        super(op, type);
        this.offset = offset;
    }


}
