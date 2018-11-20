package Lab4.Zad2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Producer implements Runnable{
	private int producerId;
	private Buffer buff;
	
	public Producer(int id, Buffer buff){
		System.out.println("Utworzono producenta " + id);
		this.producerId = id;
		this.buff = buff;
	}

	
	public void run(){
		Random generator = new Random();
			int rand = generator.nextInt(Main.M/2) + 1;

		long startPutTime = System.nanoTime();

		System.out.println("Producent "+ producerId + " chce zapisac do " + rand + " komorek.");
			buff.put(rand, this.producerId);
			System.out.println("Producent "+ producerId + " dodal dane do " + rand + " komorek.");
		long estimatedPutTime = System.nanoTime() - startPutTime;


		try {
			FileWriter file = new FileWriter("putZGlodzeniem.txt", true);
			BufferedWriter out = new BufferedWriter(file);
			out.write((double)estimatedPutTime/1_000_000_000.0 + " , " + rand + "\n");
			out.close();
			} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
