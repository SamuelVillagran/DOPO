
/**
 * Write a description of class Obtacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Obstacle
{
    // instance variables - replace the example below with your own
    private Rectangle square;

    /**
     * Constructor for objects of class Obtacle
     */
    public Obstacle() {
        square = new Rectangle();
        square.changeSize(10, 10);
        square.changeColor("black");
        square.makeVisible();
    }

    public void setPosition(int xPos, int yPos) {
        square.setPosition(xPos, yPos);
    }
}