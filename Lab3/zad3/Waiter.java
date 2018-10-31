package Lab3.zad3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
	private final Lock lock = new ReentrantLock();
	private int[] firstpair = new int[Main.N];
	private final Condition freeTable = lock.newCondition();					
	private int eatingClients = 0;
	private Condition[] secondPersonpair = new Condition[Main.N];	
	
	
	public Waiter(){
		for(int i = 0; i < Main.N; i++){
			firstpair[i] = 0;										//najpierw nie ma nikogo z pary
			secondPersonpair[i] = lock.newCondition();			// warunek, druga osoba z pary musi poprosić o stolik aby dostać
		}		
	}
	
	public void takeTable(int pairId){
		lock.lock();
		System.out.println("Nadszedl klient o id pary " + pairId);	
		firstpair[pairId]++;
															
		try{															
			if(firstpair[pairId] != 2)								//jeżeli to nie jest druga osoba z pary
				secondPersonpair[pairId].await();	
			
			else {													//jesli obie osoby przyszly
				while(eatingClients == 2)							//dopoki nie ma wolnego stolu to czekamy
					freeTable.await();
				System.out.println("Para " + pairId + " dostała stolik");
				eatingClients = 2;
				secondPersonpair[pairId].signal();				//informujemy drugą osobe z pary
			}		
			
		}catch (InterruptedException e){
			e.printStackTrace();
		}
				
		lock.unlock();
	}
	
	public void releaseTable(){
		lock.lock();
		eatingClients--; //zwalniamy pojedynczo, warunek zadania
		
		if(eatingClients == 0){
			System.out.println("Zwolniono stolik.");
			freeTable.signal();
		}
				
		lock.unlock();
	}
}
