import java.time.LocalDateTime;

/**
 * Write a description of class Purchase here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Purchase {
    private int id;
    private LocalDateTime creationDate;
    private String status;
    
    public String getStatus() {
        return status;
    }
}