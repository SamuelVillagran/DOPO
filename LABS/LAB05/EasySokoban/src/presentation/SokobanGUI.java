 package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
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
		
		
	}

	private void prepareElements() {
		setScreen();
		elementsPanelButtons();
		elementsPanelImage();
		
		prepareElementsMenu();/*Menu*/
		prepareElementsBoard();
		
	}
	
	private void prepareActions() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(SokobanGUI.this, 
						"¿Está seguro que desea salir?");
				switch(result) {
					case JOptionPane.YES_OPTION -> SokobanGUI.this.dispose();
				}
			}
		});
		
		prepareActionsMenu(); /*AccionesMenu*/
		
		prepareActionsBoard();
	}
	
	private void prepareActionsBtnPlay() { //Ayudado con Claude Sonnet 4.6 IA
		btnPlay.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int heigth = Integer.parseInt(configHeight.getText());
		            int width = Integer.parseInt(configWidth.getText());
		            createBoard(heigth, width);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(SokobanGUI.this,
		                "Ingresa números válidos para Height y Width.");
		        }
		    }

			
		});
	}

	private void prepareActionsMenu() {
		menuItemOpen.addActionListener(new ActionListener() { //Ayudado por Claude Sonnet 4.6, supervisado
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser fc = new JFileChooser();
	            fc.setDialogTitle("Abrir archivo");
	            int opcion = fc.showOpenDialog(SokobanGUI.this);
	            if (opcion == JFileChooser.APPROVE_OPTION) {
	                
	                JOptionPane.showMessageDialog(SokobanGUI.this,
	                        "Funcion en construcción");
	            }
	        }
	    });

	    menuItemSave.addActionListener(new ActionListener() { //Ayudado por Claude Sonnet 4.6, supervisado
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser fc = new JFileChooser();
	            fc.setDialogTitle("Guardar Archivo");
	            int opcion = fc.showSaveDialog(SokobanGUI.this);
	            if (opcion == JFileChooser.APPROVE_OPTION) {
	                JOptionPane.showMessageDialog(SokobanGUI.this,
	                		"Funcion en construcción");
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
		prepareActionsBtnPlay();
		prepareActionsBtnChangeColor();
	
	}
	
	private void prepareActionsBtnChangeColor() {
		btnChangeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(SokobanGUI.this, 
						"Elige el color de las piezas", 
						getForeground()
				);
				if (color != null) {
					changeColorProps(color);
				}
			}
		});
	}

	private void changeColorProps(Color color) {
		if (cells != null) {
			for (JPanel cell : cells) {
				cell.setBackground(color);
				cell.repaint();
			}
		}
	}
	
	private void createBoard(int heigth, int width) {
		if (boardPanel != null && cells != null) {
			boardPanel = null;
			cells = null;
		}
		int STATIC_AREA = 500; // Se tienen 500 pixeles para el area
		int CELL_PIXELS =  Math.max(5, STATIC_AREA / Math.max(heigth, width)); // Idea dada por Claude Sonnet 4.6 IA
		
		boardPanel = new JPanel(new GridLayout(heigth, width));
		boardPanel.setPreferredSize(new Dimension(width * CELL_PIXELS, heigth * CELL_PIXELS));
		cells = new JPanel[heigth*width];
		
		for (int i = 0; i < cells.length; i++) {
			cells[i] = new JPanel();
			cells[i].setBackground(getForeground());
			cells[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); /* Ayudado por Clude Sonnet 4.6 IA 
			            Se hace una linea para identificar el espácio                     */
			boardPanel.add(cells[i]);
		}
		
		add(boardPanel, BorderLayout.CENTER);
		revalidate();
	    repaint();
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