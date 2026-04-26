package domain;

public class SokobanException extends Exception {

	public static final String PLAYER_CANT_MOVE = "Player try moves to some invalid moves.";
	public static final String BOARD_TOO_SMALL = "Height or width aren't correctly, try with others dimensions.";
	
	public SokobanException(String message) {
		super(message);
	}
}
