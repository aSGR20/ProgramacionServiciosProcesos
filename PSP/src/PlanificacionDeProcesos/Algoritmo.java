package PlanificacionDeProcesos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Algoritmo {
	
	private Scanner teclado = new Scanner(System.in);
	private List<Tarea> lista = new ArrayList<>();
	
	/**
	 * Inicia la planificación de proceso seleccionada
	 */
	public void run() {
		tareasDefault();
		int opcion = 0;
		do {
			System.out.println("¿Qué sistema de planificación desea escoger?");
			System.out.println("1.- FIFO");
			System.out.println("2.- Shortest Job First");
			System.out.println("3.- Shortest Remaining Time");
			System.out.println("4.- Round Robin");
			System.out.println("0.- Salir");
			System.out.print("Opción: ");
			opcion = teclado.nextInt();
			switch(opcion) {
			case 1:
				fifo(lista);
				break;
			case 2:
				sjf(lista);
				break;
			case 3:
				srt(lista);
				break;
			case 4:
				rr(lista);
				break;
			case 0:
				System.out.println("Hasta la próxima ^^");
				break;
			}
		}while(opcion!=0);
	}
	
	
	/**
	 * Meto las tareas en una lista del objeto Tareas
	 */
	public void tareasDefault() {
		lista.add(new Tarea(0, 3));
		lista.add(new Tarea(2, 6));
		lista.add(new Tarea(4, 4));
		lista.add(new Tarea(6, 5));
		lista.add(new Tarea(8, 2));
	}
	
	/**
	 * Inicia la planificación de proceso <br>
	 * FIFO
	 */
	public void fifo(List<Tarea>lista) {
		System.out.println("-----------------------------FIFO-----------------------------");
		int orden = lista.get(2).getOrdenLlegada();
		int orden2 = lista.get(4).getOrdenLlegada();
		for(int i=0; i<lista.size();i++) {
			if(lista.get(i).getOrdenLlegada() < orden) {
				orden = lista.get(i).getOrdenLlegada();
			}
			if(lista.get(i).getOrdenLlegada() > orden && lista.get(i).getOrdenLlegada() < orden2) {
				orden2= lista.get(i).getOrdenLlegada();
			}
		}
	}
	
	/**
	 * Inicia la planificación de proceso <br>
	 * Shortest Job First
	 */
	public void sjf(List<Tarea>lista) {
		System.out.println("-----------------------------SJF------------------------------");
		
	}
	
	/**
	 * Inicia la planificación de proceso <br>
	 * Shortest Remaining Time
	 */
	public void srt(List<Tarea>lista) {
		System.out.println("-----------------------------SRT------------------------------");
		
	}
	
	/**
	 * Inicia la planificación de proceso <br>
	 * Round Robin
	 */
	public void rr(List<Tarea>lista) {
		System.out.println("-----------------------------RR-------------------------------");
		
	}
}
