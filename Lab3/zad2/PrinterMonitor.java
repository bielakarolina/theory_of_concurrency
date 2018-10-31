package Lab3.zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class PrinterMonitor {

    public final static int M = 2;		//liczba drukarek
    public final static int N = 10;		//liczba watkow

	final Lock lock = new ReentrantLock();
	final Condition freePrinters = lock.newCondition();
		
	private boolean [] printerTaken = new boolean[M]; 		//sluzy do oznaczenia zajetych drukarek
	private int counter = 0;										// zliczanie zajetych drukarek
	
	public PrinterMonitor(){
		for (int i = 0; i < M; i++)
			printerTaken[i] = false;
	}
	
	//zwraca id drukarki
	public int takePrinter(){		
		lock.lock();
		try {
			while(counter == printerTaken.length){			//dopoki wszystkie drukarki zajete
				freePrinters.await();						//czekaj na warunek freePrinters
				System.out.println("Oczekiwanie na wolną drukarke.");
			}
					
		}catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		
		counter++;
		int index = -1;
		//szukamy wolnej drukarki
		for(int i = 0; i < M; i++){
			if(printerTaken[i] == false){
				printerTaken[i] = true;				//zajmujemy drukarke
				index = i;							//zaznaczamy ktora drukarke zajelismy
				break;
			}					
		}
		if(index == -1)
			System.out.println("ERROR zajmowania drukarki");
			
		lock.unlock();
		return index;								//zwracamy index rowny numerowi zajetej drukarki
		
	}
	
	public void releasePrinter(int id){
		lock.lock();
		counter--;
		if(printerTaken[id] == false)
			System.out.println("ERROR, Zwalnianie wolnej drukarki");
		printerTaken[id] = false;
		freePrinters.signal();
        System.out.println("Zwalnianie drukarki o id "+ id);

        lock.unlock();
	}
}
