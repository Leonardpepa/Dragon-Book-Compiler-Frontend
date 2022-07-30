package Inter;

import Lexer.Token;

public class Expr {

    public Token op;
    public Token type;

    public Expr(Token op, Token type) {
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
