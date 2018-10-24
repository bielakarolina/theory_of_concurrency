package Lab2.B;

public class Client implements Runnable{
    private Semaphore sem;
    private int n;

    public Client(Semaphore sem, int n){
        this.sem = sem;
        this.n = n;
    }

    private void shopping() throws InterruptedException{
        System.out.println("Kupowanie " + n);
        sem.increase();
    }

    @Override
    public void run(){

        try{
            sem.decrease(n);
            Thread.sleep(5);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        try{
            shopping();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}


