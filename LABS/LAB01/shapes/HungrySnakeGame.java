import java.util.Random;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Frame;

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

    /**
     * Constructor for objects of class HungrySnakeGame
     */
    public HungrySnakeGame() {
        Canvas.getCanvas().changeColorBackground("green");
        setUp();
        putRandomObstacles();
        putRandomApple();
        gameState = snake.getSize();
    }
    
    private void setUp() {
        fruit = new Fruit();
        rn = new Random();
        this.snake = new Snake(rn.nextInt(30), rn.nextInt(30));
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
    
    /*
     * Show a window to retry the game or exit of game 
     */
    public void gameOver() {
        int answer = JOptionPane.showConfirmDialog( // Ayudado por IA
            null, 
            "You lose!, but don't give up, the greatest humans are made from persistence", 
            "Game Over", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (answer == JOptionPane.YES_OPTION) {
            rebootGame();
            
        }
        if (answer == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Reboot the game propieties
     */
    private void rebootGame() {
        for (Obstacle obs: obstacles) {
            obs.makeInvisible();
            Canvas.getCanvas().erase(obs);
        }
        fruit.makeInvisible();
        Canvas.getCanvas().erase(fruit);
        snake.makeInvisible();
        Canvas.getCanvas().erase(snake);
        Canvas.getCanvas().erase(this);
        
        setUp();
        putRandomObstacles();
        putRandomApple();
        gameState = snake.getSize();
        Canvas.getCanvas().erase(Canvas.getCanvas());
        postionObstacles.clear();
        gameState = snake.getSize();
    }

    /*
     * This method put 3 new obstacles at the board in random positions
     * 
     */
    private void putRandomObstacles() {
        obstacles = new Obstacle[numObstacles];
        postionObstacles = new ArrayList<>(); //Crea la lista de las posiciones random de los obstaculos
        
        for (int i = 0; i < numObstacles; i++) {
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
                fruit.makeVisible();
                i++;
            }
        }
    }
}