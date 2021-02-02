package WalkieTalkie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	// Asignacion de variables
	static int port = 6000;
	static boolean ocupado = false;
	static String frecuencia;
	private static String mensajeLocal = "";
	private static String mensajeOcupado = "OCUPADO";
	private static byte[] mensajeEnviar = new byte[2048];
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception{
		// Abro el socket para nada mas iniciar la api, escuchar peticiones
        DatagramSocket socket = new DatagramSocket(port);
        // Creación de un hilo en 2do plano para escuchar paquetes
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true) {
                	byte[] bufer = new byte[2048];
                    DatagramPacket recibido = new DatagramPacket(bufer, bufer.length);
                    try {
                        socket.receive(recibido);
                        String paquete = new String(recibido.getData());
                        String texto = paquete.trim();
                        // Si el paquete contiene OCUPADO, bloquea la opción de escribir 
                        // Y notifica de que el otro usuario esta escribiendo
                        if(texto.equalsIgnoreCase("OCUPADO")) {
                        	System.out.println("\n\t\t\t· El usuario está emitiendo...");
                            ocupado = true;
                            // Si el paquete contiene CAMBIO, da paso a la opción de poder escribir
                            // Y notifica que el otro usuario ha dejado de escribir
                        }else if(texto.equalsIgnoreCase("CAMBIO")){
                        	System.out.println("\n\t\t\t· El usuario ha dejado de emitir");
                        	ocupado = false;
                        	// Si el paquete contiene CAMBIO Y CORTO, da paso a la opción de poder escribir
                        	// Y notifica que el otro usuario ha cerrado la conexion
                        }else if(texto.equalsIgnoreCase("CAMBIO Y CORTO")){
                        	System.out.println("\n\t\t\t· El usuario ha salido de la frecuencia");
                        	ocupado = false;
                        }else {
                        	// En caso de no contener lo anterior, muestra el mensaje por pantalla
                            recibiendo(texto);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // Inicializacion del hilo
        Thread thread = new Thread(runnable);
        thread.start();
        startUp();
        while(!mensajeLocal.equalsIgnoreCase("Cambio y corto")) {
        	menu(socket);
        }
        socket.close();
    }
	
	/**
	 * Metodo que pide la ip (frecuencia) para conectarse con el otro cliente <br>
	 * y almacena la "frecuencia" en la variable ip.
	 */
	public static void startUp() {
		// Pregunta la IP a la que quiera conectarse
		System.out.println("¿A qué frecuencia se quiere unir?");
		frecuencia = teclado.next();
		// Notifica de que está estableciendo una conexion
		System.out.println("Uniendo a la frecuencia...\n");
		// Intenta conectarse
		try {
			InetAddress.getByName(frecuencia);
			// En caso de no poder, notifica al usuario y sale de la api
		} catch (UnknownHostException e) {
			// e.printStackTrace();
			System.out.println("Frecuencia incorrecta");
			System.out.println("Saliendo...");
			System.exit(0);
		}
	}
	
	/**
	 * Metodo que enseña por consola un menú con el cuál el usuario puede <br>
	 * hablar (escribir) un mensaje.
	 */
	public static void menu(DatagramSocket socket) {
		// variable para salir del menú
		boolean salirMenu = false;
		do {
			try {
				// Pregunta la opcion que desea realizar
				System.out.println("¿Qué desea realizar? (Solo número)");
				System.out.println("1.- Hablar");
				System.out.println("Otro.- Salir");
				System.out.print("Opción: ");
				int opcion = teclado.nextInt();
				switch(opcion) {
				case 1:
					hablando(socket);
					salirMenu = true;
					break;
				default:
					System.out.println("Saliendo...");
					System.exit(0);
					break;
				}
			} catch (Exception e) {
				// Controla que el usuario no pueda escribir palabras y lo notifica
				System.out.println("Estás en un menú con elecciones mediante número\n");
				teclado.nextLine();
			}
		}while (!salirMenu);
	}
	
	/**
	 * Metodo que permite hablar (escribir) por el walkie talkie hacia el otro <br>
	 * usuario, mientras haya uno en esta opcion, el otro no podra usarla.
	 */
	public static void hablando(DatagramSocket socket) throws Exception {
		// Si la variable "ocupado" devuelve falso, te deja escribir
		if(!ocupado) {
			// Devuelve a la variable "ocupado" del otro usuario verdadero
			enviarOcupado(socket);
			System.out.println("- Comienza a escribir: ");
			// Mientras el mensaje no sea "CAMBIO", el usuario podrá seguir escribiendo
			do {
				mensajeLocal = teclado.nextLine();
				// Si el mensaje no es "CAMBIO Y CORTO", envia el mensaje correctamente
				if(!mensajeLocal.equalsIgnoreCase("CAMBIO Y CORTO")) {
					mensajeEnviar = mensajeLocal.getBytes();
					DatagramPacket envio = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, InetAddress.getByName(frecuencia), port);
					socket.send(envio);
					// En caso de que el mensaje sea, envia el mensaje y cierra la api
				}else {
					mensajeEnviar = mensajeLocal.getBytes();
					DatagramPacket envio = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, InetAddress.getByName(frecuencia), port);
					socket.send(envio);
					System.out.println("- Saliendo...");
					System.exit(0);
				}
			} while(!mensajeLocal.equalsIgnoreCase("CAMBIO"));
			// En caso de que la variable "ocupado" devuelva verdadero, te notifica que no puedes escribir
		}else {
			System.out.println("El otro usuario está hablando");
			System.out.println("Por favor, espere\n");
		}
	}
	
	/**
	 * Metodo que envia al otro usuario un paquete el cual pone "OCUPADO" y bloquea <br>
	 * la opcion de escribir al otro usuario
	 * @param socket
	 * @throws IOException
	 */
	public static void enviarOcupado(DatagramSocket socket) throws IOException {
		mensajeEnviar = mensajeOcupado.getBytes();
		DatagramPacket inicioEnvio = new DatagramPacket(mensajeEnviar, mensajeEnviar.length, InetAddress.getByName(frecuencia), port);
		socket.send(inicioEnvio);
	}
	
	/**
	 * Metodo que muestra por consola los mensajes obtenidos
	 * @param texto
	 */
	public static void recibiendo(String texto) {
		if(texto.equalsIgnoreCase("")){
			
		}else {
			System.out.println("\t\t\tMensaje: " + texto);
		}
	}
}
