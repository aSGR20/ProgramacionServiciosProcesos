package McDonald;

public class Bandeja {

	int cantidad;
	int cantidadTotal;
	int cantidadPedida;
	
	public Bandeja() {
		cantidad = 0;
		cantidadTotal = 0;
		cantidadPedida = 0;
	}
	
	public synchronized void consumir(int id) {
		if (cantidad>0) {
			cantidad--;
			cantidadTotal++;
			System.out.println("El Cliente "+ id +" ha comido una hamburguesa.");
			System.out.println("Quedan "+ cantidad +" hamburguesas en la bandeja");
			System.out.println();
			notify();
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			cantidadPedida++;
			System.out.println("El cliente "+ id +" ha pedido una hamburguesa, ya van "+ cantidadPedida +" hamburguesas pedidas");
			System.out.println();
			notify();
		}
	}
	
	public synchronized void cocinar(int id) {
		if(cantidadPedida!=0) {
			cantidadPedida--;
			cantidad++;
			
			System.out.println("El Cocinero "+ id +" ha hecho una hamburguesa");
			System.out.println("Hay "+ cantidad +" hamburguesas en la bandeja");
			System.out.println("Quedan "+ cantidadPedida +" hamburguesas por hacer");
			System.out.println();
			notify();
		} else {
			System.out.println("El Cocinero "+ id +" está preparandolo todo para el siguiente pedido");
			System.out.println();
			notify();
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
