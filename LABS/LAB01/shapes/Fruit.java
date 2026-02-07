
/**
 * Write a description of class Fruit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Fruit
{
    private int xPsoition;
    private int yPosition;
    private Circle body;

    /**
     * Constructor for objects of class Fruit
     */
    public Fruit(){
        this.body = new Circle();
        body.changeSize(10);
        body.changeColor("red");
    }
    
    /**
     * Make visible the fruit
     */
    public void makeVisible(){
        body.makeVisible();
    }
    
    /**
     * Make invisble the fruit
     */
    public void makeInvisible(){
        body.makeInvisible();
    }
    
    public void setPosition(int xPos, int yPos) {
        body.setPosition(xPos, yPos);
    }
}