package Lab4.Zad2;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Consumer implements Runnable{
	private int consumerId;
	private Buffer buff;
	
	public Consumer(int id, Buffer buff){
		System.out.println("Utworzono konsumenta " + id);
		this.consumerId = id;
		this.buff = buff;
	}
	
	public void run(){
		Random generator = new Random();

			int rand = generator.nextInt(Main.M/2) + 1;
		long startGetTime = System.nanoTime();
			System.out.println("Konsument "+ consumerId + " chce pobrac wartosci " + rand + " komorek.");
			buff.take(rand, this.consumerId);
			System.out.println("Konsument "+ consumerId + " pobral dane  do " + rand + "komorek.");
		long estimatedGetTime = System.nanoTime() - startGetTime;

		try {
			FileWriter file = new FileWriter("getZGlodzeniem.txt", true);
			BufferedWriter out = new BufferedWriter(file);
			out.write((double)estimatedGetTime/1_000_000_000.0 + " , " + rand + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
