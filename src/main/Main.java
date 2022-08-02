package main;

import main.Lexer.Lexer;
import main.Parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Lexer lex = null;
        Parser parser = null;
        try {
            lex = new Lexer("./src/main/resources/test.txt");
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
