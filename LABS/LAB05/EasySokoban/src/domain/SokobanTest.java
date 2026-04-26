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

}
