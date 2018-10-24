package Lab2.B;

public class Main {
    public static void main (String args[]){
        Semaphore sem = new Semaphore();

        Thread c1 = new Thread(new Client(sem, 1));
        Thread c2 = new Thread(new Client(sem, 2));
        Thread c3 =  new Thread(new Client(sem, 3));
        Thread c4=  new Thread(new Client(sem, 4));
        Thread c5 =  new Thread(new Client(sem, 5));
        Thread c6 =  new Thread(new Client(sem, 6));

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();

        try{
            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();
            c6.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    }

