package domain;

import java.io.Serializable;

public class Estacion extends ElementoTransmi implements Serializable {

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
