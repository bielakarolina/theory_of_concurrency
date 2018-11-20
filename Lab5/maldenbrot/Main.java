package Lab5.maldenbrot;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFrame;
 
public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private final double Zoom = 200;
    private final int MaxIter = 100;
    private final int NumberOfThreads = 1;
    private final int Tasks = 10;

    //private final int NumberOfThreads = 2; //tyle wątków co rdzeni
    //private final int NumberOfThreads = 4; // dwa razy więcej wątków niż rdzeni
    //private final int MaxIter = 100;
    //private final int NumberOfThreads = 1;

    private BufferedImage img;
	
 //A Future represents the result of an asynchronous computation.
    public Main() throws InterruptedException, ExecutionException {
        setBounds(0, 0, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        //The Thread Pool pattern helps to save resources in a multithreaded application,
        //and also to contain the parallelism in certain predefined limits.
        //When you use a thread pool, you write your concurrent code in the
        //form of parallel tasks and submit them for execution to an instance of a thread pool.
        ExecutorService pool = Executors.newFixedThreadPool(NumberOfThreads);
        //Creates a thread pool that reuses a fixed number of threads, aktywnych maksymalnie NumberOfThreads watkow
        Set<Future<Object>> callables = new HashSet<Future<Object>>();
        //future reprezentuje wynik asynchronicznego obliczania

        for (int y = 0; y < getHeight(); y++) {							//obliczenia dla kazdego wiersza pikseli na obrazku
            for (int x = 0; x < getWidth(); x++) {						//obliczenia dla kazdej kolumny pikseli
            	
            	//dla danego piksela przeprowadzamy obliczenia
                Callable<Object> callable = new Mandelbrot(x, y, MaxIter, Zoom, img, Tasks);
                //A task that returns a result and may throw an exception
                callables.add(pool.submit(callable));
                //Interfejs ExecutorService zawiera trzy metody submit(...), kt�re tworz� - i zwracaj� - dla nas Futub            re.
            }
            //Submits a value-returning task for execution and returns a Future representing
            // the pending results of the task.
        }			//submit przekazuje egzekutorowi do wykonania zadanie, ale jednoczesnie moze
        // wykonywac sie jedynie ich numberofthreads
       
        for (Future<Object> callable : callables) {
        	callable.get();									//get() = join() i zwrocenie wartosci (po zakonczeniu sie watku)
        }
    }
 
    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
 
    public static void main(String[] args) {
        try{
        	long start = System.nanoTime();
	    	new Main().setVisible(true);
	    	long end = System.nanoTime();
	    	long tmp = end - start;
	    	long result = tmp / 1000000;
	    	System.out.println("Wynik: " + result + "[ms]");
        } catch(InterruptedException e){
        	e.printStackTrace();
        } catch (ExecutionException e){
        	e.printStackTrace();
        }
    }
}