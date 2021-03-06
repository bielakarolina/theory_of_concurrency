package A;

//import Lab2.BinarySem;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter obj = new Counter(0);

        //BinarySem binary1 = new BinarySem();

        Thread thread1 = new Thread(new Inc(obj));
        Thread thread2 = new Thread(new Dec(obj));
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