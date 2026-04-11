package domain;


/**
 * Fifa's exception class
 *
 * @author Sanchez-Villagran
 */
public class FifaException extends Exception{
    public static final String MINUTES_UNKNOWN = "There's not minutes known";
    public static final String IMPOSSIBLE = "Operation can't do it";
    public static final String VALUE_UNKNOWN = "Value market is not known";
    
    public FifaException(String message){
        super(message);
    }
}