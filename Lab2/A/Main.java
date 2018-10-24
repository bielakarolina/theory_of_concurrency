package Lab2.A;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter obj = new Counter(0);

        BinarySem binary1 = new BinarySem();

        Thread thread1 = new Thread(new Inc(obj,binary1));
        Thread thread2 = new Thread(new Dec(obj,binary1));
        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(obj.getNum());
    }
}