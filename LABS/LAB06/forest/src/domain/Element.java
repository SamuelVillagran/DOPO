package domain;

import java.awt.Color;

public interface Element {

	public void ticTac();
	public int shape();
	public boolean isOnlyThing();
	public boolean isLivingThing();
	
	public default Color getColor() {
		return Color.BLACK;
	}
	
}
