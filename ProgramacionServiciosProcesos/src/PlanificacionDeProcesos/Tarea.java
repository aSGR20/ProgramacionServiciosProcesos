package PlanificacionDeProcesos;

public class Tarea {
	String nombreTarea;
	int ordenLlegada; 
	int tiempoServicio;
	
	public Tarea(String nombreTarea, int ordenLlegada, int tiempoServicio) {
		super();
		this.nombreTarea = nombreTarea;
		this.ordenLlegada = ordenLlegada;
		this.tiempoServicio = tiempoServicio;
	}
	public Tarea(int ordenLlegada, int tiempoServicio) {
		super();
		this.ordenLlegada = ordenLlegada;
		this.tiempoServicio = tiempoServicio;
	}
	
	public String getNombreTarea() {
		return nombreTarea;
	}
	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}
	public int getOrdenLlegada() {
		return ordenLlegada;
	}
	public void setOrdenLlegada(int ordenLlegada) {
		this.ordenLlegada = ordenLlegada;
	}
	public int getTiempoServicio() {
		return tiempoServicio;
	}
	public void setTiempoServicio(int tiempoServicio) {
		this.tiempoServicio = tiempoServicio;
	}
	
}
