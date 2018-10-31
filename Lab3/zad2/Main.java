package Lab3.zad2;

import static Lab3.zad2.PrinterMonitor.N;

public class Main {

	
	public static void main (String[] args){
		PrinterMonitor m = new PrinterMonitor();
		
		Thread [] threads = new Thread[N];
		for(int i = 0; i < N; i++){
			threads[i] = new Thread(new Task(i, m));
		}
		
		for(int i = 0; i < N; i++){
			threads[i].start();
		}
		
		try{
			threads[0].join();
		} catch (InterruptedException e){
			System.out.println("InterruptedException");
		}
		
	}
}
