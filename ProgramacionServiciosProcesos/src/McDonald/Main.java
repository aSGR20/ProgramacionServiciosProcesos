package McDonald;

public class Main {
	
	final static int numCocineros = 3;
	final static int numClientes = 3;
	
	/**
	 * Mensaje de bienvenida al McDonald's e iniciliza los hilos 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("---------------------- BIENVENIDO AL McDonald's ----------------------");
		System.out.println();
		Bandeja bandeja = new Bandeja();
		Cocineros[] cocinero = new Cocineros[numCocineros];
		Clientes[] cliente = new Clientes[numClientes];
		
		for(int i = 0; i < numClientes; i++) {
			cliente[i] = new Clientes(i+1, bandeja);
		}
		
		for (Clientes clientes : cliente) {
			clientes.start();
		}
		
		for(int o = 0; o < numCocineros; o++) {
			cocinero[o] = new Cocineros(o+1, bandeja);
		}
		
		for (Cocineros cocineros : cocinero) {
			cocineros.start();
		}
	}
}
