
package domain;

import java.awt.Point;
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
	private static Point coordenadesPy;
	
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
		board = new char[width][height];
		totalBoxes = (int) (0.1*width*height);
		generateObjects();
	}
	
	/*
	 * Generate all objects at the board
	 */
	public void generateObjects() {
		generateWalls();
		generateBoxes();
		generatePointGoal();
		generatePlayer();
	}
	
	/*
	 * Makes move the player to some direction
	 * @param d d is the direction that going to move player
	 */
	public void movePlayer(char d) throws SokobanException {
		int pyx, newpyy;
		boolean inBounds;
		switch (d) { /* 'u' = up, 'd' = down, 'r' = right, 'l' = left */
			case 'u':
				pyx = (int) coordenadesPy.getX();
				newpyy = (int) (coordenadesPy.getY()+1);
				inBounds = pyx < width-1 && newpyy < height;
				if (!inBounds || board[pyx][newpyy] != '\0') throw new SokobanException(SokobanException.PLAYER_CANT_MOVE);
				if (board[pyx][newpyy] == '\0' && inBounds) {
					board[pyx][newpyy] = 'p';
					coordenadesPy.setLocation(pyx, newpyy);
				}
				break;
				
			case 'd':
				pyx = (int) coordenadesPy.getX();
				newpyy = (int) (coordenadesPy.getY()-1);
				inBounds = pyx < width-1 && newpyy < height;
				if (!inBounds || board[pyx][newpyy] != '\0') throw new SokobanException(SokobanException.PLAYER_CANT_MOVE);
				if (board[pyx][newpyy] == '\0' && inBounds) {
					board[pyx][newpyy] = 'p';
					coordenadesPy.setLocation(pyx, newpyy);
				}
				break;
				
			case 'r':
				pyx = (int) coordenadesPy.getX()+1;
				newpyy = (int) (coordenadesPy.getY());
				inBounds = pyx < width-1 && newpyy < height;
				if (!inBounds || board[pyx][newpyy] != '\0') throw new SokobanException(SokobanException.PLAYER_CANT_MOVE);
				if (board[pyx][newpyy] == '\0' && inBounds) {
					board[pyx][newpyy] = 'p';
					coordenadesPy.setLocation(pyx, newpyy);
				}
				break;
				
			case 'l':
				pyx = (int) coordenadesPy.getX()-1;
				newpyy = (int) (coordenadesPy.getY());
				inBounds = pyx < width-1 && newpyy < height;
				if (!inBounds || board[pyx][newpyy] != '\0') throw new SokobanException(SokobanException.PLAYER_CANT_MOVE);
				if (board[pyx][newpyy] == '\0' && inBounds) {
					board[pyx][newpyy] = 'p';
					coordenadesPy.setLocation(pyx, newpyy);
				}
				break;
		}
	}

	private void generatePlayer() {
		int isCreatedPy = 1;
		while (isCreatedPy > 0) {
			int posXBox = generateRandomNumW();
			int posYBox = generateRandomNumH();
			if (board[posXBox][posYBox] == '\0') { 
				board[posXBox][posYBox] = 'p';
				coordenadesPy = new Point(posXBox, posYBox);
				numbersW.add(posXBox);
				numbersH.add(posYBox);
				isCreatedPy--;
			}
		}
	}

	/*
	 * Generate the points of goal of sokoban
	 */
	private void generatePointGoal() {
		int copyTotal = totalBoxes;
		while (copyTotal > 0) {
			int posXBox = generateRandomNumW();
			int posYBox = generateRandomNumH();
			if (board[posXBox][posYBox] == '\0') { 
				
				board[posXBox][posYBox] = 'g';
				numbersW.add(posXBox);
				numbersH.add(posYBox);
				copyTotal--;
			}
			
		}
	}

	/*
	 * Generate boxes necessary at the board
	 */
	private void generateBoxes() {
		int copyTotal = totalBoxes;
		while (copyTotal > 0) {
			int posXBox = generateRandomNumW();
			int posYBox = generateRandomNumH();
			if (board[posXBox][posYBox] == '\0') { 
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
			board[0][i] = '1';
			board[width-1][i] = '1';
		}
		
		for (int j = 0; j < width; j++) {
			board[j][0] = '1';
			board[j][height-1] = '1';
		}
		
		numbersW.add(0);
		numbersH.add(0);
		numbersW.add(width-1);
		numbersH.add(height-1);
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
	
	public char[][] getBoard() {
		return board;
	}
	
	public int getTotalBoxes() {
		return totalBoxes;
	}
	
	public int getHeigth() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
