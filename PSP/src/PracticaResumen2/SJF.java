package PracticaResumen2;

import java.util.ArrayList;
import java.util.Collections;

public class SJF {
	ArrayList<Procesos> process;
	ArrayList<Procesos> listaEjecuciones;
	ArrayList<String> listaMedia;
	int reloj;
	
	/**
	 * Inicializa los procesos, una ArrayList de procesos, <br>
	 * una ArrayList de String y el tick del tiempo.
	 * @param procesos
	 */
	public SJF(ArrayList<Procesos> procesos) {
		this.process = procesos;
		listaMedia = new ArrayList();
		listaEjecuciones = new ArrayList();
		reloj = 1;
	}
	
	/**
	 * Ejecuta los procesos en el orden del SJF
	 */
	public void ejecucion() {
		boolean termina = false;
		boolean cambia = false;
		Procesos pequenyo = null;
		while(termina == false) {
			addMenor();
			cambia = false;
			for(int i = 0; i < listaEjecuciones.size(); i++) {
				while(cambia == false) {
					if(listaEjecuciones.get(i).getDuracion()!=0) {
						pequenyo = menor();
						if(pequenyo!=null) {
							if(listaEjecuciones.get(i).getDuracion()<=pequenyo.getDuracion()) {
								listaEjecuciones.get(i).setDuracion(listaEjecuciones.get(i).getDuracion()-1);
								System.out.println("Tiempo del sistema: "+ reloj);
								System.out.println(listaEjecuciones.get(i).ToString());
								System.out.println("");
								reloj++;
								if(listaEjecuciones.get(i).getDuracion()==0) {
									listaEjecuciones.get(i).setFin(reloj);
								}
							}else {
								process.add(listaEjecuciones.get(i));
								listaEjecuciones.remove(listaEjecuciones.get(i));
								cambia = true;
							}
						}else {
							listaEjecuciones.get(i).setDuracion(listaEjecuciones.get(i).getDuracion()-1);
							System.out.println("Tiempo del sistema: "+ reloj);
							System.out.println(listaEjecuciones.get(i).ToString());
							System.out.println("");
							if(listaEjecuciones.get(i).getDuracion()==0) {
								listaEjecuciones.get(i).setFin(reloj);
							}
							reloj++;
						}
						if(!listaEjecuciones.isEmpty()) {
							if(listaEjecuciones.get(i).getDuracion()==0) {
								cambia = true;
							}
						}
						eliminar();
					}
				}
				if(process.isEmpty() && listaEjecuciones.isEmpty()) {
					termina = true;
					media();
				}
			}
		}
	}
	
	/**
	 * Elimina el proceso ya finalizado.
	 */
	public void eliminar() {
		for(int i = 0; i < listaEjecuciones.size(); i++) {
			if(listaEjecuciones.get(i).getDuracion()==0) {
				listaMedia.add(listaEjecuciones.get(i).calcularMedia());
				listaEjecuciones.remove(i);
			}
		}
	}
	
	/**
	 * Si la lista de procesos está vacia añade el proceso mas pequeño.
	 */
	public void addMenor() {
		if(!process.isEmpty()) {
			addLista();
		}
	}
	
	/**
	 * Añade en la lista el proceso mas pequeño.
	 */
	public void addLista() {
		Procesos elMenor;
		elMenor = menor();
		listaEjecuciones.add(elMenor);
		if(!process.isEmpty()) {
			for(int i = 0; i < process.size(); i++) {
				if(process.get(i).getNombre() == elMenor.getNombre()) {
					process.remove(i);
				}
			}
		}
	}
	
	/**
	 * Devuelve el proceso mas pequeño.
	 * @return
	 */
	public Procesos menor() {
		int contador;
		Procesos pequenyo = null;
		Procesos proceso1 = null;
		Procesos proceso2 = null;
		for (int i = 0; i < process.size(); i++) {
			contador = i + 1;
			if(process.get(i).getEntrada()+1<=reloj) {
				proceso1 = crearProcesos(i);
			}
			if(contador<process.size()) {
				if(process.get(contador).getEntrada()+1<=reloj) {
					proceso2 = crearProcesos(i);
				}
			}
			if (proceso1!=null && proceso2!=null) {
				if(pequenyo==null) {
					if(proceso1.getDuracion()<proceso2.getDuracion()) {
						pequenyo = proceso1;
					}else {
						pequenyo = proceso2;
					}
				}else {
					if(proceso1.getDuracion()<pequenyo.getDuracion()) {
						pequenyo = proceso1;
					}
				}
			}else {
				pequenyo = proceso1;
			}
		}
		return pequenyo;
	}
	
	/**
	 * Guarda las variables del proceso pasado y devuelve el proceso.
	 * @param numProceso
	 * @return
	 */
	public Procesos crearProcesos(int numProceso) {
		String nombre = process.get(numProceso).getNombre();
		int inicio = process.get(numProceso).getInicio();
		int duracion = process.get(numProceso).getDuracion();
		Procesos proceso = process.get(numProceso);
		return proceso;
	}
	
	/**
	 * Calcula la media de la ejecución de los procesos y la devuelve por consola.
	 */
	public void media() {
		System.out.println("Media de los procesos:");
		Collections.sort(listaMedia);
		for(int i = 0; i < listaMedia.size(); i++) {
			System.out.println(listaMedia.get(i).toString());
		}
	}
	
}
