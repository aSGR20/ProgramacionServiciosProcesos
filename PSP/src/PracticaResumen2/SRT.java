package PracticaResumen2;

import java.util.ArrayList;
import java.util.Collections;

public class SRT {

	ArrayList<Procesos> process;
	int reloj;
	ArrayList<String> listaMedia;
	ArrayList<Procesos> listaEjecuciones;
	
	public SRT(ArrayList<Procesos> procesos) {
		this.process = procesos;
		listaMedia = new ArrayList();
		listaEjecuciones = new ArrayList();
		reloj = 1;
	}
	
	public void ejecucion() {
		boolean termina = false;
		while(termina==false) {
			if(!process.isEmpty()) {
				anyadirLista();
			}
			for(int i = 0; i < listaEjecuciones.size(); i++) {
				while(listaEjecuciones.get(i).getDuracion()!=0) {
					listaEjecuciones.get(i).setDuracion(listaEjecuciones.get(i).getDuracion()-1);
					System.out.println("Tiempo del sistema: "+ reloj);
					System.out.println(listaEjecuciones.get(i).ToString());
					System.out.println("");
					if(listaEjecuciones.get(i).getDuracion()==0) {
						listaEjecuciones.get(i).setFin(reloj);
					}
					reloj++;
				}
			}
			eliminar();
			if(process.isEmpty() && listaEjecuciones.isEmpty()) {
				termina = true;
				media();
			}
		}
	}
	
	public void eliminar() {
		for(int i = 0; i < listaEjecuciones.size(); i++) {
			if(listaEjecuciones.get(i).getDuracion()==0) {
				listaMedia.add(listaEjecuciones.get(i).calcularMedia());
				listaEjecuciones.remove(i);
			}
		}
	}
	
	private void anyadirLista() {
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
