package Symbols;

import Lexer.Token;

import java.util.Hashtable;

public class Env {
    private Hashtable table;
    protected Env prev;

    public Env(Env prev) {
        table = new Hashtable();
        this.prev = prev;
    }

//    public void put(Token token, Id i){
//        table.put(token, i);
//    }
//
//    public Id get(Token token){
//        for (Env e = this; e != null; e = prev){
//            Id found = (Id) e.table.get(token);
//            return found;
//        }
//    }
}
