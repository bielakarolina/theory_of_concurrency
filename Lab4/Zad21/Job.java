package Lab4.Zad21;

import java.util.Random;

public class Job implements Runnable {
	
	protected int id;
	protected Monitor monitor;
	Random r = new Random();
	
	public Job(int id, Monitor m) {
		this.id = id;
		this.monitor = m;
	}
	
	public int getRandom() {
		int rand = r.nextInt(Main.M);
		return rand;
	}

	@Override
	public void run() {
		
	}

}
