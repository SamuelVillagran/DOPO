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
    public static Random rn;
    private int gameState;
    private Snake snake;
    private Fruit fruit;
    private Obstacle[] obstacles;
    private ArrayList<int[]> postionObstacles;

    /**
     * Constructor for objects of class HungrySnakeGame
     */
    public HungrySnakeGame() {
        Canvas.getCanvas().changeColorBackground("green");
        fruit = new Fruit();
        rn = new Random();
        this.snake = new Snake(rn.nextInt(30), rn.nextInt(30));
        putRandomObstacles();
        putRandomApple();
        gameState = snake.getSize();
    }

    /**
     * This method put 3 new obstacles at the board in random positions
     * 
     */
    private void putRandomObstacles() {
        obstacles = new Obstacle[3];
        postionObstacles = new ArrayList<>(); //Crea la lista de las posiciones random de los obstaculos
        
        for (int i = 0; i < obstacles.length ; i++) {
            int[] newPos = new int[] {rn.nextInt(30)*squareSize, rn.nextInt(30)*squareSize};
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
            obstacles[i].setPosition(newPos[1], newPos[0]);
            
        }

    }
    
    /**
     * This method put the fruit at the board in random positions
     */
    private void putRandomApple() {
        Fruit fruit = new Fruit();
        byte i = 0;
        while (i < 1) {
            int[] newPos = new int[] {rn.nextInt(30)*squareSize, rn.nextInt(30)*squareSize};
            boolean exists = false;
            for (int[] pos : postionObstacles) {
                if (pos[0] == newPos[0] && pos[1] == newPos[1]) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                fruit.setPosition(newPos[0], newPos[1]);
                i++;
            }
        }
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
            snake.move(direction);
        }
    }
}