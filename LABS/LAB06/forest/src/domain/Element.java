package domain;

import java.awt.Color;
import java.io.Serializable;

public interface Element extends Serializable{

	public void ticTac();
	public int shape();
	public boolean isOnlyThing();
	public boolean isLivingThing();
	
	public default Color getColor() {
		return Color.BLACK;
	}
	
}
