package domain;

public class ForestException extends Exception{
	
	public ForestException(String message) {
		super(message);
	}
	
	public ForestException(String operation, String file) {
		super("Operación " + operation + " en construcción. Archivo " + file);
	}
}
