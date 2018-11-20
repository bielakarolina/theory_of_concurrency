package Lab4.zad1_sem;

import java.util.ArrayList;

public class Main {
	
	public static final int N = 10; //bufor
	public static final int K = 5; //watki

	public static void main(String[] args) {
		
		ArrayList <Semaphore> sem = new ArrayList<Semaphore>(K);
		ArrayList <Integer> buffer = new ArrayList <Integer>(N);
		ArrayList <Thread> threads = new ArrayList <Thread>();
		
		Producer p;
		Thread t;
		
		
		for (int i = 0; i < N; i++) buffer.add(i, 0);
		
		for(int i = 0; i < K; i++) {
			if (i==0) sem.add(i, new Semaphore(N));
			else 
				sem.add(i, new Semaphore(0));
		}
		
		
		for (int i = 0; i < K; i++) {
			p = new Producer(N, i, sem, buffer);
	    	t = new Thread(p);
	    	threads.add(t);	    
		}
		
		for (Thread th : threads) {
			th.start();
		}
		try {
			for (Thread th : threads) {
			th.join();
			}
		} catch (Exception e) {}

		System.out.println("Wartości w buffor: ");

		for (int i = 0; i < N; i++) {
			System.out.print(" " + buffer.get(i)+ " ");
		}
		
	}
}
