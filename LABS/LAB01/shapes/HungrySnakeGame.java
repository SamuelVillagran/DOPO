import java.util.Random;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
    private boolean lastOk;

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
        int[] newHeadPos = getNewPositionHead(direction);
        
        if(collidesWithObstacle(newHeadPos)){
            gameOver();
            return;
        }
        
        if(collidesWithFruit(newHeadPos)){
            snake.grow(direction);
            fruit.makeInvisible();
            putRandomApple();
            return;
        }
        
        if(!isInBounds(newHeadPos, maxRow, maxCol)){
            lastOk = false;
            gameOver();
            return;
        }
        
        snake.move(direction, this);
    }

    /**
     * Get the new position depending of the movement.
     * @return Array with [row, col].
     */
    private int[] getNewPositionHead(char direction){
        int[] headPos = snake.head();
        int row = headPos[0];
        int col = headPos[1];
        switch(direction) {
            case 'n': row--; break;
            case 'w': col--; break;
            case 's': row++; break;
            case 'e': col++; break;
        }
        return new int[]{row, col};
    }
    
    /**
     * Check the collision with fruits
     */
    private boolean collidesWithFruit(int[] pos){
        int[] fruitPos = fruit.getPosition();
        if(pos[0] == fruitPos[0] && pos[1] == fruitPos[1]){
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Check the collision with fruits
     */
    private boolean collidesWithObstacle(int[] pos){
        int boardRow = pos[0];
        int boardCol = pos[1];
        
        for(int[] obstaclePos : postionObstacles){
            int obstacleRow = obstaclePos[0] / squareSize;
            int obstacleCol = obstaclePos[1] / squareSize;
            if(boardCol == obstacleCol && boardRow == obstacleRow){
                return true;
            }
        }
        return false;
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
    
    /**
     * Check if the snake is in bounds limit.
     */
    private boolean isInBounds(int[] pos, int maxRow, int maxCol){
        int row = pos[0];
        int col = pos[1];
        if(row >= 0 && col >=0 && row < maxRow && col < maxCol){
            return true;
        } else{
            return false;
        }
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
    
    /*
     * Inicializate some variables 
     */
    private void setUp() {
        fruit = new Fruit();
        rn = new Random();
        this.snake = new Snake(rn.nextInt(30), rn.nextInt(30));
    }
    
    /**
     * Show the message gameOver
     */
    public void gameOver() {
        String[] options = {"Retry", "Exit"};
        int answer = JOptionPane.showOptionDialog( // Ayudado por IA
            null, 
            "You lose!, but don't give up, the greatest humans are made from persistence", 
            "Game Over", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            options,
            options[0]
        );
        
        if (answer == JOptionPane.YES_OPTION) {
            rebootGame();
        }
        
        if (answer == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    
    /*
     * Quit some objects of the screen from canvas
     */
    private void eraseObjects() {
        for (Obstacle obs: obstacles) {
            obs.makeInvisible();
            Canvas.getCanvas().erase(obs);
        }
        fruit.makeInvisible();
        Canvas.getCanvas().erase(fruit);
        snake.makeInvisible();
        Canvas.getCanvas().erase(snake);
        Canvas.getCanvas().erase(this);
    }
    
    /*
     * Check the collision with fruits
     */
    public void rebootGame() {
        
        eraseObjects();
        
        setUp();
        putRandomObstacles();
        putRandomApple();
        gameState = snake.getSize();
        Canvas.getCanvas().erase(Canvas.getCanvas());
        postionObstacles.clear();
        gameState = snake.getSize();
    }
}