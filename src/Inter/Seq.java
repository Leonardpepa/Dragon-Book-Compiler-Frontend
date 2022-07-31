package Inter;

public class Seq extends Stmt{

    public Stmt stmt1, stmt2;

    public Seq(Stmt stmt1, Stmt stmt2){
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    @Override
    public void gen(int b, int a) {
        super.gen(a, b);
    }
}
