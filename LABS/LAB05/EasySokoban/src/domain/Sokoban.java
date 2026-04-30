
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is Sokoban logic
 */
public class Sokoban {
	public static final int MIM_DIMENSSIONS = 9;
	public static final int MAX_DIMENSSIONS = 40;
	private int height;
	private int width;
	private char[][] board;
	private int score;
	private int playerRow;
	private int playerCol;
	private int totalBoxes;
	private List <int[]> goalPositions;
	
	/**
	 * Constuctor class Sokoban
	 * Generates a Sokoban game with walls, boxes and one player.
	 * @param height number of rows.
	 * @param width  number of columns.
	 * @throws SokobanException if dimensions are so big or too small.
	 */
	public Sokoban(int height, int width) throws SokobanException {
		checkDimenssions(height,  width);
		
		board = new char[height][width];
		buildBoardCeros();
		this.height = height;
		this.width = width;
		totalBoxes = (int) (0.1*(width*height));
		score = 0;
		goalPositions = new ArrayList<>();
		
		playerRow = height /2;
		playerCol = width /2;
		board[playerRow][playerCol] = 'p';
	
	}
	
	/**
	 * Build a sokoban with just one player and a wall around limits.
	 * @param height number of rows.
	 * @param width  number of columns.
	 * @throws SokobanException if dimensions are so big or too small.
	 */
	public Sokoban(int height, int width, String type) throws SokobanException {
		checkDimenssions(height,  width);
		
		board = new char[height][width];
		buildBoardCeros();
		this.height = height;
		this.width = width;
		totalBoxes = (int) (0.1*(width*height));
		score = 0;
		goalPositions = new ArrayList<>();
		
		playerRow = height /2;
		playerCol = width /2;
		board[playerRow][playerCol] = 'p';
		
		generateWalls();
		generateAleatoryWalls();
		generateBoxes();
		generatePointGoal();
		
	}
	
	/*
	 * Fill the board of zero.
	 */
	private void buildBoardCeros() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = '0';
			}
		}
	}
	
	/**
	 * Check if the game has been completed.
	 * All the boxes are in goals.
	 * @ return true if every box is in a goal otherwise false.
	 */
	public boolean isGameCompleted() {
		return !goalPositions.isEmpty() && score == goalPositions.size();
	}
	
	/**
	 * Build the walls, boxes and goals in random positions.
	 */
	public void generateObjects() {
		generateWalls();
		generateAleatoryWalls();
		generateBoxes();
		generatePointGoal();
	}
	
	
	/*
<<<<<<< HEAD
	 * Generate walls limits.
=======
	 * Generate the walls of the board
>>>>>>> Samuel
	 */
	private void generateWalls() {
		for (int i = 0; i < height; i++) {
			board[0][i] = '1';
			board[width-1][i] = '1';
		}
		
		for (int j = 0; j < width; j++) {
			board[j][0] = '1';
			board[j][height-1] = '1';
		}
	}
	
	/*
	 * Generate aleatory walls, 5 as much.
	 */
	private void generateAleatoryWalls() { 
		Random range = new Random();
		int extraWalls = 3 + range.nextInt(4);
		
		int placed = 0, attempts = 0;	//Idea generada por chatGpt
        while (placed < extraWalls && attempts < 200) {
            int r = 1 + range.nextInt(height - 2);
            int c = 1 + range.nextInt(width  - 2);
            if (board[r][c] == '0'
                    && !(r == height / 2 && c == width / 2)
                    && !(r == height / 2 && c == width / 2 + 1)) {
                board[r][c] = '1';
                placed++;
            }
            attempts++;
        }
	}
	
	/*
	 * Generate boxes aleatory.
	 */
	private void generateBoxes() {
		Random range = new Random();
		int placed = 0;
		while(placed < totalBoxes) {
			int row = 1 + range.nextInt(height - 2);
			int col = 1 + range.nextInt(width - 2);
			if(board[row][col] == '0') {
				board[row][col] = 'b';
				placed++;
			}
		}
	}
	
	/*
	 * Create a point goal in a random place.
	 */
	private void generatePointGoal() {
		Random range = new Random();
		int placed = 0;
		while(placed < totalBoxes) {
			int row = 1 + range.nextInt(height - 2);
            int col = 1 + range.nextInt(width  - 2);
            if(board[row][col] =='0') {
            	board[row][col] = 'g';
            	goalPositions.add(new int[]{row, col});
            	placed++;
            }
		}		
	}
	
	/**
	 * Move the player in the wished direction.
	 * @param direction char indicating the direction feel.
	 * n - move north
	 * w - move west.
	 * s - move south.
	 * e - move east.
	 */
	public void movePlayer(char direction) {
		int dr = 0, dc = 0;
		switch(direction) {
			case 'n' -> dr = -1;
			case 's' -> dr = 1;
			case 'w' -> dc = -1;
			case 'e' -> dc = 1;
		}
		
		int newRow = playerRow +dr;
		int newCol = playerCol + dc;
		
		if(!inBounds(newRow, newCol)) return;
		
		char nextMovement = board[newRow][newCol];
		
		if(nextMovement == '1') { //Muro - no deja mover
			return;
		}
		
		if(nextMovement == '0' || nextMovement == 'g') { //Celda libre
			resetLeavePosition();
			playerRow = newRow;
			playerCol = newCol;
			board[playerRow][playerCol] = 'p';
		} else if (nextMovement == 'b') { //Celda con caja, la empuja
			int newRowBox = newRow + dr;
			int newColBox = newCol + dc;
			
			if(!inBounds(newRowBox, newColBox)) return;
			
			char behindBox = board[newRowBox][newColBox];
			if(behindBox == '0' || behindBox == 'g') {
				board[newRowBox][newColBox] = 'b';
				board[newRow][newCol] = isGoal(newRow, newCol) ? 'g' : '0';
				resetLeavePosition();
				playerRow = newRow;
				playerCol = newCol;
				board[playerRow][playerCol] = 'p';
					
				calculateScore();
			}
		}
	}
	
	/**
	 * Restart the game with the same dimenssions
	 * @throws SokobanException
	 */
	public void restart() {
		buildBoardCeros();
		goalPositions.clear();
		score = 0;
		playerRow = height / 2;
		playerCol = width / 2;
		board[playerRow][playerCol] = 'p';
		generateWalls();
		generateBoxes();
		generatePointGoal();
	}
	
	/*
	 * Get the number of boxes in goals
	 */
	private void calculateScore() {
		int count = 0;
		for(int[] goal : goalPositions) {
			if (board[goal[0]][goal[1]] == 'b') count++;
			score = count;
		}
	}
	
	public int getScore() {
		return score;
	}
	
	private boolean inBounds(int r, int c) {
		return r >=0 && r < height && c >= 0 && c < width; 
	}
	
	private void resetLeavePosition() {
		board[playerRow][playerCol] = isGoal(playerRow, playerCol) ? 'g' : '0';
	}
	
	/**
	 * Check if (r,c) is a position of a goal.
	 * @param r row position in the board.
	 * @param c col position in the board.
	 * @return true if r, c is a goal.
	 */
	private boolean isGoal(int r, int c) {
		for(int[] goal : goalPositions) {
			if(goal[0] == r && goal[1] == c) {
				return true;
			}
		}
		return false;
	}
	
	public int getTotalWalls() {
		return 0;
	}
	public int getTotalElements(char type) {
		int count = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if (board[i][j] == type) count++;
			}
		}
		return count;

	}
	
	public int getPlayerRow() {
		return playerRow;
	}
	
	public char getElement(int r, int c) {
		return board[r][c];
	}
	
	public int getPlayerCol() {
		return playerCol;
	}
	
	/**
	 * Set the character in the board.
	 * @param r row in board.
	 * @param c col in board.
	 * @param ch character wished to put.
	 */
	public void setCharacter(int r, int c, char ch) {
		if (ch == 'g') {
			goalPositions.add(new int[]{r, c});
		} else {
			for (int i = 0; i < goalPositions.size(); i++) {
				int[] goal = goalPositions.get(i);
				if (goal[0] == r && goal[1] == c) {
					goalPositions.remove(i);
					break;
				}
			}
		}
		board[r][c] = ch;
	}
	
	/**
	 * Change the size of the Sokoban board.
	 * @param newHeight new height wished.
	 * @param newWidth new width wished.
	 * @throws SokobanException
	 */
	public void changeSize(int newHeight, int newWidth) throws SokobanException {
		checkDimenssions(newHeight, newWidth);
		this.height = newHeight;
		this.width = newWidth;
		board = new char[height][width];
		buildBoardCeros();
		goalPositions.clear();
		score = 0;
		playerRow = height / 2;
		playerCol = width / 2;
		board[playerRow][playerCol] = 'p';
		generateWalls();
		generateBoxes();
		generatePointGoal();
	}
	
	/**
	 * Get all board.
	 * @return  Information board game.
	 */
	public char[][] board(){
		return board;
	}
	
	/**
	 * Check the dimensions of the new board.
	 * @param h new height wished.
	 * @param w new width wished.
	 * @throws SokobanException.
	 */
	private void checkDimenssions(int h, int w) throws SokobanException {
		if(h <= 0 || w <= 0) {
			throw new SokobanException(SokobanException.HEIGTH_OR_WIDTH_INVALID);
		} else if(h <  Sokoban.MIM_DIMENSSIONS || w <  Sokoban.MIM_DIMENSSIONS) {
			throw new SokobanException(SokobanException.BOARD_TOO_SMALL);
		}else if(h >Sokoban.MAX_DIMENSSIONS || w > Sokoban.MAX_DIMENSSIONS) {
			throw new SokobanException(SokobanException.BOARD_TOO_BIG);
		}
	}


	public int getTotalBoxes() {
		return totalBoxes;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public char[][] getBoard() {
		return board;
	}
}