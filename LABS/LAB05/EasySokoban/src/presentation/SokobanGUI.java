 package presentation;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.event.*;


public class SokobanGUI extends JFrame{
	
	private SokobanGUI gui;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemNew, menuItemOpen, menuItemSave, menuItemExit;
	
	
	public SokobanGUI() {
		prepareElements(); /*Vista*/
		prepareElementsMenu(); /*Menu*/
		prepareActions();  /*Controlador*/
		prepareActionsMenu(); /*AccionesMenu*/
	}

	private void prepareElements() {
		setTitle("EasySokoban");
		setScreen();
		elementsPanelButtons();
		elementsPanelImage();
		prepareElementsMenu();
		
	}
	
	private void prepareActions() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(SokobanGUI.this, 
						"¿Está seguro que desea salir?");
				switch(result) {
					case JOptionPane.YES_OPTION -> System.exit(0);
				}
			}
		});
	}
	
	private void prepareElementsMenu() {
		menuBar = new JMenuBar();
		menuFile = new JMenu("Archivo");
		
		menuItemNew = new JMenuItem("Nuevo");
		menuItemOpen = new JMenuItem("Abrir");
		menuItemSave = new JMenuItem("Salvar");
		menuItemExit = new JMenuItem("Salir");
		
		//Agregar elementos al menú
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.addSeparator(); //Separador de opciones
		menuFile.add(menuItemSave);
		menuFile.add(menuItemExit);
		
		menuBar.add(menuFile);
		
		setJMenuBar(menuBar);
	}
	
	private void prepareActionsMenu() {
		menuItemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(SokobanGUI.this,
						"¿Está seguro que desea salir?");
				if(result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
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
		SokobanGUI gui = new SokobanGUI();
		gui.setVisible(true);
	}
}