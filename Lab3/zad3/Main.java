package Lab3.zad3;

public class Main {
	public final static int N = 4;
	
	public static void main (String [] args){
		Waiter waiter = new Waiter();
	      for(int i = 0; i < N; i++) {
	         new Thread(new Client(i, waiter)).start();
	         new Thread(new Client(i, waiter)).start();
	      }
	}
}
