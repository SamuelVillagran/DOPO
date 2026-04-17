package presentation;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class SokobanGUI extends JFrame{
	
	public SokobanGUI() {
		prepareElements();
		prepareActions();
		
	}
	
	private void prepareElements() {
		setTitle("EasySokoban");
		setScreen();
		elementsPanelButtons();
		elementsPanelImage();
	}
	
	private void elementsPanelImage() {
		// TODO Auto-generated method stub
		
	}

	private void elementsPanelButtons() {
		// TODO Auto-generated method stub
		
	}

	private void setScreen() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)size.getWidth() / 2;
		int height = (int)size.getHeight() / 2;
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	private void prepareActions() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(SokobanGUI.this, "¿Está seguro que desea salir?");
				switch(result) {
					case JOptionPane.YES_OPTION -> System.exit(0);
				}
			}
		});
	}
	
	

	public static void main (String[] args) {
		SokobanGUI sokobanGUI = new SokobanGUI();
		sokobanGUI.setVisible(true);
	}
}