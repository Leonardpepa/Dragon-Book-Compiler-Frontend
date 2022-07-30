package Symbols;

import Lexer.Tag;
import Lexer.Word;

public class Type extends Word {

    public int width;

    public static final Type Int = new Type("int", Tag.BASIC, 4),
                             Float = new Type("float", Tag.BASIC, 8),
                             Char = new Type("char", Tag.BASIC, 1),
                             Bool = new Type("bool", Tag.BASIC, 1);


    public Type(String lexeme, int tag, int width) {
        super(lexeme, tag);
        this.width = width;
    }


    public static boolean numeric(Type p){
        return (p == Type.Int || p == Type.Char || p == Type.Float);
    }

    public static Type max(Type t1, Type t2){

        if ( !numeric(t1) || !numeric(t2)){
            return null;
        }

        if (t1 == Type.Float || t2 == Type.Float) {
            return Type.Float;
        }else if (t1 == Type.Int || t2 == Type.Int){
            return Type.Int;
        }

        return Type.Char;

    }


}
