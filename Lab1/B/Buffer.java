package B;

public class Buffer {

    private String buffer;
    private Boolean isEmpty;

    public Buffer(){
        isEmpty = true;
    }

    public synchronized void put(String message) throws InterruptedException{
        while(isEmpty!=true){
            wait();
        }
        buffer = message;
        isEmpty = false;
        notifyAll();

    }

    public synchronized String take() throws InterruptedException{
        while(isEmpty==true){
            wait();
        }
        isEmpty = true;
        notifyAll();

        return buffer;
    }
}
