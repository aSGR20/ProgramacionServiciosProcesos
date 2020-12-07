package PracticaResumen2;

import java.util.ArrayList;
import java.util.Collections;

public class RoundRobin {
	
	ArrayList<Procesos> process;
	ArrayList<String> listaMedia;
	int quantum;

	public RoundRobin(ArrayList<Procesos> procesos, int quantum) {
		this.process = procesos;
		this.quantum = quantum;
		listaMedia = new ArrayList();
	}
	
	public void ejecucion() {
		Procesos proceso;
		int reloj = 1;
		boolean fin = false;
		boolean cambio = false;
		while(!fin) {
			cambio = false;
			while(!cambio) {
				if(!process.isEmpty()) {
					if(process.get(0).getEntrada()<reloj) {
						for (int i = 0; i < quantum; i++) {
							if(process.get(0).getDuracion()!=0) {
								process.get(0).setDuracion(process.get(0).getDuracion()-1);
								System.out.println("Tiempo del sistema: "+ reloj);
								System.out.println(process.get(0).ToString());
								System.out.println("");
								if(process.get(0).getDuracion()==0) {
									process.get(0).setFin(reloj);
								}
								reloj++;
								if(i==1) {
									if(process.get(0).getDuracion()!=process.get(0).getInicio()) {
										proceso = crearProcesos(0);
										process.remove(0);
										process.add(proceso);
										cambio = true;
									}
								}
							}
						}
					}
				} else {
					cambio = true;
				}
				eliminar();
			}
			if(process.isEmpty()) {
				fin = true;
				System.out.println("Round Robin con Q = \""+ quantum +"\" finalizado.");
				media();
			}
		}
	}
	
	public void eliminar() {
		try {
			if(!process.isEmpty()) {
				for(int i = 0; i < process.size(); i++) {
					if( process.get(i).getDuracion()==0) {
						listaMedia.add(process.get(i).calcularMedia());
						process.remove(i);
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Round Robin finalizado");
		}
	}
	
	public Procesos crearProcesos(int numProceso) {
		String nombre = process.get(numProceso).getNombre();
		int inicio = process.get(numProceso).getInicio();
		int duracion = process.get(numProceso).getDuracion();
		Procesos proceso = process.get(numProceso);
		return proceso;
	}
	
	public void media() {
		System.out.println("Media de los procesos:");
		Collections.sort(listaMedia);
		for(int i = 0; i < listaMedia.size(); i++) {
			System.out.println(listaMedia.get(i).toString());
		}
	}
	
}
