
/**
 * Write a description of class Review here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Review {
    private String comment;
    private int score;
    private User author;
    
    public int getScore() {
        return score;
    }
    
    public boolean isWriteByOwner(User user) {
        return author.equals(user);
    }
}