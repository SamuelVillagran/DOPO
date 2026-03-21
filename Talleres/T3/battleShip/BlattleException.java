
/**
 * Write a description of class BlattleException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlattleException extends Exception {
    public static final String DONTENOUGHTPOWER = "Fleet doesn't has enought power";
    
    private String message;
    /**
     * Constructor for objects of class BlattleException
     */
    public BlattleException(String message) {
        this.message = message;
    }

    
}