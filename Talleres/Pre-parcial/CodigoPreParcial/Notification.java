import java.time.LocalDateTime;

/**
 * Write a description of class Notification here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notification {
    private String message;
    private LocalDateTime creationDate;
    
    public Notification(String message) {
        this.message = message;
        creationDate = LocalDateTime.now();
    }
}