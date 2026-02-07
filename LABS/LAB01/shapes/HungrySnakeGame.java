import java.util.Random;
import java.util.ArrayList;

/**
 * This is the board where going to set objects of game
 * 
 * @author Sanchez-Villagran 
 * @version 1.0
 */
public class HungrySnakeGame {
    public static final int squareSize = 10;
    public static final int numObstacles = 3;
    public static Random rn;
    private int gameState;
    private Snake snake;
    private Fruit fruit;
    private Obstacle[] obstacles;
    private ArrayList<int[]> postionObstacles;
    private int maxRow = 30;
    private int maxCol = 30;

    /**
     * Constructor for objects of class HungrySnakeGame
     */
    public HungrySnakeGame() {
        Canvas.getCanvas().changeColorBackground("green");
        fruit = new Fruit();
        rn = new Random();
        this.snake = new Snake(rn.nextInt(maxRow), rn.nextInt(maxCol));
        putRandomObstacles();
        putRandomApple();
        gameState = snake.getSize();
    }
    
    /**
     * Delete the fuits of the game
     */
    public void quitFruits() {
        fruit = null;
        putRandomApple();
    }
    
    /**
     * Move the snake to a determinated position
     */
    public void moveSnake(char direction){
        if(snake != null){
            snake.move(direction, maxRow, maxCol);
        }
    }

    /**
     * This method put 3 new obstacles at the board in random positions
     * 
     */
    private void putRandomObstacles() {
        obstacles = new Obstacle[3];
        postionObstacles = new ArrayList<>(); //Crea la lista de las posiciones random de los obstaculos
        
        for (int i = 0; i < numObstacles; i++) {
            int[] newPos = new int[] {rn.nextInt(maxRow)*squareSize, rn.nextInt(maxCol)*squareSize};
            boolean exists = false;            
            for (int[] position : postionObstacles) { //
                if (position[0] == newPos[0] && position[1] == newPos[1]) { // código ayudado a hacer por IA
                    exists = !exists; //
                    break; //
                } //
            } //
            
            if (exists) { //
                i--; // 
                continue; //
            } //
            
            postionObstacles.add(newPos);
            createNewObstacle(newPos[1], newPos[0], i);
            
        }

    }
    
    
    /*
     * Create a new obstacle and this is added at the obstacles ArrayList
     * @param xPos xPos is the x's coordenade going to set the obstacle created
     * @param yPos yPos is the y's coordenade going to set the obstacle created
     * @param indexObstacle indexObstacle is the index specific of the object at the collection obstacles
     */
    private void createNewObstacle(int xPos, int yPos, int indexObstacle) {
        obstacles[indexObstacle] = new Obstacle();
        obstacles[indexObstacle].setPosition(xPos, yPos);
    }
    
    /*
     * This method put the fruit at the board in random positions
     */
    private void putRandomApple() {
        fruit = new Fruit();
        byte i = 0;
        while (i < 1) {
            int[] newPos = new int[] {rn.nextInt(maxRow)*squareSize, rn.nextInt(maxCol)*squareSize};
            boolean exists = false;
            for (int[] pos : postionObstacles) {
                if (pos[0] == newPos[0] && pos[1] == newPos[1]) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                fruit.setPosition(newPos[0], newPos[1]);
                fruit.makeVisible();
                i++;
            }
        }
    }
}