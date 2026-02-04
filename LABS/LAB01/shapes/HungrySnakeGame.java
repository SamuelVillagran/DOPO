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
    private Circle apple;
    private Rectangle[] obstacles;
    private ArrayList<int[]> postionObstacles;

    /**
     * Constructor for objects of class HungrySnakeGame
     */
    public HungrySnakeGame() {
        Canvas.getCanvas().changeColorBackground("green");
        
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
        obstacles = new Rectangle[3];
        postionObstacles = new ArrayList<>(); //Crea la lista de las posiciones random de los obstaculos
        
        for (int i = 0; i < obstacles.length ; i++) {
            postionObstacles.add(i, new int[] {rn.nextInt(30)*squareSize, rn.nextInt(30)*squareSize});
            if (postionObstacles.contains(postionObstacles.get(i))) {
                postionObstacles.remove(i);
                i--; // Si llega a repetirse la posición se repite esa iteración
                continue;
            }
            obstacles[i] = new Rectangle();
            obstacles[i].setPosition(postionObstacles.get(i)[0], postionObstacles.get(i)[1]);
            obstacles[i].changeColor("black");
            obstacles[i].makeVisible();
        }
        
    }
    
    
    
    /**
     * This method put the fruit at the board in random positions
     */
    private void putRandomApple() {
        apple = new Circle();
        apple.changeSize(10);
        apple.setPosition(rn.nextInt(30)*squareSize, rn.nextInt(30)*squareSize);
        apple.changeColor("red");
    }
    
    private void quitApple() {
        apple = null;
        putRandomApple();
    }
}