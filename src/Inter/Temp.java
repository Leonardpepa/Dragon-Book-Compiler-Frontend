package Inter;

import Lexer.Word;
import Symbols.Type;

public class Temp extends Expr{

    public static int count = 0;
    public int number = 0;
    public Temp(Type p){
        super(Word.temp, p);
        number = ++count;
    }

    @Override
    public String toString() {
        return "t" + number;
    }
}
