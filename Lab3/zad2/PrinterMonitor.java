package Lab3.zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//w mainie tworze drukarki 
//drukarki przekazuje do monitora
//monitor zarzadza drukarkami

public class PrinterMonitor {
	final Lock lock = new ReentrantLock();
	final Condition freePrinters = lock.newCondition();
		
	private boolean [] printerTab = new boolean[Main.M]; 		//okresla ktore drukarki sa zajete - jesli true to drukarka o id == indeksowi jest zajeta
	private int counter = 0;										// = Main.M;  // okresla liczbe zajetych drukarek
	
	public PrinterMonitor(){
		for (int i = 0; i < Main.M; i++)
			printerTab[i] = false;								//ustawiamy drukarki na niezajete 
	}
	
	//zwraca id drukarki
	public int takePrinter(){		
		lock.lock();
		try {
			while(counter == printerTab.length){			//dopoki wszystkie drukarki zajete
				freePrinters.await();						//czekaj na warunek freePrinters
				System.out.println("Czekamy na wolna drukarke.");
			}
					
		}catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		
		counter++;
		int index = -1;
		//szukamy wolnej drukarki
		for(int i = 0; i < Main.M; i++){			 
			if(printerTab[i] == false){
				printerTab[i] = true;				//zajmujemy drukarke
				index = i;							//zaznaczamy ktora drukarke zajelismy
				break;
			}					
		}
		if(index == -1)
			System.out.println("Cos poszlo nie tak");
			
		lock.unlock();
		return index;								//zwracamy index rowny numerowi zajetej drukarki
		
	}
	
	public void releasePrinter(int id){
		lock.lock();
		counter--;
		if(printerTab[id] == false)
			System.out.println("Cos poszlo nie tak");
		printerTab[id] = false;
		freePrinters.signal();						//
		lock.unlock();
	}
}
