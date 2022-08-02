package main.Inter;

import main.Lexer.Token;
import main.Symbols.Type;

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

    public void jumping(int t, int f){
        emitJumps(this.toString(), t, f);
    }

    public void emitJumps(String test, int t, int f){
        if ( t != 0 && f != 0){
            emit("if " + test + " goto L" + t);
            emit("goto L" + f);
        }else if ( t != 0){
            emit("if " + test + " goto L" + t);
        }else if ( f != 0){
            emit("iffalse " + test + " goto L" + f);
        }
    }


    @Override
    public String toString() {
        return op.toString();
    }
}
