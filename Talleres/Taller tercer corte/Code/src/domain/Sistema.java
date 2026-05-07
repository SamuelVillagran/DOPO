package domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;


public class Sistema implements Serializable {

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
	 * Busca el nombre de rutas en los que estan en las estaciones dadas, con una coleccion el cual tiene como llave principal
	 * el numero de paradas y su valor como un conjunto de los nombres de paradas que tienen ese numero de paradas
	 * que cumplen con las estaciones dadas
	 * @param estacionInicio estacionInicio es la estacion en que se inicializa en la busqueda en las rutas
	 * @param estacionFinal estacionFinal es la estacion en que se finaliza en la busqueda en las rutas
	 * @return returna un TreeMap con su llave como el numero de paradas que se tienen y como valor un TreeSet que tiene
	 * el nombre de las rutas con ese respectivo numero de paradas
	 * @throws SistemaExcepcion NO_SE_ENCUENTRA_ESTACION_EN_RUTAS - Se lanza si no se encuentra las estaciones en alguna ruta ruta
	 */
	public MyCollection nombreRutasSinTransbordos(String estacionInicio, String estacionFinal) throws SistemaExcepcion {
		MyCollection nombreRutas = new MyCollection();
		for (Ruta r : rutas.values()) {
			try {
			boolean estanEnRutas = r.tieneEstaciones(estacionInicio, estacionFinal);
			if (estanEnRutas) {
				String nombreRuta = r.obtenerNombre();
				int numParadas = r.calcularNumParadas(estacionInicio, estacionFinal);
				nombreRutas.agregarRuta(numParadas, nombreRuta);
			} } catch (SistemaExcepcion e) {
			}
		}
		return nombreRutas;
	}
	
	/**
	 * Permite hacer un informe en el archiv informe.txt que esta en la carpeta src
	 * @param estacionInicio estacionInicio es la estacion donde se va a verificar el inicio de las rutas sin transbordo
	 * @param estacionFinal estacionFinal es la estacion donde se va a verificar el final de las rutas sin transbordo
	 * @throws SistemaExcepcion 
	 * @throws IOException
	 */
	public void exportarInformeRutasSinTransbordo(String estacionInicio, String estacionFinal) throws SistemaExcepcion, IOException {
		MyCollection firstRes = nombreRutasSinTransbordos(estacionInicio, estacionFinal);
		TreeSet<String> resultIterable = (TreeSet<String>) firstRes.nombreRutasSinTransbordo();
		FileWriter fw = new FileWriter("informe.txt");
		BufferedWriter out = new BufferedWriter(fw);
		out.write("Informe de rutas sin transbodo");
		out.newLine();
		out.write("______________________________");
		out.newLine();
		out.write("De: " + estacionInicio + " -> Hasta: " + estacionFinal);
		out.newLine();
		out.newLine();
		int contador = 1;
		for (String nombreRuta : resultIterable) {
			out.write(nombreRuta+" | ");
			if (contador % 3 == 0) {
				out.write("\n_______________________________");
				out.newLine();
			}
			contador++;
		}
		out.close();
	}
	
	/**
	 * Importa una ruta desde un archivo de texto (se va a agregar al sistema lo que este en el archivo ruta1.txt)
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
			boolean esLineaVacia = linea.isEmpty();
			if (esLineaVacia) {
				numLine = 0;
			} else {
				if (numLine == 0) {
					newRuta = new Ruta(linea);
					rutas.put(linea, newRuta);
				} else {
					newRuta.agregarParada(linea);
				}
				numLine++;
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
			System.out.println(rutas.size());
		}
		
	}
	
	public void agregarRuta(String nombreRuta, String... paradas) {
	    Ruta r = new Ruta(nombreRuta);
	    for (String p : paradas) r.agregarParada(p);
	    rutas.put(nombreRuta, r);
	}
	
	/**
	 * Guarda una troncal respectiva en un archivo .dat 
	 * @param nombreTroncal nombreTroncal es el nombre de la troncal que se va a guardar
	 */
	public void guardarTroncal(String nombreTroncal) {
		String nomTroncal;
		Troncal troncal = null;
		for (Troncal t : troncales) {
			nomTroncal = t.obtenerNombre();
			if (nomTroncal.equals(nombreTroncal)) {
				troncal = t;
				break;
			}
		}
		if (troncal != null) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("GuardaTroncal.dat"));
				oos.writeObject(troncal);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void agregarTroncal(Troncal t) {
	    troncales.add(t);
	}
	
	public static void main(String[] args) throws IOException {
		Sistema sys = new Sistema();
		sys.importarNuevaRuta();
		sys.imprimirResultadosRuta(); 
		/*
		Sistema sys = new Sistema();

	    // Rutas que pasan por Portal_Norte -> Calle_72
	    sys.agregarRuta("Ruta-A", "Portal_Norte", "Calle_100", "Calle_92", "Calle_80", "Calle_72", "Calle_63");
	    sys.agregarRuta("Ruta-B", "Portal_Norte", "Calle_85", "Calle_72", "Calle_45");
	    sys.agregarRuta("Ruta-C", "Portal_Norte", "Calle_100", "Calle_72", "Av_Jimenez");
	    sys.agregarRuta("Ruta-D", "Portal_Norte", "Calle_72", "Calle_26");

	    // Rutas que NO tienen ambas estaciones (para probar la excepcion)
	    sys.agregarRuta("Ruta-E", "Portal_Sur", "NQS", "Calle_26", "Av_Jimenez");
	    sys.agregarRuta("Ruta-F", "Portal_80", "Calle_63", "Calle_45", "Av_Jimenez");
	    sys.agregarRuta("Ruta-G", "Portal_Usme", "Calle_45", "Calle_26", "Av_Jimenez");
	    sys.agregarRuta("Ruta-H", "Portal_Norte", "Calle_100", "Calle_92", "Calle_80", "Calle_72");

	    System.out.println("=== Rutas cargadas ===");
	    sys.imprimirResultadosRuta();

	    System.out.println("\n=== Exportando informe ===");
	    try {
	        sys.exportarInformeRutasSinTransbordo("Portal_Norte", "Calle_72");
	    } catch (SistemaExcepcion e) {
	        System.err.println("SistemaExcepcion: " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("IOException: " + e.getMessage());
	        e.printStackTrace();
	    }
	    *//*
		Sistema sys = new Sistema();

	    // Crear y agregar troncal de prueba
	    Troncal t1 = new Troncal("Troncal-Norte");
	    sys.agregarTroncal(t1);

	    // Guardar troncal existente
	    System.out.println("Guardando Troncal-Norte...");
	    sys.guardarTroncal("Troncal-Norte");
	    System.out.println("Guardado exitoso. Revisa GuardaTroncal.dat");

	    // Intentar guardar una que no existe (no debe hacer nada)
	    System.out.println("Intentando guardar troncal inexistente...");
	    sys.guardarTroncal("Troncal-Sur");
	    System.out.println("Fin (no debio guardar nada)");
		*/
	}
}
