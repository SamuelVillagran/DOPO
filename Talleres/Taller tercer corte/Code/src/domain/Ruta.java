package domain;

import java.util.LinkedList;
import java.util.TreeMap;

public class Ruta extends ElementoTransmi {

	private LinkedList<Estacion> paradas;
	
	/**
	 * Constructor de ruta
	 * @param nombre nombre es el nombre especifico de la ruta
	 */
	public Ruta(String nombre) {
		this.nombre = nombre;
		paradas = new LinkedList<>();
	}
	
	public int numeroParadas(String nombreEstacion1, String nombreEstacion2) {
		return 0;
	}
	
	/**
	 * Verifica si en esta ruta se tienen aquellas estaciones respectivas
	 * @param estA estA es el nombre de la estacion dada
	 * @param estB estB es el nombre de otra estacion dada
	 * @return retorna true si las dos estaciones se encuentran en esta ruta, false sino
	 * @throws SistemaExcepcion NO_SE_ENCUENTRA_ESTACION_EN_RUTAS - Se lanza si no se encuentra las estaciones en esta ruta
	 */
	public boolean tieneEstaciones(String estA, String estB) throws SistemaExcepcion {
		boolean resultado = false;
		boolean isEstA = false, isEstB = false;
		for (Estacion e : paradas) {
			String nombreEstacion = e.obtenerNombre();
			if (nombreEstacion.equals(estA)) isEstA = true;
			if (nombreEstacion.equals(estB)) isEstB = true;
		}
		resultado = isEstA && isEstB;
		if (!resultado) throw new SistemaExcepcion(SistemaExcepcion.NO_SE_ENCUENTRA_ESTACION_EN_RUTAS);
		return resultado; 
	}
	
	/**
	 * Calcula el numero de paradas de una estacion a otra
	 * @param estA estA es la estacion de inicio desde donde se va a contar
	 * @param estB estB es la estacion final hasta donde se va a contar
	 * @return retorna el numero de paradas de la estacion de inicio a la estacion final
	 */
	public int calcularNumParadas(String estA, String estB) {
		int numParadas = 0;
		boolean empezarCuenta = false;
		for (Estacion p : paradas) {
			String nombreActualEstacion = p.obtenerNombre();
			if (nombreActualEstacion.equals(estA)) {
				empezarCuenta = true;
			}
			if (empezarCuenta) numParadas++; 
			if (empezarCuenta && numParadas > 0 && nombreActualEstacion.equals(estB)) {
				empezarCuenta = false;
				break;
			}
		}
		return numParadas;
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

	public void imprimirParadas() {
		for (Estacion est : paradas) {
			System.out.println(est.obtenerNombre());
		}
	}
	
}
