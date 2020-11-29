package McDonald;

public class Main {

	final static int numCocineros = 1;
	final static int numClientes = 3;
	
	public static void main(String[] args) {
		System.out.println("---------------------- BIENVENIDO AL McDonald's ----------------------");
		System.out.println();
		Bandeja bandeja = new Bandeja();
		Cocineros[] cocinero = new Cocineros[numCocineros];
		Clientes[] cliente = new Clientes[numClientes];
		
		for(int i = 0; i < numClientes; i++) {
			cliente[i] = new Clientes(i+1, bandeja);
			cliente[i].start();
		}
		
		for(int o = 0; o < numCocineros; o++) {
			cocinero[o] = new Cocineros(o+1, bandeja);
			cocinero[o].start();
		}
	}
}
