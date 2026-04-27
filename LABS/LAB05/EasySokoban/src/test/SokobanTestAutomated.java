package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Sokoban;
import domain.SokobanException;

/*
 * These tests are reference to the constructor that create elements aleatory.
 */
class SokobanTestAutomated {


	@Test
	public void shouldBuildTwoBoxes() throws SokobanException{
		Sokoban game = new Sokoban(9,9);
		int gotTotalBoxes = game.getTotalElements('b');
		int expectedBoxes = 2;
		
		assertEquals(expectedBoxes, gotTotalBoxes);
	}
	
	@Test
	public void shouldBuildMoreThanBoxesBorder()throws SokobanException{
		Sokoban game = new Sokoban(9,9);
		int gotTotalBoxes = game.getTotalElements('1');
		int minimumBoxes = 27; // muros en total que estan en el borde en forma de U
		assertTrue(gotTotalBoxes > minimumBoxes); 
	}
	
	@Test
	public void shouldBuildTwoGoals() throws SokobanException{
		Sokoban game = new Sokoban(9,9);
		int gotTotalGoals = game.getTotalElements('g');
		int expectedGoals = 2;
		
		assertEquals(expectedGoals, gotTotalGoals);
	}
	
	@Test
	public void shoudThrowAnExceptionIfDimensionAreSoBig() {
		try {
			Sokoban game = new Sokoban(100, 100);
			fail("Did not trow an exception");
		} catch(SokobanException e) {
			assertEquals(SokobanException.BOARD_TOO_BIG , e.getMessage());
		}
	}
	
	@Test
	public void shoudThrowAnExceptionIfDimensionAreSmall() {
		try {
			Sokoban game = new Sokoban(1, 1);
			fail("Did not trow an exception");
		} catch(SokobanException e) {
			assertEquals(SokobanException.BOARD_TOO_SMALL , e.getMessage());
		}
	}
	
	@Test
	public void shoouldThrowAnExceptionIfDimensionAreNegatives() {
		try {
			Sokoban game = new Sokoban(-1, -1);
			fail("Did not trow an exception");
		} catch(SokobanException e) {
			assertEquals(SokobanException.HEIGTH_OR_WIDTH_INVALID , e.getMessage());
		}
	}
	
}
