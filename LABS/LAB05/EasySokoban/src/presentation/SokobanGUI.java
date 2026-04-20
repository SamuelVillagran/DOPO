package presentation;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class SokobanGUI extends JFrame{
	
	private SokobanGUI gui;
	
	public SokobanGUI() {
		prepareElements(); /*Vista*/
		prepareActions();  /*Controlador*/
		
	}
	
	private void prepareActions() {
	    addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Elimina proceso cuando se oprime X
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        }
	    });
	}

	private void prepareElements() {
		setTitle("EasySokoban");
		setScreen();
		elementsPanelButtons();
		elementsPanelImage();
	}
	
	private void elementsPanelImage() {
		
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

	public static void main (String[] args) {
		SokobanGUI gui = new SokobanGUI();
		gui.setVisible(true);
	}
}