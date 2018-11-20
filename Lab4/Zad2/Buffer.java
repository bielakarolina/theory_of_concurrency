package Lab4.Zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
	final Lock lock = new ReentrantLock();
	final Condition freeCells  = lock.newCondition();
	final Condition takenCells  = lock.newCondition();
	final Condition letMeTryProd  = lock.newCondition();				//pozwolenie na ubieganie sie o wejscie do bufora
	final Condition letMeTryCons = lock.newCondition();
	private boolean producerInWaitingQueue;								//mowi, czy juz jakis producent czeka na wprowadzanie zmian w buforze
	private boolean consumerInWaitingQueue;		
	
	private int [] buffTab;
	private int currentlyFreeCells;
	
	public Buffer(){
		buffTab = new int [2 * Main.M];
		currentlyFreeCells = 2 * Main.M;
		producerInWaitingQueue = false;	 					//na poczatku zaden watek nie czeka w kolejce do bufora
		consumerInWaitingQueue = false;
		for (int i = 0; i < 2 * Main.M; i++)
			buffTab[i] = -1;
	}
	
	public void put(int cellsToPut, int id){
		try{
			lock.lock();
			if (producerInWaitingQueue)						//jesli jakis producent czeka juz w kolejce do wejscia do bufora to czekamy
				letMeTryProd.await();						//watek czeka az bedzie mogl ubiegac sie o wejscie, ale jakis inny ma wejsc wczesniej
																	
																//while - bo po wybudzeniu z warunku i tak moze brakowac wolnych komorek do zapisu
			while (cellsToPut > currentlyFreeCells){			//chcemy wlozyc wiecej niz wolnych komorek w buforze => czekamy
				producerInWaitingQueue = true;
				freeCells.await();
			}
															//zaczynamy zmiany
			for (int i = 0; i < cellsToPut; i++){
				int j = 0;
				boolean freeCellFound = false;
				while (j < 2 * Main.M && freeCellFound != true){			//tam gdzie -1 mozemy przerobic komorke na 1
					if (buffTab[j] == -1){
						buffTab[j] = 1;
						currentlyFreeCells--;
						freeCellFound = true;
						System.out.println("Producent " + id + " zapisal wartosc do komorki: " + j);
					}
					j++;
				}
			}
			
			printBuffer();
			
			letMeTryProd.signal(); 								//budzimy kolejny watek ktory chce wejsc do bufora 
			takenCells.signal();								//dajemy znak jednemu konsumentowi, ze juz bufor nie jest pusty
			consumerInWaitingQueue = false;						//konsument, ktory czekal na sprawdzenie czy jest juz wystarczajaca liczba 
																//elementow do skonsumowania moze juz to zrobic (ale tylko jeden)
			
			lock.unlock();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	public void take(int cellsToTake, int id){
		try{
			lock.lock();
			
			if (consumerInWaitingQueue){
				letMeTryCons.await();
				System.out.println("----Konsument " + id + " czeka az jakis inny konsument skonczy dzialanie");
			}
			
			while (cellsToTake > 2*Main.M - currentlyFreeCells){			//nie ma wystarczajaco wiele el w buforze
				takenCells.await();
				System.out.println("*****Konsument " + id + " czeka na wystarczajaca liczbe wartosci do pobrania");
				consumerInWaitingQueue = true;
			}
				
			for (int i = 0; i < cellsToTake; i++){
				int j = 0;
				boolean cellTaken = false;
				while (j < 2 * Main.M && cellTaken == false){
					if (buffTab[j] != -1){
						buffTab[j] = -1;
						currentlyFreeCells++;
						cellTaken = true;
						System.out.println("Konsument "+ id + " pobral wartosc z komorki: " + j);
					}
					j++;
				}
			}
			printBuffer();
			
			
			freeCells.signal();
			letMeTryCons.signal();
			producerInWaitingQueue = false;
			lock.unlock();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	private void printBuffer(){
		System.out.println();
		System.out.println();
		for (int i = 0; i < 2*Main.M; i++)
			System.out.print("[" + buffTab[i] + "]");
		System.out.println();
		System.out.println();
	}
}
