package Lab4.zad1_sem;

public class Semaphore {
	private int sem;
	
	public Semaphore (int i) {
		sem = i;
	}
	
	public synchronized void up() {
		notifyAll();
		sem+=1;
	}
	
	public synchronized void down () throws InterruptedException {
		while(sem == 0) {
			wait();
		}
		sem-=1;
	}
}
