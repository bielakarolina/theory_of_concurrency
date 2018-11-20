package Lab4.Zad21;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class Producer extends Job implements Runnable {
	
	
	public Producer(int id, Monitor m) {
		super(id, m);
	}
	File plik = new File("czasProducent2.txt");

	@Override
	public void run() {
		Random generator = new Random();
		int rand = generator.nextInt(Main.M/2) + 1;;
		long systime = System.nanoTime();

		try {

			System.out.println("Producent " + id + " rozpoczyna produkowanie "+ rand + " elementów;");
		monitor.produce(rand, id);
			long estimatedPutTime = System.nanoTime() - systime;

			System.out.println("Producent " + id + " zakończył produkowanie "+ rand + " elementów;");

		FileWriter file = new FileWriter("czasProducent2.txt", true);
		BufferedWriter out = new BufferedWriter(file);
			out.write((double)estimatedPutTime/1000000000.0 + " , " + rand + "\n");
		out.close();
		}catch(Exception e) {}
	}

}
