import java.util.ArrayList;

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
    private String direction;
    private boolean lastOk;
    private boolean isVisible;
    

    /**
     * Constructor for objects of class Snake
     */
    public Snake(int row, int col)
    {
        this.head = new Rectangle();
        this.body = new ArrayList<>();
        this.positionsElements = new ArrayList<>();
        this.color = "blue";
        this.direction = "east";
        this.lastOk = true;
        
        // preparin elements
        this.head = new Rectangle(30);
        this.head.changeColor("blue");
        this.body.add(this.head);
        this.head.setPosition(col * HungrySnakeGame.squareSize, row * HungrySnakeGame.squareSize);
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
    public void move(String direction){
        lastOk = false;
        if(!canMove(direction)){
            return;
        }
        this.direction = direction;
        
        // Change the head position
        int[] headPos = positionsElements.get(0);
        int ro = headPos[0];
        int co = headPos[1];
        
        int[] newHead;
        switch(direction){
            case "north":
                newHead = new int[]{ro - 1,co};
                break;
            case "east":
                newHead = new int[]{ro, co + 1};
                break;
            case "west":
                newHead = new int[]{ro, co - 1};
                break;
            case "south":
                newHead = new int[]{ro + 1, co};
                break;
            default: return;
        }
        
        positionsElements.add(0, newHead);
        positionsElements.remove(positionsElements.size() - 1);
        
        // Actualizar posiciones del cuerpo
        for(int i = 0; i < body.size(); i++){
            int[] pos = positionsElements.get(i);
            int newX = pos[1] * HungrySnakeGame.squareSize;
            int newY = pos[0] * HungrySnakeGame.squareSize;
            body.get(i).setPosition(newX, newY);
        }
        lastOk = true;
    }
    
    /**
     * Checks if the snake can move, it can't move to the opossite direction
     * that actualliy it is
     * returns true or false
     */
    private boolean canMove(String direction){
        switch(direction){
            case "east":
                return !this.direction.equals("west");
            case "west":
                return !this.direction.equals("east");
            case "north":
                return !this.direction.equals("south");
            case "south":
                return !this.direction.equals("north");
            default: return false;
        }
    }
    
    /**
     * Move the snale in the given direction
     * @param direcion could take values like nort, west, south, east
     */
    public void grow(String direction){
        if(!canMove(direction)){
            lastOk = false;
            return;
        }
        this.direction = direction;
        
        // Change the head position
        int[] headPos = positionsElements.get(0);
        int ro = headPos[0];
        int co = headPos[1];
        
        int[] newHead;
        switch(direction){
            case "north":
                newHead = new int[]{ro - 1,co};
                break;
            case "east":
                newHead = new int[]{ro, co + 1};
                break;
            case "west":
                newHead = new int[]{ro, co - 1};
                break;
            case "south":
                newHead = new int[]{ro + 1, co};
                break;
            default: return;
        }
        
        positionsElements.add(0, newHead);
        Rectangle newRectHead = new Rectangle(30);
        newRectHead.changeColor("blue");
        body.add(0, newRectHead);
        
        // Actualizar posiciones del cuerpo
        for(int i = 0; i < body.size(); i++){
            int[] pos = positionsElements.get(i);
            int newX = pos[1] * HungrySnakeGame.squareSize;
            int newY = pos[0] * HungrySnakeGame.squareSize;
            body.get(i).setPosition(newX, newY);
        }
        lastOk = true;
        if(isVisible){
            makeVisible();
        }
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
}
