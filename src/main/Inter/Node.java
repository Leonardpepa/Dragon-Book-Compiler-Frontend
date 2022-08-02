package main.Inter;

import main.Lexer.Lexer;

public class Node {

    public int lexline = 0;

    public static int labels = 0;

    public Node() {
        lexline = Lexer.line;
    }

    public void error(String s){
        throw new Error("new line: " + lexline + " " + s);
    }


    public int newLabel(){
        return ++labels;
    }

    public void emitLabel(int label){
        System.out.println("L" + label + ":");
    }

    public void emit(String s){
        System.out.println("\t" + s);
    }

}
