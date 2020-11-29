package McDonald;

public class Clientes extends Thread {
	
	int id;
	Bandeja bandeja;
	
	public Clientes(int id, Bandeja bandeja) {
		this.id = id;
		this.bandeja = bandeja;
	}
	
	public void run() {
		while(true) {
			bandeja.consumir(id);
			try {
				Thread.sleep((long) ((Math.random()+1)*3));
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
