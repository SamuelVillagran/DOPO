package domain;


import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyCollection { // Clase ayudada a hacer por Gemini IA 2026

	private TreeMap<Integer, TreeSet<String>> collection;
	
	public MyCollection() {
		this.collection = new TreeMap<>();
	}

	public void agregarRuta(int numParadas, String nombreRuta) {
		if (!collection.containsKey(numParadas)) {
			collection.put(numParadas, new TreeSet<>());
		}
		collection.get(numParadas).add(nombreRuta);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--Colección personalizada \n");
		for (Entry<Integer, TreeSet<String>> entrada : collection.entrySet()) {
			sb.append("Número de parada: ").append(entrada.getKey())
			.append("Rutas con ese número de paradas: ").append(entrada.getValue()+"\n");
		}
		return sb.toString();
	}
	
	public Set<String> nombreRutasSinTransbordo() {
		Set<String> resultado = new TreeSet();
		for (Map.Entry<Integer, TreeSet<String>> entrada : collection.entrySet()) {
			resultado.addAll(entrada.getValue());
		}
		return resultado;
	}
	
}
