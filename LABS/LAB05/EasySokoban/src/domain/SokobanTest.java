package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SokobanTest {
	private Sokoban game;

	@BeforeEach
	public void setUp() throws SokobanException {
		game = new Sokoban(10, 10, "primitivo");
		//Jugador siempre aparece en la mitad del mapa
		//Jugador estaria en la posicion (5, 5)
	}
	
	
	@Test
	public void shouldMovePlayerCorrectly() {
		//Intentamos que el jugador llegue una fila abajo
		//pero dando una vuelta, sube, baja dos veces y gira a la izquierda.
		//Jugador inicia en (5,5) como baja una fila se espera (6,5).
		game.movePlayer('n');
		game.movePlayer('e');
		game.movePlayer('s');
		game.movePlayer('s');
		game.movePlayer('w');
		
		int playerRowPosition = game.getPlayerRow();
		int playerColPosition = game.getPlayerCol();
		
		int expectedPlayerColPosition = 5;
		int expectedPlayerRowPosition = 6;
		
		assertEquals(expectedPlayerRowPosition, playerRowPosition);
		assertEquals(expectedPlayerColPosition, playerColPosition);
	}
	
	@Test
	public void shouldLastPlaceBeCero() {
		game.movePlayer('n');
		game.movePlayer('n');
		
		char element = game.getElement(4,5);
		char expecteElement = '0';
		
		assertEquals(expecteElement, element);
	}
	
	@Test
	public void shouldLastPlaceBeGoal() {
		game.setCharacter(4, 5, 'g');
		game.movePlayer('n');
		game.movePlayer('n');
		
		char elementBox = game.getElement(4,5);
		char expectedElementBox = 'g';
		
		assertEquals(expectedElementBox, elementBox);
		
	}
	
	@Test
	public void shouldntMoveInFrontOfWall() {
		game.setCharacter(4, 5, '1'); //Se pone un muro justo al frente del jugador
		game.movePlayer('n');
		
		int playerRowPosition = game.getPlayerRow();
		int playerColPosition = game.getPlayerCol();
		
		int expectedPlayerRowPosition = 5;
		int expectedPlayerColPosition = 5; 
		
		assertEquals(expectedPlayerRowPosition, playerRowPosition);
		assertEquals(expectedPlayerColPosition, expectedPlayerColPosition);
	}

	@Test
	public void shouldMoveBox() {
		game.setCharacter(1, 5, '1');
		game.setCharacter(4, 5, 'b');
		game.movePlayer('n');
		
		int playerRowPosition = game.getPlayerRow();
		int playerColPosition = game.getPlayerCol();
		int expectedPlayerColPosition = 5; 
		int expectedPlayerRowPosition = 4;
		
		//Verificar movimiento jugador en la casilla de arriba
		assertEquals(expectedPlayerRowPosition, playerRowPosition);
		assertEquals(expectedPlayerColPosition, playerColPosition);
		
		char gotElement = game.getElement(3, 5);
		char expectedElement = 'b';
		
		assertEquals(expectedElement, gotElement);
	}
	
	@Test
	public void shouldntMoveABoxItBehindItTheresAWall() {
		game.setCharacter(1, 5, '1');
		game.setCharacter(2, 5, 'b');
		game.movePlayer('n'); //fila 4
		game.movePlayer('n'); //fila 3
		game.movePlayer('n'); //fila 2, intenta empujarla
		
		int playerRowPosition = game.getPlayerRow();
		int playerColPosition = game.getPlayerCol();
		int expectedPlayerColPosition = 5; 
		int expectedPlayerRowPosition = 3;
		
		assertEquals(expectedPlayerRowPosition, playerRowPosition);
		assertEquals(expectedPlayerColPosition, playerColPosition);
	}

	@Test
	void shouldGenerateCorectlyWalls() throws SokobanException {
		char[][] boardGame = game.getBoard();
		
		for (int i = 0; i < 4; i++) {
			assertEquals('1', boardGame[0][i]);
			assertEquals('1', boardGame[i][0]);
			assertEquals('1', boardGame[9][i]);
			assertEquals('1', boardGame[i][9]);
		}
	}

	@Test
	void shouldGenerateCorrectlyTotalBoxes() throws SokobanException {
		int totalBoxes = game.getTotalBoxes();
		assertEquals(10, totalBoxes);
	}
	
	@Test
	void shouldGenerateAllBoxes() throws SokobanException {
		char[][] boardGame = game.getBoard();
		int countBoxes = 0, width = game.getWidth(), heigth = game.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < heigth ; j++) {
				if (boardGame[i][j] == 'b') {
					countBoxes++; // Should count 10 = 100*0.1 = area*0.1
				}
			}
		}
		assertEquals(10, countBoxes);
	}
	
	@Test
	void shouldGenerateAllPointsGoal() throws SokobanException {
		char[][] boardGame = game.getBoard();
		int countGoal = 0, width = game.getWidth(), heigth = game.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < heigth ; j++) {
				if (boardGame[i][j] == 'g') {
					countGoal++; // Should count 4 = 42*0.1 = area*0.1
				}
			}
		}
		assertEquals(10, countGoal);
	}
	
}
