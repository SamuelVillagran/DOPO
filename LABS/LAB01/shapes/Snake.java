import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This will be the player snake who going to move around board
 *
 * @author Sanchez-Villagran
 * @version 1.0
 */
public class Snake {
    private int row;
    private int col;
    private Rectangle head;
    private ArrayList<Rectangle> body;
    private ArrayList<int[]> positionsElements;
    private String color;
    private char direction;
    private boolean lastOk;
    private boolean isVisible;
    

    /**
     * Constructor for objects of class Snake
     */
    public Snake(int row, int col) {
        this.head = new Rectangle();
        this.body = new ArrayList<>();
        this.positionsElements = new ArrayList<>();
        this.color = "blue";
        this.direction = 'e';
        this.lastOk = true;
        
        // preparing elements
        this.head = new Rectangle();
        head.changeSize(10, 10);
        this.head.changeColor("blue");
        this.body.add(this.head);
        this.head.setPosition(col * HungrySnakeGame.SQUARESIZE, row * HungrySnakeGame.SQUARESIZE);
        this.positionsElements.add(new int[]{row, col});
        makeVisible();
    }
    
    /**
     * Get the head position
     * return an array with[row, col]
     */
    public int[] head(){
        return positionsElements.get(0);
    }
    
    /**
     * Get the tail position
     * return an array with[row, col]
     */
    public int[] tail(){
        return positionsElements.get(positionsElements.size() - 1);
    }
    
    /**
     * Make visisble the snake
     */
    public void makeVisible(){
        for (Rectangle element : body){
            element.makeVisible();
        }
        isVisible = true;
    }
    
    /**
     * Move the snake to a specific direction
     * @param  direction indicates north, west, south or east direction
     */
    public void move(char direction, HungrySnakeGame game){
        lastOk = false;
        if(!canMove(direction)){
            return;
        }
        this.direction = direction;
        
        // Change the head position
        int[] headPos = positionsElements.get(0);
        int[] newHead = getNewPositionHead(headPos, direction);
        
        if(colidesWithItself(newHead)){
            lastOk = false;
            game.gameOver();
            return;
        }
        
        positionsElements.add(0, newHead);
        positionsElements.remove(positionsElements.size() - 1);
        
        updatePositions();
        lastOk = true;
    }
    
    /**
     * Get the new position depending of the movement.
     * @return Array with [row, col].
     */
    private int[] getNewPositionHead(int[] headpos, char direction){
        int row = headpos[0];
        int col = headpos[1];
        
        switch(direction) {
            case 'n': row--; break;
            case 'w': col--; break;
            case 's': row++; break;
            case 'e': col++; break;
        }
        return new int[]{row, col};
    }
    
    /**
     * Checks if the snake can move, it can't move to the opossite direction
     * that actualliy it is
     * returns true or false
     */
    private boolean canMove(char direction){
        switch(direction){
            case 'e':
                return !(this.direction == 'w');
            case 'w':
                return !(this.direction == 'e');
            case 'n':
                return !(this.direction == 's');
            case 's':
                return !(this.direction == 'n');
            default: return false;
        }
    }
    
    /**
     * Move the snale in the given direction
     * @param direcion could take values like nort, west, south, east
     */
    public void grow(char direction){
        if(!canMove(direction)){
            lastOk = false;
            return;
        }
        this.direction = direction;
        
        // Change the head position
        int[] headPos = positionsElements.get(0);
        int[] newHead = getNewPositionHead(headPos, direction);
        
        positionsElements.add(0, newHead);
        Rectangle newRectHead = new Rectangle();
        newRectHead.changeSize(10, 10);
        newRectHead.changeColor("blue");
        body.add(0, newRectHead);
        
        updatePositions();
        lastOk = true;
    }
    
    /**
     * Update the body's snake positions
     */
    private void updatePositions(){
        for(int i = 0; i < body.size(); i++){
            int[] pos = positionsElements.get(i);
            int newX = pos[1] * HungrySnakeGame.SQUARESIZE;
            int newY = pos[0] * HungrySnakeGame.SQUARESIZE;
            body.get(i).setPosition(newX, newY);
        }
         if(isVisible){
            makeVisible();
        }
    }
    
    /**
     * Check the contact with itself
     */
    public boolean colidesWithItself(int[] pos){
        for(int[] positions: positionsElements){
            if( pos[0] == positions[0] && pos[1] == positions[1]){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Return if the last movement was valid
     */
    public boolean isOk(){ 
        // Se verifica cuando se haga el tablero
        return lastOk;
    }
    
    /**
     * Return the length of the snake
     */
    public int length(){
        return positionsElements.size();
    }
    
    /**
     * Make visible the snake
     */
    public void makeInvisible(){
        head.makeInvisible();
        for(Rectangle segment : body){
            segment.makeInvisible();
        }
        isVisible = false;;
    }
    
    /**
     * Give snake's length
     */
    public int getSize() {
        return body.size();
    }
    
    /**
     * This method give the snake's body
     */
    public ArrayList<Rectangle> getBody() {
        return body;
    }
}
