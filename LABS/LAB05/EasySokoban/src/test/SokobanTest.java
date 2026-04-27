package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Sokoban;
import domain.SokobanException;

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
	public void shouldGetScoreAfterMoveABoxToAGoal() {
		game.setCharacter(4,5, 'b');
		game.setCharacter(3, 5, 'g');
		game.movePlayer('n');
		
		int totalPointsGot = game.getScore();
		int expectedPoints = 1;
		
		assertTrue(totalPointsGot > 0);
		assertEquals(expectedPoints, totalPointsGot);
	}
	
}
