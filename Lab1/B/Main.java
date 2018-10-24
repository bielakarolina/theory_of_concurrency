package B;

public class Main {

    public static int ILOSC = 1000;

    public static void main (String args[]){
        Buffer buff = new Buffer();
        Producer p1 = new Producer(buff);
        Consumer c1 = new Consumer(buff);
        Thread prodThread = new Thread(p1);
        Thread consThread = new Thread(c1);
        prodThread.start();
        consThread.start();

        try{
            consThread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
