package Lab4.Zad2;

public class Main {
	final static int M = 1000;							//elementow ma byc 2M
	final static int producerNum = 1000;
	final static int consumerNum = 1000;

	public static void main (String [] args){
		Buffer buffer = new Buffer();
		Thread [] producers = new Thread [producerNum];
		Thread [] consumers = new Thread [consumerNum];
		
		for (int i = 0; i < producerNum; i++)
			producers[i] = new Thread(new Producer(i, buffer)); 
		
		for (int i = 0; i < consumerNum; i++)
			consumers[i] = new Thread(new Consumer(i, buffer));
		
		for (int i = 0; i < producerNum; i++)
			producers[i].start(); 
		
		for (int i = 0; i < consumerNum; i++)
			consumers[i].start();		
	}
}
