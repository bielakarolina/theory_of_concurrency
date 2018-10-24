package Lab2.A;

public class Inc implements Runnable{
    public Counter n;
    public BinarySem b;

    public Inc(Counter num, BinarySem bsem){

        this.n=num;
        this.b=bsem;
    }

    @Override
    public void run() {

        try {
            b.block();

            for (int i = 0; i < 100000000; i++)
                n.inc();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.unlock();
    }
}