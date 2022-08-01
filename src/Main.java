import Lexer.Lexer;
import Parser.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Lexer lex = null;
        Parser parser = null;
        try {
            lex = new Lexer("C:/Users/USER/Desktop/programming/Java/Dragon Book Compiler/resources/tests/test");
            parser = new Parser(lex);
            parser.program();
            System.out.write('\n');
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
