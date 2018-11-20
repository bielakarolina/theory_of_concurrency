package Lab4.Zad21;

import java.util.ArrayList;

public class Main {
	
	public static final int M = 1000; //max liczba wstawianych elementów
	public static final int K = 1000; //konsumenci;
	public static final int P = 1000; //producenci
	public static final int B = 2*M; //bufor
	
	public static void main(String[] args) throws InterruptedException {
		
		Buffer b = new Buffer();
		Monitor m = new Monitor(b);
		ArrayList<Thread> threads = new ArrayList<Thread>();
		Thread t;
		for (int i=0; i<K; i++) {
		    t = new Thread(new Consumer(i, m));
		    threads.add(t);	    
		}
		for (int i=0; i<P; i++) {
		    t = new Thread(new Producer(i, m));
		    threads.add(t);	    
		}
		
		for(Thread th : threads) {
			th.start();
		}
		
		for(Thread th : threads) {
			th.join();
		}
		

	}

}