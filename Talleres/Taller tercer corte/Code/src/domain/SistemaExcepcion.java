package domain;

public class SistemaExcepcion extends Exception {

	public final static String NO_SE_ENCUENTRA_ESTACION_EN_RUTAS = "Las estaciones dadas no se encuentran en la ruta";
	
	public SistemaExcepcion(String message) {
		super(message);
	}
	
}
