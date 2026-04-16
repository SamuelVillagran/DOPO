package presentation;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;


public class SokobanGUI extends JFrame{
	
	public SokobanGUI() {
		prepareElements();
		
	}
	
	private void prepareElements() {
		setTitle("EasySokoban");
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)size.getWidth() / 2;
		int height = (int)size.getHeight() / 2;
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main (String[] args) {
		SokobanGUI sokobanGUI = new SokobanGUI();
		sokobanGUI.setVisible(true);
	}
}