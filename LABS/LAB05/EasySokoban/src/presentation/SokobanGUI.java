 package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
	
	//Atributos board
	private JTextField score, time, configHeight, configWidth;
	private JPanel infoPanel, optionsPanel, configPanel, controlPanel, arrowsPanel, boardPanel;
	private JButton btnPlay, btnChangeColor, btnRefresh, btnDown, btnRight, btnLeft, btnUp;
	private JPanel[] cells;
	
	
	
	public SokobanGUI() {
		super("EasySokoban");
		prepareElements(); /*Vista*/
		prepareActions();  /*Controlador*/
		prepareElementsBoard();
		prepareActionsBoard();
	}

	private void prepareElements() {
		setScreen();
		elementsPanelButtons();
		elementsPanelImage();
		
		prepareElementsMenu();/*Menu*/
		
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
		
		prepareActionsMenu(); /*AccionesMenu*/
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

	private void prepareElementsBoard() {
		//Asignando el layout como BorderLayout
		setLayout(new BorderLayout());
		
		
		//Configuracion zona North
		configPanel = new JPanel(new FlowLayout());
		configPanel.add(new JLabel("Height:"));
		configHeight = new JTextField(3);
		configPanel.add(configHeight);
		
		configPanel.add(new JLabel("Width"));
		configWidth = new JTextField(3);
		configPanel.add(configWidth);
		
		btnPlay = new JButton("Play");
		
		configPanel.add(btnPlay);
		
		add(configPanel, BorderLayout.NORTH);
		
		
		//Configuracion zona West
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(4,1, 10, 10));
		optionsPanel.add(new JButton("Modify"));
		optionsPanel.add(new JButton("Save"));
		optionsPanel.add(new JButton("Restart"));
		optionsPanel.add(new JButton("Open"));
		
		add(optionsPanel, BorderLayout.WEST);
		
		
		//Configuracion botones del juego.
		
		arrowsPanel = new JPanel(new BorderLayout());
		controlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4,4,4,4);
		
		btnLeft = new JButton("w");
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add(btnLeft, c);
		
		btnRight = new JButton("R");
		c.gridx = 3;
		c.gridy = 1;
		controlPanel.add(btnRight,c);
		
		btnDown = new JButton("D");
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		controlPanel.add(btnDown, c);
		
		btnUp = new JButton("Up");
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		controlPanel.add(btnUp, c);
		
		arrowsPanel.add(controlPanel, BorderLayout.CENTER);
		add(arrowsPanel, BorderLayout.SOUTH);
		
		
		//Zona East Zone
		
		//Info panel
		JPanel eastPanel = new JPanel(new BorderLayout());
		infoPanel = new JPanel(new GridLayout(2,1,5,5));
		infoPanel.add(new JLabel("Score:"));
		JTextField infoScore = new JTextField("0", 5);
		infoScore.setEditable(false);
		infoPanel.add(infoScore);
		
		infoPanel.add(new JLabel("Time:"));
		JTextField infoTime = new JTextField("00:00", 5);
		infoTime.setEditable(false);
		infoPanel.add(infoTime);
		
		//Opciones panel
		optionsPanel = new JPanel(new GridLayout(2,1,10,15));
		btnChangeColor = new JButton("Change Color");
		optionsPanel.add(btnChangeColor);
		btnRefresh = new JButton("Refresh");
		optionsPanel.add(btnRefresh);
		
		eastPanel.add(infoPanel, BorderLayout.NORTH);
		eastPanel.add(optionsPanel, BorderLayout.CENTER);
		
		add(eastPanel, BorderLayout.EAST);
		
		setVisible(true);
		
	}
	
	private void prepareActionsBoard() {
		
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