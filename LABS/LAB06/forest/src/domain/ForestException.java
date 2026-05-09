package domain;

public class ForestException extends Exception{
	
	public final static String CANT_IMPORT_THAT = "At the selected file there are some thing that can't create, don't exists at this Forest";
	public final static String CANT_CREATE_FOREST = "Can't create a forest inside this forest, It's impossible";
	
	public ForestException(String message) {
		super(message);
	}
	
	public ForestException(String operation, String file) {
		super("Operación " + operation + " en construcción. Archivo " + file);
	}
}
