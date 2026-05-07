package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Troncal extends ElementoTransmi implements Serializable {

	
	private int velocidadPromedio;
	private HashMap<Integer, Tramo> tramos; //
	private LinkedList<Estacion> paradas;

	public Troncal(String string) {
		nombre = string;
	}

	/**
	 * Verifica si las estaciones dadas se encuentran en las troncales
	 * @param estacionInicio estacionInicio es una de los dos nombre de estaciones a buscar en troncales
	 * @param estacionFinal estacionFinal es uno de los dos nombre de estaciones a buscar en troncales
	 * @return retorna si la estacion de inicio y final se encuentra en las troncales especificas
	 * @throws SistemaExcepcion NO_SE_ENCUENTRA_ESTACION_EN_TRONCAL - Esto se activa cuando no se cumple que las dos estaciones se encuentra en la troncal 
	 */
	/*
	public boolean tieneEstacion(String estacionInicio, String estacionFinal) throws SistemaExcepcion {
		boolean estacionInicioEncontrada = false, estacionFinalEncontrada = false;
		String nombreEstacion;
		boolean resultado = false;
		for (Estacion e : paradas) {
			nombreEstacion = e.obtenerNombre();
			resultado = estacionInicioEncontrada && estacionFinalEncontrada;
			if (nombreEstacion.equals(estacionInicio) && !estacionInicioEncontrada) {
				estacionInicioEncontrada = true;
			}
			if (nombreEstacion.equals(estacionFinal) && !estacionFinalEncontrada) {
				estacionFinalEncontrada = true;
			}
			if (resultado) break;
		}
		if (!resultado) throw new SistemaExcepcion(SistemaExcepcion.NO_SE_ENCUENTRA_ESTACION_EN_RUTAS);
		return resultado;
	}
	*/
	
	
}
