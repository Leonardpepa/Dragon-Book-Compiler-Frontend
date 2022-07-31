import Lexer.Lexer;
import Parser.Parser;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        Lexer lex = new Lexer();

        Parser parser = null;
        try {
            parser = new Parser(lex);
            parser.program();
            System.out.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
