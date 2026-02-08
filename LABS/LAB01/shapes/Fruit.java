
/**
 * Write a description of class Fruit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Fruit
{
    private int xPosition;
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
    
    /**
     * Get the position in coodinates of matrix[row, col]
     */
    public int[] getPosition(){
        return new int[]{yPosition / HungrySnakeGame.SQUARESIZE, xPosition / HungrySnakeGame.SQUARESIZE};
    }

    public void setPosition(int xPos, int yPos) {
        this.xPosition = xPos;
        this.yPosition = yPos;
        body.setPosition(xPos, yPos);
    }
}