package domain;

/**
 * Exceptions class own Sokoban Game.
 */
public class SokobanException extends Exception {

	public static final String PLAYER_CANT_MOVE = "Jugador se intenta mover a espacios no validos.";
	public static final String BOARD_TOO_SMALL = "Altura o anchura no son validos, intente con otros valores.";
	public static final String BOARD_TOO_BIG= "Tablero demasiado grande! Reduzca su tamaño.";
	public static final String HEIGTH_OR_WIDTH_INVALID = "Parametros de altura o ancho inválidos.";
	
	public SokobanException(String message) {
		super(message);
	}
}
