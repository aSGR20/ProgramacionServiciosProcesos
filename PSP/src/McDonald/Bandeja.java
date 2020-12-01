package McDonald;

public class Bandeja {

	int cantidad;
	int cantidadPedida;
	
	public Bandeja() {
		cantidad = 0;
		cantidadPedida = 0;
	}
	
	/**
	 * Sección crítica de los clientes
	 * @param id
	 */
	public synchronized void consumir(int id) {
		if (cantidad>0) {
			cantidad--;
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
	
	/**
	 * Sección crítica de los cocineros
	 * @param id
	 */
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