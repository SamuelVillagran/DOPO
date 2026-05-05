package domain;

import java.util.LinkedList;

public class Ruta extends ElementoTransmi {

	private LinkedList<Estacion> paradas;
	
	public Ruta(String nombre) {
		this.nombre = nombre;
	}
	
	public int numeroParadas(String nombreEstacion1, String nombreEstacion2) {
		return 0;
		
	}
	
	public boolean tieneEstaciones(String estA, String estB) {
		return false;
	}
	
	public int calcularNumParadas(String estA, String estB) {
		return 0;
	}

	/**
	 * Agrega una nueva parada de la ruta 
	 * @param nombewNuevaParada nombreNuevaParada es el nombre de la nueva parada que se va a crear
	 * para esta ruta
	 */
	public void agregarParada(String nombreNuevaParada) {
		Estacion newEstacion = new Estacion(nombreNuevaParada);
		paradas.add(newEstacion);
	}
	
	
}
