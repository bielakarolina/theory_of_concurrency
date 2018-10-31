
package Lab3.zad2;

public class Task implements Runnable{
	private int taskId;
	private PrinterMonitor printMonitor;
	
	public Task(int taskId, PrinterMonitor printMonitor){
		System.out.println("Tworzenie zadania o id: " + taskId); //  Utworz_zadanie_do_druku();

		this.taskId = taskId;
		this.printMonitor = printMonitor;
	}
	
	public void run(){
		int printerId;
		System.out.println("Zadanie o id " + this.taskId + " - zaczynamy."); //nr_drukarki=Monitor_Drukarek.zarezerwuj();
		printerId = printMonitor.takePrinter();
		System.out.println("Zadanie o id " + this.taskId + " drukarka zajeta: " + printerId);  // drukuj(nr_drukarki);
		try{
			Thread.sleep(500);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		printMonitor.releasePrinter(printerId); //  Monitor_Drukarek.zwolnij(nr_drukarki);

	}
}
