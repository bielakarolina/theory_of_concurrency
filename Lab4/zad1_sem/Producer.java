package Lab4.zad1_sem;

import java.util.ArrayList;

public class Producer implements Runnable {
	
	private int id;
	private ArrayList<Semaphore> sem;
	private ArrayList<Integer> buffer;
	private int position;
	
	
	public Producer(int N, int id, ArrayList<Semaphore> s, ArrayList<Integer> b) {
		this.position = 0;
		this.id = id;
		this.buffer = b;
		this.sem = s;
	}
	
	@Override
	public void run() {
			for(int i = 0; i < Main.N; i++) {
				try {
		//opuszczam semafor, przetwarzam, a później go podnoszę by dwa wątki na raz się nie wykonywały
					sem.get(id).down();
					processing(position);
					position = (position + 1) % Main.N;

					sem.get((id+1)%Main.K).up();
		
				} catch(Exception e) {}
			}
		}

	
	public void processing (int pos) {
		int num = buffer.get(pos) + 1;
		buffer.set(pos, num);
		System.out.println("Wątek nr "+ id +" zmienił zawartość bufora #" + position +" na "+ num);
	}
}
