package Lab4.Zad21;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class Consumer extends Job implements Runnable {
	
	public Consumer(int id, Monitor monitor) {
		super(id, monitor);
	}
	File plik = new File("czasKonsument2.txt");

	@Override
	public void run(){
		Random generator = new Random();
		int rand = generator.nextInt(Main.M/2) + 1;;
		long systime = System.nanoTime();
		try {

		System.out.println("#Konsument " + id + " rozpoczyna konsumowanie "+ rand + " elementów;");
		monitor.consume(rand);
			long estimatedPutTime = System.nanoTime() - systime;

			System.out.println("#Konsument " + id + " zakończył konsumowanie "+ rand + " elementów;");


		FileWriter file = new FileWriter("czasKonsument2.txt", true);
		BufferedWriter out = new BufferedWriter(file);
			out.write((double)estimatedPutTime/1000000000.0 + " , " + rand + "\n");
		out.close();

		}catch (Exception e) {
			
		}
	}
}
