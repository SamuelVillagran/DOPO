package domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;


public class Sistema {

	private HashMap<String, Estacion> estaciones;
	private LinkedList<String> troncales;
	private TreeMap<String, Ruta> rutas;
	
	public TreeMap rutasOrdenAlfabetico() {
		return rutas;
	}
	
	public double tiempoDeEsperaRuta() {
		return 0.0;
	}
	
	public int numeroParadasEnRuta(String nombreRuta, String nombreEstacion1, String nombreEstacion2) {
		return rutas.size();
	}
	
	public TreeMap<String, TreeMap<Integer, Estacion>> nombreRutasSinTransbordos(String estacionInicio, String estacionFinal) {
		
		
		return null;
	}
}
