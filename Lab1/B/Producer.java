package B;

public class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < Main.ILOSC;   i++) {
            try {
                buffer.put("message "+i);
            //    System.out.println("message "+i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
