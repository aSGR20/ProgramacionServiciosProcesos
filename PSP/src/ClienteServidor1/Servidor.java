package ClienteServidor1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws Exception {
		//Asigno el puerto al cual el servidor quiere escuchar
		int numeroPuerto = 6000;
		
		//Creo el Socket con dicho puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        Socket clienteConectado = null;
        System.out.println("Esperando la conexión del cliente...");
        
        //Espero a tener una conexión con algun cliente
        clienteConectado = servidor.accept();
        System.out.println("\n\tConexión realizada\n");
        System.out.println("Esperando el mensaje del cliente...");
       
        
        //Una vez aceptada, creo un flujo de entrada 	[ESCUCHA]
        InputStream entrada = null;
        entrada = clienteConectado.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);
       
        //Muestro por consola el flujo de entrada
        System.out.println("Mensaje recibido: \n\t" + flujoEntrada.readUTF());
       
        
        //Creo un flujo de salida						[ESCRIBE]
        System.out.println("Escribiendo el mensaje del servidor...");
        OutputStream salida = null;
        salida = clienteConectado.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);
       
        //Envio el flujo de salida
        flujoSalida.writeUTF("Saludos al Cliente desde el Servidor");
        System.out.println("\n\tMensaje enviado\n");
        
        
        //Cierro todos los flujos y conexiones			[TERMINA]
        System.out.println("\tCerrando conexión...");
        entrada.close();
        flujoEntrada.close();
        salida.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();
	}
}
