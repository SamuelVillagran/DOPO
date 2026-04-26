package domain;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class SokobanTest {

	@Test
	void shouldGenerateCorectlyWalls() {
		Sokoban game = new Sokoban(4, 4);
		char[][] boardGame = game.getBoard();
		
		for (int i = 0; i < 4; i++) {
			assertEquals('1', boardGame[0][i]);
			assertEquals('1', boardGame[i][0]);
			assertEquals('1', boardGame[3][i]);
			assertEquals('1', boardGame[i][3]);
		}
		
	}

	@Test
	void shouldGenerateCorrectlyTotalBoxes() {
		Sokoban game = new Sokoban(10, 10);
		int totalBoxes = game.getTotalBoxes();
		assertEquals(10, totalBoxes);
	}
	
	@Test
	void shouldGenerateAllBoxes() {
		Sokoban game = new Sokoban(6, 7); // Area = 6*7 = 42
		char[][] boardGame = game.getBoard();
		int countBoxes = 0, width = game.getWidth(), heigth = game.getHeigth();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < heigth ; j++) {
				if (boardGame[i][j] == 'b') {
					countBoxes++; // Should count 4 = 42*0.1 = area*0.1
				}
			}
			
		}
		assertEquals(4, countBoxes);
	}
	
	@Test
	void shouldGenerateAllPointsGoal() {
		Sokoban game = new Sokoban(6, 7); // Area = 6*7 = 42
		char[][] boardGame = game.getBoard();
		int countGoal = 0, width = game.getWidth(), heigth = game.getHeigth();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < heigth ; j++) {
				if (boardGame[i][j] == 'g') {
					countGoal++; // Should count 4 = 42*0.1 = area*0.1
				}
			}
		}
		
		assertEquals(4, countGoal);
	}
	
}
