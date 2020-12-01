package McDonald;

import java.util.Random;

public class Cocineros extends Thread{

	int id;
	Bandeja bandeja;
	
	public Cocineros(int id, Bandeja bandeja) {
		this.id = id;
		this.bandeja = bandeja;
	}
	
	/**
	 * Duerme al hilo durante X segundos
	 */
	public void cocinar() {
		this.bandeja.cocinar(id);
		try {
			Random rand = new Random();
			int low = 1000;
			int high = 3000;
			int dormir = rand.nextInt(high-low) + low;
			Thread.sleep(dormir);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			cocinar();
		}
	}
}