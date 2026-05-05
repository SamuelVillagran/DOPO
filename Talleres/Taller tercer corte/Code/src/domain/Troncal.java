package domain;

import java.util.LinkedList;
import java.util.TreeMap;

public class Troncal extends ElementoTransmi {

	private int velocidadPromedio;
	private TreeMap<String, Ruta> rutas; //
	private LinkedList<Estacion> paradas;
	
	public TreeMap<String, Ruta> RutasOrdenAlfabetico() {
		return rutas;
	}
	
}
