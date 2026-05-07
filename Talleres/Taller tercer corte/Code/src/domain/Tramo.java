package domain;

import java.io.Serializable;

public class Tramo implements Serializable {
	
	private Estacion estacionFinal;
	private Estacion estacionInicial;
	private int distancia;
	
	public boolean tieneEstacion(String estA, String estB) {
		String nombreEstFinal = estacionFinal.obtenerNombre();
		String nombreEstInicial = estacionInicial.obtenerNombre();
		return (nombreEstFinal.equals(estA) && nombreEstInicial.equals(estB)) || (nombreEstFinal.equals(estB) && nombreEstInicial.equals(estA));
	}
	

}
