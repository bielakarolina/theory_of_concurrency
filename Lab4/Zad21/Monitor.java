package Lab4.Zad21;

import java.util.concurrent.locks.*;

public class Monitor {
	private Lock lock = new ReentrantLock();
	private Condition firstProd = lock.newCondition();
	private Condition firstCons = lock.newCondition();
	private Condition restProd = lock.newCondition();
	private Condition restCons = lock.newCondition();
	private boolean prodWaiting;
	private boolean consWaiting;
	
	private Buffer b;

//	Porcje są przydzielane zgłaszającym się konsumentom w takiej kolejności, w jakiej rozpoczęło się ich
//	wkładanie do bufora

	public Monitor(Buffer b) {
		this.b = b;
		prodWaiting = false;
		consWaiting = false;
	}
	public void produce(int rand, int id) throws InterruptedException {
		
		lock.lock();
		if(prodWaiting) 
			restProd.await();
		
		while (!b.fits(rand)) {
			firstProd.await();
			prodWaiting = true;
		}
		b.write(rand);
		b.printBuffer();
		restProd.signal();
		firstCons.signal();
		consWaiting = false;
		lock.unlock();
	}
	
	public void consume(int rand) throws InterruptedException {
		
		lock.lock();
		if(consWaiting)
			restCons.await();
		while(!b.eatable(rand)) {
			firstCons.await();
			consWaiting = true;
		}
		b.eat(rand);
		b.printBuffer();
		restCons.signal();
		firstProd.signal();
		prodWaiting = false;
		lock.unlock();
	}
}

