package domain;

public class SokobanException extends Exception {

	public static final String PLAYER_CANT_MOVE = "Player try moves to some invalid moves";
	
	public SokobanException(String message) {
		super(message);
	}
}
