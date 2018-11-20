package Lab5.maldenbrot;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;


//os x - rzeczywiste, y - urojone
public class Mandelbrot implements Callable<Object> {
	
	private int x;
	private int y;
	private int max_iter;
	private double ZOOM;
	private int task;
	BufferedImage I;
	
	public Mandelbrot(int x, int y, int max_iter, double ZOOM, BufferedImage I, int tasks) {
		this.x = x;
		this.y = y;
		this.max_iter = max_iter;
		this.ZOOM = ZOOM;
		this.I = I;
		this.task=tasks;
	}
	
	public Object call() throws Exception {
		for (int i = 0; i < task; i++) {
			double zx = 0;
			double zy = 0;
			double cX = (x - 400) / ZOOM;
			double cY = (y - 300) / ZOOM;
			int iter = max_iter;
			double tmp;
			while (zx * zx + zy * zy < 4 && iter > 0) {                //dopoki |z| < 2 w zaleznosci od przyjetej maksymalnej liczby iteracji
				tmp = zx * zx - zy * zy + cX;                        //z = z^2 + p, gdzie z = zx + izy
				zy = 2.0 * zx * zy + cY;
				zx = tmp;
				iter--;
			}

			I.setRGB(x, y, iter | (iter << 8));                    //kolorujemy piksel w zaleznosci od uzyskanej liczby iter
		}
		return null;
	}
}
