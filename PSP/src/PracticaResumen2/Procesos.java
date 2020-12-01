package PracticaResumen2;

public class Procesos {
	
	protected String nombre;
	protected int entrada;
	protected int duracion;
	protected int fin;
	protected int inicio;
	
	public Procesos(String nombre, int entrada, int duracion) {
		this.nombre = nombre;
		this.entrada = entrada;
		this.duracion = duracion;
		inicio = duracion;
		fin = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEntrada() {
		return entrada;
	}

	public void setEntrada(int entrada) {
		this.entrada = entrada;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	
	public String calcularMedia() {
		float resta = fin - entrada;
		float media = resta / inicio;
		//float media = (fin - inicio) / inicio;
		return ("\t La media de "+ nombre + " es de: "+ String.valueOf(media));
	}
	
	public String ToString() {
		if(duracion!=0) {
			return "El proceso "+ nombre +" le queda "+ duracion;
		}else {
			return"El proceso "+ nombre +" ha finalizado";
		}
	}
	
}
