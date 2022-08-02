package main.Inter;

public class Break extends Stmt{
    public Stmt stmt;
    public Break(){
        if (Stmt.Enclosing == null){
            error("Unenclosed break^");
        }
        stmt = Stmt.Enclosing;
    }

    @Override
    public void gen(int b, int a) {
        emit("goto L" + stmt.after);
    }
}
