package domain;


/**
 * Write a description of class FifaExceptions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FifaException extends Exception{
    public static final String MINUTES_UNKNOWN = "There's not minutes known";
    public static final String IMPOSSIBLE = "Operation can't do it";
    public static final String VALUE_UNKNOWN = "Value is not known";
    
    public FifaException(String message){
        super(message);
    }
}