package domain;

public class ForestException extends Exception{
	
	public static final String CORRUPT_FILE = "File not compatible, please check its contents.";
	public static final String FILE_NO_FOUND = "File does not exist, please verify its creation.";
	public static final String IO_ERROR = "Problem writing the game";
	public static final String FILE_NOT_FOUND = "The file was not found.";
	public static final String NOT_SERIALIZABLE = "A forest element cannot be serialized";
	public static final String IO_SAVE_ERROR = "Input or output error when saving the game";
	
	public ForestException(String message) {
		super(message);
	}
	
	public ForestException(String operation, String file) {
		super("Operación " + operation + " en construcción. Archivo " + file);
	}
}
