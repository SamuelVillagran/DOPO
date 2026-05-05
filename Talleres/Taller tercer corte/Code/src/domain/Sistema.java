package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
	public void importarNuevaRuta() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("ruta1.txt");
		if (is == null) {
			System.err.println("No se encuentra el arvhivo de la ruta en especifico");
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String linea = in.readLine();
		int numLine = 0;
		while (linea != null) {
			linea = linea.trim();
			Ruta newRuta = null;
			if (numLine == 0) {
				newRuta = new Ruta(linea);
				rutas.put(linea, newRuta);
			} else {
				newRuta.agregarParada(linea);
			}
			numLine++;
			linea = in.readLine();
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		Sistema sys = new Sistema();
		sys.importarNuevaRuta();
	}
}
