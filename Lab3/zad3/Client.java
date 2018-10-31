package Lab3.zad3;

public class Client implements Runnable{
	private int pairId;
	private Waiter waiter;
	
	public Client(int id, Waiter w){
		this.pairId = id;
		this.waiter = w;
	}
	
	public void run(){
		try{
			Thread.sleep(250);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		waiter.takeTable(this.pairId); //zabieram stolik
		
		System.out.println(this.pairId+ " Siedzi przy stoliku");//jedzenie
		try{
			Thread.sleep(500);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		waiter.releaseTable();//zwalnianie stolika
	}
}
