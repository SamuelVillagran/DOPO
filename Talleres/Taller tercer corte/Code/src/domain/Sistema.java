package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Sistema {

	private HashMap<String, Estacion> estaciones;
	private LinkedList<Troncal> troncales;
	private TreeMap<String, Ruta> rutas;
	
	public Sistema() {
		estaciones = new HashMap<>();
		troncales = new LinkedList<>();
		rutas = new TreeMap<>();
	}
	
	
	public TreeMap rutasOrdenAlfabetico() {
		return rutas;
	}
	
	public double tiempoDeEsperaRuta() {
		return 0.0;
	}
	
	public int numeroParadasEnRuta(String nombreRuta, String nombreEstacion1, String nombreEstacion2) {
		return rutas.size();
	}
	
	/**
	 * Busca el nombre de rutas en los que estan en las estaciones dadas
	 * @param estacionInicio estacionInicio es la estacion en que se inicializa en la busqueda en las rutas
	 * @param estacionFinal estacionFinal es la estacion en que se finaliza en la busqueda en las rutas
	 * @return returna un TreeMap con su llave como el numero de paradas que se tienen y como valor un TreeSet que tiene
	 * el nombre de las rutas con ese respectivo numero de paradas
	 * @throws SistemaExcepcion NO_SE_ENCUENTRA_ESTACION_EN_RUTAS - Se lanza si no se encuentra las estaciones en alguna ruta ruta
	 */
	public MyCollection nombreRutasSinTransbordos(String estacionInicio, String estacionFinal) throws SistemaExcepcion {
		MyCollection nombreRutas = new MyCollection();
		
		for (Ruta r : rutas.values()) {
			boolean estanEnRutas = r.tieneEstaciones(estacionInicio, estacionFinal);
			if (estanEnRutas) {
				String nombreRuta = r.obtenerNombre();
				int numParadas = r.calcularNumParadas(estacionInicio, estacionFinal);
				nombreRutas.addRuta(nombreRuta, numParadas);
			}
		}
		return nombreRutas;
	}
	
	/**
	 * Importa una ruta desde un archivo de texto
	 * Lee linea por linea donde la primera linea del archivo es el nombre de la ruta
	 * Y las siguientes lineas son el nombre de las paradas de aquella ruta, además
	 * que cada ruta con sus paradas están separadas por un espacio en blanco (ENTER)
	 * @throws IOException
	 */
	public void importarNuevaRuta() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("ruta1.txt");
		if (is == null) {
			System.err.println("No se encuentra el arvhivo de la ruta en especifico");
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String linea = in.readLine();
		int numLine = 0;
		Ruta newRuta = null;
		while (linea != null) {
			linea = linea.trim();
			if (numLine == 0) {
				newRuta = new Ruta(linea);
				rutas.put(linea, newRuta);
			} else {
				newRuta.agregarParada(linea);
			}
			numLine++;
			
			if (in == null) {
				linea = in.readLine();
			}
			linea = in.readLine();
		}
	}
	
	public void imprimirResultadosRuta() {
		String nombreRuta;
		Ruta ruta;
		for (Entry<String, Ruta> entry : rutas.entrySet()) {
			nombreRuta = entry.getKey();
			ruta = entry.getValue();
			System.out.println("nombre ruta: " + nombreRuta + " ruta: "+ ruta);
			ruta.imprimirParadas();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Sistema sys = new Sistema();
		sys.importarNuevaRuta();
		sys.imprimirResultadosRuta();
	}
}
