package WalkieTalkie;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	// Asignación de variables
	private static InetAddress ip;
	static int port = 6000;
	static String frecuencia;
	private static String mensaje;
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket(port);
		startUp();
		do {
			menu();
		}while (!mensaje.equalsIgnoreCase("cambio y corto"));
		socket.close();
	}
	
	/**
	 * Método que pide la ip (frecuencia) para conectarse con el otro cliente <br>
	 * y almacena la "frecuencia" en la variable ip
	 */
	public static void startUp() {
		System.out.println("¿A qué frecuencia se quiere unir?");
		frecuencia = teclado.next();
		System.out.println("Uniendo a la frecuencia...");
		try {
			ip.getByName(frecuencia);
		} catch (UnknownHostException e) {
			// e.printStackTrace();
			System.out.println("Frecuencia incorrecta");
			System.out.println("Saliendo...");
			System.exit(0);
		}
	}
	
	public static void menu() {
		boolean salirMenu = false;
		do {
			try {
				System.out.println("¿Qué desea realizar?");
				System.out.println("1.- Hablar");
				System.out.println("2.- Escuchar");
				int opcion = teclado.nextInt();
				switch(opcion) {
				case 1:
					hablando();
					salirMenu = true;
					break;
				case 2:
					escuchando();
					salirMenu = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("Opción incorrecta\n");
				teclado.nextLine();
			}
		}while (!salirMenu);
	}
	
	public static void hablando() {
		System.out.println("estoy hablando xd");
	}
	
	public static void escuchando() {
		System.out.println("estoy escuchando xd");
	}

}
