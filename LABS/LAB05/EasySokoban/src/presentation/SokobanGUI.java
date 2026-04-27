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

import domain.Sokoban;
import domain.SokobanException;

import java.awt.event.*;


public class SokobanGUI extends JFrame{
	
	private SokobanGUI gui;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemNew, menuItemOpen, menuItemSave, menuItemExit;
	
	//Atributos board
	private JTextField score, time, configHeight, configWidth;
	private JPanel infoPanel, optionsPanel, configPanel, controlPanel, arrowsPanel, boardPanel;
	private JButton btnPlay, btnChangeColor, btnRefresh, btnDown, btnRight, btnLeft, btnUp, btnRestart;
	private JPanel[] cells;
	private Sokoban game;
	
	
	/**
	 * Class contains the sokoban game and show to tne user.
	 */
	public SokobanGUI() {
		super("EasySokoban");
		prepareElements(); /*Vista*/
		prepareActions();  /*Controlador*/
		
		
	}

	private void prepareElements() {
		setScreen();
		prepareElementsMenu();/*Menu*/
		prepareElementsMainWindow();
		
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
		prepareActionsArrows();
	}
	
	private void prepareActionsBtnPlay() { //Ayudado con Claude Sonnet 4.6 IA
		btnPlay.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		           prepareElementsBoard();
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
	
	private void prepareElementsBoard() {
		try {
            int height = Integer.parseInt(configHeight.getText());
            int width = Integer.parseInt(configWidth.getText());
            if(game == null) {
            	new Sokoban(height, width);
            }else {
            	game.changeSize(height, width);
            }
            createBoard(height, width);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(SokobanGUI.this,
                "Ingresa números válidos para Height y Width.");
        } catch(SokobanException e){
        	JOptionPane.showMessageDialog(this, e.getMessage());
        }
	}

	private void prepareActionsArrows() {
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) { 
					game.movePlayer('n');
					refresh(); }
			}
		});
		
		btnDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) { 
					game.movePlayer('s');
					refresh(); }
			}
		});
		
		btnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) { 
					game.movePlayer('w');
					refresh(); }
			}
		});
		
		btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) { 
					game.movePlayer('e');
					refresh(); }
			}
		});
	}
	
	/*
	 * Prepare the necessary elements to the correctly work.
	 */
	private void prepareElementsMainWindow() {
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
		
		btnRestart = new JButton("Restart");
		optionsPanel.add(btnRestart);
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
		
		score = new JTextField("0", 5);
		score.setEditable(false);
		infoPanel.add(score);
		
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
		prepareActionsBtnRestart();
	
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
	
	private void prepareActionsBtnRestart() {
		btnRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game != null) {
					game.restart();
					refresh();
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
	
	/*
	 * Creates a board of JPanels
	 */
	private void createBoard(int heigth, int width) {
		if (boardPanel != null) {
			remove(boardPanel);
			boardPanel = null;
			cells = null;
		}
		try {
			game = new Sokoban(heigth, width);
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
			refresh();
			revalidate();
		    repaint();
		    
		} catch(SokobanException s) {
			JOptionPane.showMessageDialog(this, s.getMessage());
		}
	}
	
	/*
	 * Set the size and center in the screen. 
	 */
	private void setScreen() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)size.getWidth() / 2;
		int height = (int)size.getHeight() / 2;
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*
	 * Updates visually the board.
	 */
	private void refresh() {
		char[][] board = game.board();
		
		for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
            	int index = i * board[0].length + j;
                cells[index].setBackground(switch (board[i][j]) {
                	case '1' -> Color.YELLOW;
                	case 'g' -> Color.RED;
                	case 'b' -> Color.ORANGE;
                	case 'p' -> Color.CYAN;
                	default -> Color.LIGHT_GRAY;
                });
            }
		}
		score.setText(String.valueOf(game.getScore()));
		if(game.isGameCompleted()) {
			JOptionPane.showMessageDialog(SokobanGUI.this, "Has completado el juego!!", "Juego Finalizado",
					JOptionPane.INFORMATION_MESSAGE); // Obtenido gracias a chat gpt
		}
		revalidate();
		repaint();
	}
	
	public static void main (String[] args) {
		SokobanGUI gui = new SokobanGUI();
		gui.setVisible(true);
	}
}