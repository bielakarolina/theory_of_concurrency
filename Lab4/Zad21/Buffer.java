package Lab4.Zad21;

import java.util.ArrayList;

public class Buffer {
	
	private ArrayList<Integer> buffer = new ArrayList<Integer>(Main.B);
	private int taken; // liczba elementów
	
	public Buffer() {
		taken = 0;
		for (int i = 0; i < Main.B; i++) {
			this.buffer.add(0);
		}
		printBuffer();
	}
	
	public void write(int howmany) {
		for (int i = 0; i < Main.B; i++) {
			if(howmany!=0) {
				if (buffer.get(i) == 0) {
					buffer.set(i, howmany);
					howmany--;
					taken++;
				}
			}
			else return;
		}
	}
	
	public void eat(int howmany) {
		for (int i = 0; i < Main.B; i++) {
			if(howmany!=0) {
				if (buffer.get(i) != 0) {
					buffer.set(i, 0);
					howmany--;
					taken--;
				}
			}
			else return;
		}
	}
	
	public boolean fits(int howmany) {
		if (Main.B - taken < howmany) {
			return false;
		}
		else return true;
	}
	
	public boolean eatable(int howmany) {
		if (taken < howmany) {
			return false;
		}
		else return true;
	}

	public void printBuffer() {
		System.out.println();
		for (Integer i : buffer) {
			System.out.print(" " + i + " ");
		}
		System.out.println();
	}
}

