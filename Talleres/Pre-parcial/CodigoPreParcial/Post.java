import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Write a description of class Post here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Post implements Trustable {
    private String id;
    private LocalDateTime creationDate;
    private String status;
    private String name;
    private Product offeredProduct; 
    private ArrayList<Review> reviews;
    private User owner;
    
    public boolean isReliable() {
        double averageReviewScore = 0.0;
        for (Review r : reviews) {
            if (r.isWriteByOwner(owner)) return false;
            averageReviewScore += r.getScore();
        }
        averageReviewScore /= reviews.size();
        int stockProduct = offeredProduct.getStock();
        return (status.equals("activo") && stockProduct == 0) || averageReviewScore <= 2.0;
    }
}