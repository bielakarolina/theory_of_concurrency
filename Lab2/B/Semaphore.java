package Lab2.B;

public class Semaphore {

    final static int max = 2;

    private int semValue;

    public Semaphore(){
        semValue = max;
    }
    public synchronized void increase(){

        semValue++;
        notifyAll();
    }

    public synchronized void decrease(int clientID) throws InterruptedException {
        while (semValue == 0) {
            wait();
            System.out.println("Sklep jest pełny, dla klienta "+ clientID );
        }
        semValue--;
    }
}
