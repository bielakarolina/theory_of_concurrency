package Lab2.A;

public class BinarySem {
    private boolean blocked;

    public BinarySem (){
        this.blocked = false;
    }


    public synchronized void block() throws InterruptedException{
        while (blocked == true)
            wait();

        blocked = true;
    }

    public synchronized void unlock(){
        this.blocked = false;
        notifyAll();
    }
}
