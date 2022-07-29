package Lexer;

import java.util.Hashtable;

public class Lexer {
    public static int line = 1;

    char peek = ' ';
    Hashtable words = new Hashtable();

    public void reserve(Word w){
        words.put(w.lexeme, w);
    }

    public Lexer() {

        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(Word.True);
        reserve(Word.False);
//        reserve(Type.Int);
//        reserve(Type.Char);
//        reserve(Type.Bool);
//        reserve(Type.Float);


    }
}
