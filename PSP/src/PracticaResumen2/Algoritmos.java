package PracticaResumen2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Algoritmos {
	
	public static Scanner teclado = new Scanner(System.in);
	public int opcion;
	public int quantum;
	
	public void run() {
		menu();
	}
	
	public static ArrayList<Procesos> listaProcesos() {
		ArrayList<Procesos> lista = new ArrayList();
		Procesos A = new Procesos("A", 0, 5);
		lista.add(A);
		Procesos B = new Procesos("B", 2, 4);
		lista.add(B);
		Procesos C = new Procesos("C", 3, 3);
		lista.add(C);
		Procesos D = new Procesos("D", 5, 2);
		lista.add(D);
		Procesos E = new Procesos("E", 6, 3);
		lista.add(E);
		return lista;
	}
	
	public void menu() {
		//try {
		System.out.println("¿Qué desea realizar?");
		System.out.println("1.- Fifo");
		System.out.println("2.- Round Robin");
		System.out.println("3.- SRT");
		System.out.println("4.- SJF");
		System.out.println("0.- Salir");
		opcion = teclado.nextInt();
		switch(opcion) {
		case 1:
			Fifo fifo = new Fifo(listaProcesos());
			fifo.ejecucion();
			break;
		case 2:
			System.out.println("¿Cuánto quantum quieres hacer Round Robin?");
			quantum = teclado.nextInt();
			roundRobin(quantum);
			RoundRobin rr = new RoundRobin(listaProcesos(), quantum);
			rr.ejecucion();
			break;
		case 3:
			break;
		case 4:
			break;
		case 0:
			break;
		}
		/*} catch (InputMismatchException e) {
			System.out.println("No puedes escribir letras ._.");
		}*/
	}
	
	public void roundRobin(int quantum) {
		System.out.println("hola");
	}
}
