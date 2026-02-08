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
    private static final int CELL_SIZE = 10;
    private Circle leftEye;
    private Circle rightEye;
    private Rectangle tongue;
    

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
        head.changeSize(CELL_SIZE, CELL_SIZE);
        this.head.changeColor("blue");
        this.body.add(this.head);
        this.head.setPosition(col * HungrySnakeGame.squareSize, row * HungrySnakeGame.squareSize);
        this.positionsElements.add(new int[]{row, col});
        // setting eyes
        leftEye = new Circle();
        leftEye.changeSize(2);
        leftEye.changeColor("white");
        rightEye = new Circle();
        rightEye.changeSize(2);
        rightEye.changeColor("white");
        tongue = new Rectangle();
        tongue.changeSize(5, 2);
        tongue.changeColor("red");
        setupFace(direction);
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
        rightEye.makeVisible();
        leftEye.makeVisible();
        tongue.makeVisible();
        isVisible = true;
    }
    
    /**
     * Move the snake to a specific direction
     * @param  direction indicates north, west, south or east direction
     */
    public void move(char direction){
        lastOk = false;
        if(!canMove(direction)){
            JOptionPane.showMessageDialog(null, "Incorrect movement, please try with other!");
            return;
        }
        this.direction = direction;
        
        // Change the head position
        int[] headPos = positionsElements.get(0);
        int[] newHead = getNewPositionHead(headPos, direction);
        
        if(colidesWithItself(newHead)){
            lastOk = false;
            JOptionPane.showMessageDialog(null, "Snake ha muerto :(");
            return;
        }
        
        positionsElements.add(0, newHead);
        positionsElements.remove(positionsElements.size() - 1);
        updatePositions();
        setupFace(direction);
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
        setupFace(direction);
        lastOk = true;
    }
    
    /**
     * Update the body's snake positions
     */
    private void updatePositions(){
        for(int i = 0; i < body.size(); i++){
            int[] pos = positionsElements.get(i);
            int newX = pos[1] * HungrySnakeGame.squareSize;
            int newY = pos[0] * HungrySnakeGame.squareSize;
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
        leftEye.makeInvisible();
        rightEye.makeInvisible();
        tongue.makeInvisible();
        isVisible = false;
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
    
    private void setupFace(char direction){
        int xPosHead = positionsElements.get(0)[1] * HungrySnakeGame.squareSize; //Generated by AI
        int yPosHead = positionsElements.get(0)[0] * HungrySnakeGame.squareSize;//
        int size = HungrySnakeGame.squareSize;//
        int near = 2;//
        int far  = 6;//
    
        switch (direction) {
            case 'e':
                tongue.changeSize(2, 5);//
                leftEye.setPosition(xPosHead + far, yPosHead + near);//
                rightEye.setPosition(xPosHead + far, yPosHead + far);//
                tongue.setPosition(xPosHead + size, yPosHead + 4);//
                break;//
            case 'w':
                tongue.changeSize(2, 5);//
                leftEye.setPosition(xPosHead + near, yPosHead + near);//
                rightEye.setPosition(xPosHead + near, yPosHead + far);//
                tongue.setPosition(xPosHead - 6, yPosHead + 4);//
                break;//
            case 'n':
                tongue.changeSize(5,2);//
                leftEye.setPosition(xPosHead + near, yPosHead + near);//
                rightEye.setPosition(xPosHead + far,  yPosHead + near);//
                tongue.setPosition(xPosHead + 4, yPosHead - 6);//
                break;
            case 's':
                tongue.changeSize(5,2);//
                leftEye.setPosition(xPosHead + near, yPosHead + far);//
                rightEye.setPosition(xPosHead + far,  yPosHead + far);//
                tongue.setPosition(xPosHead + 4, yPosHead + size);//
                break;
        }
        if(isVisible){
            leftEye.makeVisible();
            rightEye.makeVisible();
            tongue.makeVisible();
        }
    }
}
