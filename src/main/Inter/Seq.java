package main.Inter;

public class Seq extends Stmt{

    public Stmt stmt1, stmt2;

    public Seq(Stmt stmt1, Stmt stmt2){
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    @Override
    public void gen(int b, int a) {
        if (stmt1 == Stmt.Null){
            stmt2.gen(b, a);
        }else if (stmt2 == Stmt.Null){
             stmt1.gen(b, a);
        }else{
            int label = newLabel();
            stmt1.gen(b, label);
            emitLabel(label);
            stmt2.gen(label, a);
        }

    }
}
