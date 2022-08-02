package main.Inter;

import main.Lexer.Num;
import main.Lexer.Token;
import main.Lexer.Word;
import main.Symbols.Type;

public class Constant extends Expr{
    public final static Constant True = new Constant(Word.True, Type.Bool),
                                 False = new Constant(Word.False, Type.Bool);
    public Constant(Token op, Type type) {
        super(op, type);
    }

    public Constant(int i){
        super(new Num(i), Type.Int);
    }
    @Override
    public void jumping(int t, int f){
        if (this == True && t != 0){
            emit("goto L" + t);
        }else if (this == False && f != 0){
            emit("goto L" + f);
        }
    }

}
