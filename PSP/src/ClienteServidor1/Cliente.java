package ClienteServidor1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws Exception {
		//Asigno la localizaci�n y el puerto al que me voy a conectar
		String Host = "localhost";
        int Puerto = 6000;//puerto remoto
       
        //Establezco conexi�n con el socket
        System.out.println("Estableciendo conexi�n con el servidor...");
        Socket Cliente = new Socket(Host, Puerto);
        System.out.println("\n\tConexi�n realizada\n");
       
        //Una vez aceptada, creo un flujo de salida		[ESCRIBE]
        System.out.println("Escribiendo el mensaje del cliente...");
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
       
        //Envio el flujo de salida
        flujoSalida.writeUTF("Saludos al Servidor desde el Cliente");
        System.out.println("\n\tMensaje enviado\n");
       
        //Creo un flujo de entrada						[ESCUCHA]
        System.out.println("Esperando el mensaje del servidor...");
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
       
        //Mientro por conhsola el flujo de entrada 
        System.out.println("Mensaje recibido: \n\t" + flujoEntrada.readUTF());
       
        //Cierro todos los flujos y conexiones			[TERMINA]
        System.out.println("\n\tCerrando conexi�n...");
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
	}
}
