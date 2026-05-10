package domain;

public class ForestException extends Exception{
	
	public static final String CORRUPT_FILE = "File not compatible, please check its contents.";
	public static final String FILE_NO_FOUND = "File does not exist, please verify its creation.";
	public static final String IO_ERROR = "Problem writing the game";
	public static final String FILE_NOT_FOUND = "The file was not found.";
	public static final String NOT_SERIALIZABLE = "A forest element cannot be serialized";
	public static final String IO_SAVE_ERROR = "Input or output error when saving the game";
	public final static String CANT_IMPORT_THAT = "At the selected file there are some thing that can't create, don't exists at this Forest";
	public final static String CANT_CREATE_FOREST = "Can't create a forest inside this forest, It's impossible";
	
	public ForestException(String message) {
		super(message);
	}
	
	public ForestException(String operation, String file) {
		super("Operación " + operation + " en construcción. Archivo " + file);
	}
}
