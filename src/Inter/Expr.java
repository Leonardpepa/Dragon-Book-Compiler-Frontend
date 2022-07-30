package Inter;

import Lexer.Token;
import Symbols.Type;

public class Expr extends Node{

    public Token op;
    public Type type;

    public Expr(Token op, Type type) {
        this.op = op;
        this.type = type;
    }
    public Expr gen(){
        return this;
    }

    public Expr reduce(){
        return this;
    }

    // TODO write code for conditions and jumps



    @Override
    public String toString() {
        return op.toString();
    }
}
