package main.Inter;

import main.Lexer.Token;
import main.Symbols.Type;

public class Id extends Expr{
    public int offset;

    public Id(Token op, Type type, int offset) {
        super(op, type);
        this.offset = offset;
    }


}
