
package domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This is Sokoban logic
 */
public class Sokoban {

	private static int height;
	private static int width;
	private static int boxesInGoal;
	private static int totalBoxes;
	private static char[][] board;
	private static Set<Integer> numbersH;
	private static Set<Integer> numbersW;
	
	/**
	 * This is the constructor of logic 
	 * of this game Sokoban
	 * @param h h is the height of board
	 * @param w w is the width of board
	 */
	public Sokoban(int h, int w) {
		height = h;
		width = w;
		numbersH = new HashSet();
		numbersW = new HashSet();
		board = new char[height][width];
		totalBoxes = (int) 0.1*width*height;
		generateObjects();
	}
	
	/*
	 * Generate all objects at the board
	 */
	public void generateObjects() {
		
		generateWalls();
		generateBoxes(); 
	}

	/*
	 * Generate boxes necessary at the board
	 */
	private void generateBoxes() {
		int copyTotal = totalBoxes;
		while (copyTotal > 0) {
			int posXBox = generateRandomNumW();
			int posYBox = generateRandomNumH();
			if (!numbersW.contains(posXBox) || !numbersH.contains(posYBox)) { 
				// if some of both coordenates aren't at the sets
				board[posXBox][posYBox] = 'b';
				numbersW.add(posXBox);
				numbersH.add(posYBox);
				copyTotal--;
			}
			
		}
		
	}

	/*
	 * Generate the walls of the board
	 */
	private void generateWalls() {
		for (int i = 0; i < height; i++) {
			board[i][0] = '1';
			board[i][height-1] = '1';
		}
		
		for (int j = 0; j < width; j++) {
			if (board[0][j] != '1') {
				board[0][j] = '1';
				board[width][j] = '1';
			}
		}
		numbersW.add(0);
		numbersH.add(0);
		numbersW.add(height);
		numbersH.add(width);
	}
	
	/*
	 * Generate a random number from
	 * 1 to height-1
	 */
	private int generateRandomNumH() {
		Random rand = new Random();
		return Math.min(rand.nextInt(height)+1, height-1); 
	}
	
	/*
	 * Generate a random number from
	 * 1 to width-1
	 */
	private int generateRandomNumW() {
		Random rand = new Random();
		return Math.min(rand.nextInt(width)+1, width-1); 
	}
}
