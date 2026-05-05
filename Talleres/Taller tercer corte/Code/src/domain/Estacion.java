package domain;

public class Estacion extends ElementoTransmi {

	private boolean nivelOcupacion;
	private double tiempoEspera;
	
	public Estacion(String nombre) {
		this.nombre = nombre;
		
	}

	public double calcularTiempoEspera() {
		
		return tiempoEspera;
		
	}
	
	
	
}
