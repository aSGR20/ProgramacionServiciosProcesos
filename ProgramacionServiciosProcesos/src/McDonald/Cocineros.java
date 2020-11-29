package McDonald;

public class Cocineros extends Thread{

	int id;
	Bandeja bandeja;
	
	public Cocineros(int id, Bandeja bandeja) {
		this.id = id;
		this.bandeja = bandeja;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep((long) ((Math.random())*3));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bandeja.cocinar(id);
		}
	}
}
