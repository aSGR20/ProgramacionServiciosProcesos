package PracticaResumen2;

import java.util.ArrayList;
import java.util.Collections;

public class Fifo {
	
	ArrayList<Procesos> process;
	ArrayList<String> listaMedia;

	/**
	 * Inicializa los procesos, una ArrayList de procesos <br>
	 * y una ArrayList de String.
	 * @param procesos
	 */
	public Fifo(ArrayList<Procesos> procesos) {
		this.process = procesos;
		listaMedia = new ArrayList();
	}
	
	/**
	 * Ejecuta los procesos en un orden y en un timing.
	 */
	public void ejecucion() {
		int reloj = 1;
		while (!(process.isEmpty())) {
			for(int i = 0; i < process.size(); i++) {
				if(process.get(i).getEntrada()+1<=reloj) {
				//if(reloj <= process.get(i).getInicio()+1) {
					while(process.get(i).getDuracion()!=0) {
						process.get(i).setDuracion(process.get(i).getDuracion()-1);
						System.out.println("Tiempo del sistema: "+ reloj);
						System.out.println(process.get(i).ToString());
						System.out.println("");
						if(process.get(i).getDuracion()==0) {
							process.get(i).setFin(reloj);
						}
						reloj++;
					}
				}
			}
			if(!(process.isEmpty())) {
				for (int i = 0; i < process.size(); i++) {
					if(process.get(i).getDuracion()==0) {
						String media = process.get(i).calcularMedia();
						listaMedia.add(media);
						process.remove(i);
					}
				}
			}
		}
		if(process.isEmpty()) {
			media();
			System.out.println("---------------------------- FIFO TERMINADO ----------------------------");
		}
	}
	
	/**
	 * Calcula la media de los procesos y te lo muestras por consola.
	 */
	public void media() {
		System.out.println("Media de los procesos:");
		Collections.sort(listaMedia);
		for(int i = 0; i < listaMedia.size(); i++) {
			System.out.println(listaMedia.get(i).toString());
		}
	}
}
