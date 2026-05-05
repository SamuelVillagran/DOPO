package domain;

public class Estacion extends ElementoTransmi {

	private boolean nivelOcupacion;
	private double tiempoEspera;
	
	/**
	 * Constructor de estacion
	 * @param nombre nombre es el nombre especifico de la estacion
	 */
	public Estacion(String nombre) {
		this.nombre = nombre;
		
	}

	public double calcularTiempoEspera() {
		
		return tiempoEspera;
		
	}
	
	
	
}
