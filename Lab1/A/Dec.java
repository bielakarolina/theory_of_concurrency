package A;

public class Dec implements Runnable{
    public Counter n;

    public Dec(Counter num){
        this.n=num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++)
            n.dec();
    }
}