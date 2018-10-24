
package Lab3.zad2;

public class Task implements Runnable{
	private int taskId;
	private PrinterMonitor printMonitor;
	
	public Task(int taskId, PrinterMonitor printMonitor){
		System.out.println("Tworzymy zadanie o id " + taskId);
		this.taskId = taskId;
		this.printMonitor = printMonitor;
	}
	
	public void run(){
		int printerId;																		//do zadania trzeba przypisac konkretna drukarke
		System.out.println("Zadanie o id " + this.taskId + " - proba rozpoczecia.");
		printerId = printMonitor.takePrinter();
		System.out.println("Zadanie o id " + this.taskId + " - zajelo drukarke o id " + printerId);
		try{
			Thread.sleep(1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		printMonitor.releasePrinter(printerId);
	}
}
