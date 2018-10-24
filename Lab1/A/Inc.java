package A;

public class Inc implements Runnable{
    public Counter n;

    public Inc(Counter num){
        this.n=num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++)
            n.inc();

    }
}