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
    public static final String PARTICIPANT_WITH_SAME_NAME = "Participant name is repeated, can't show at the screen";
    public static final String ATTRIBUTE_INTEGER_SETTING_INCORRECTLY = "Some integer attribute isn't setting correctly";
    public static final String ATTRIBUTE_STRING_CHAR_SETTING_INCORRECTLY = "Some String or char attribute isn't setting correctly";
    
    public FifaException(String message){
        super(message);
    }
}