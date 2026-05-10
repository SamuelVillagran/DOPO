package presentation;
import domain.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ForestGUI extends JFrame{  
    public static final int SIDE=20;

    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private PhotoForest photo;
    private Forest theForest;
    public static ForestGUI gui = null;
    
    //Lab 06 - Menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem optionOpen, optionSaveAs, optionImport, optionExportAs, optionNew, optionExit;
    
    
    
    private ForestGUI() {
        theForest=new Forest();
        SIZE=theForest.getSize();
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements() {
        setTitle("Schelling Forest");
        photo=new PhotoForest(this);
        ticTacButton=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(ticTacButton,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE+15,SIDE*SIZE+72)); 
        setResizable(false);
        photo.repaint();
        
        prepareElementsMenu();
    }

    private void prepareElementsMenu() {
    	menuBar  = new JMenuBar();
    	menu = new JMenu("Archivo");
    	optionNew = new JMenuItem("Nuevo");
    	optionSaveAs = new JMenuItem("Guardar");
    	optionOpen = new JMenuItem("Abrir");
    	optionImport = new JMenuItem("Importar");
    	optionExportAs = new JMenuItem("Exportar como");
    	optionExit = new JMenuItem("Salir");
    	
    	menu.add(optionNew);
    	menu.addSeparator();
    	menu.add(optionSaveAs);
    	menu.add(optionOpen);
    	menu.addSeparator();
    	menu.add(optionExportAs);
    	menu.add(optionImport);
    	menu.addSeparator();
    	menu.add(optionExit);
    	
    	menuBar.add(menu);
    	setJMenuBar(menuBar);
    	
    }
    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        ticTacButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    ticTacButtonAction();
                }
            });
        
        prepareActionsMenu();
    }

    private void prepareActionsMenu() {
    	
    	//Funcion abrir
    	/*
    	optionOpen.addActionListener(
    		new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
			    	JFileChooser fileChooser = new JFileChooser();
			    	int result = fileChooser.showOpenDialog(ForestGUI.this);
			    	if(result == JFileChooser.APPROVE_OPTION) {
			    		File selectedFile = fileChooser.getSelectedFile();
			    		try {
			    			theForest.open00(selectedFile);
			    		} catch(ForestException fe){
			    			JOptionPane.showMessageDialog(ForestGUI.this, fe.getMessage());
			    	}
			    }
    		}
    	});
    	*/
    	
    	/*
    	optionSaveAs.addActionListener(
    		new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				JFileChooser fileChooser = new JFileChooser();
    				int result = fileChooser.showSaveDialog(ForestGUI.this);
    				if(result == JFileChooser.APPROVE_OPTION) {
    					File selectedFile = fileChooser.getSelectedFile();
    					try {
    						theForest.saveAs00(selectedFile);
    					} catch(ForestException fe){
    						JOptionPane.showMessageDialog(ForestGUI.this, fe.getMessage());
    					}
    				}
    			}
    	});
    	*/
    	
    	optionImport.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
    			    	JFileChooser fileChooser = new JFileChooser();
    			    	int result = fileChooser.showOpenDialog(ForestGUI.this);
    			    	if(result == JFileChooser.APPROVE_OPTION) {
    			    		File selectedFile = fileChooser.getSelectedFile();
    			    		try {
    			    			theForest.importFile(selectedFile);
    			    			photo.repaint();
    			    		} catch(ForestException fe){
    			    			JOptionPane.showMessageDialog(ForestGUI.this, fe.getMessage());
    			    		} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    			    }
        		}
        			
        	});
    	
    	optionExportAs.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
    			    	JFileChooser fileChooser = new JFileChooser();
    			    	int result = fileChooser.showSaveDialog(ForestGUI.this);
    			    	if(result == JFileChooser.APPROVE_OPTION) {
    			    		File selectedFile = fileChooser.getSelectedFile();
    			    		try {
    			    			theForest.exportAs(selectedFile);
    			    		} catch(ForestException fe){
    			    			JOptionPane.showMessageDialog(ForestGUI.this, fe.getMessage());
    			    		} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    			    }
        		}
        			
        	});
    	
    	optionNew.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				newAction();
        		}
        	});
    	
    	optionExit.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					dispose();
    				}
    			});
    	/*
    	//Segunda version guardar:
    	optionSaveAs.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				saveAsAction();
        			}
        	});
    	
    	//Sregunda version abrir:
    	optionOpen.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
    			    	openAction();
        		}
        	});
    	*/
    	//Tercera version guardar:
    	optionSaveAs.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				saveAsAction02();
        			}
        	});
    	
    	//Tercera version abrir:
    	optionOpen.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
    			    	openAction02();
        		}
        	});
    }
    	

    private void newAction() {
    	int result = JOptionPane.showConfirmDialog(ForestGUI.this,
				"¿Esta seguro que quiere iniciar un nuevo Forest? Se perderá el progreso.", "Nuevo juego",
				JOptionPane.YES_NO_OPTION);
    	
		if(result == JOptionPane.YES_OPTION) {
			theForest = new Forest();
			
	    	repaint();
	    	revalidate();
	    	JOptionPane.showMessageDialog(ForestGUI.this, "Se ha restablecido el juego.", "Nuevo juego", JOptionPane.INFORMATION_MESSAGE);
		}
    }
    
    private void saveAsAction01() {
    	JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("DAT Files", "dat"));
		int result = fileChooser.showSaveDialog(ForestGUI.this);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if(!selectedFile.getName().endsWith(".dat")) {
				selectedFile = new File(selectedFile.getAbsolutePath() + ".dat");
			}
			try {
				theForest.saveAs01(selectedFile);
			} catch (IOException  io) {
				JOptionPane.showMessageDialog(ForestGUI.this, "Error al guardar archivo", "Error",
	    				JOptionPane.ERROR_MESSAGE);
			}
		}
    }
    
    private void openAction01() {
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setFileFilter(new FileNameExtensionFilter("DAT Files", "dat"));
    	int result = fileChooser.showOpenDialog(ForestGUI.this);
    	if(result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    		try {
    			Forest loadedForest = Forest.open01(selectedFile);
    			theForest = loadedForest;
	    		photo.repaint();
    		} catch(IOException | ClassNotFoundException io){
    			JOptionPane.showMessageDialog(ForestGUI.this, "Error al intentar abrir el archivo",
    					"Eror", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    private void openAction02() {
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setFileFilter(new FileNameExtensionFilter("DAT Files", "dat"));
    	int result = fileChooser.showOpenDialog(ForestGUI.this);
    	if(result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    		try {
    			Forest loadedForest = Forest.open02(selectedFile);
    			theForest = loadedForest;
	    		photo.repaint();
    		} catch(ForestException fe){
    			JOptionPane.showMessageDialog(ForestGUI.this, fe.getMessage(),"Error",
    					JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    private void saveAsAction02() {
    	JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("DAT Files", "dat"));
		int result = fileChooser.showSaveDialog(ForestGUI.this);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if(!selectedFile.getName().endsWith(".dat")) {
				selectedFile = new File(selectedFile.getAbsolutePath() + ".dat");
			}
			try {
				theForest.saveAs02(selectedFile);
			} catch (ForestException  fe) {
				JOptionPane.showMessageDialog(ForestGUI.this, fe.getMessage(), "Error",
	    				JOptionPane.ERROR_MESSAGE);
			}
		}
    }
    
    private void ticTacButtonAction() {
        theForest.ticTac();
        photo.repaint();
    }
    
    public Forest gettheForest(){
        return theForest;
    }
    
    public static void main(String[] args) {
        ForestGUI cg=new ForestGUI();
        cg.setVisible(true);
    }  
    
    public static ForestGUI getGUI() {
        
        if (gui == null) gui = new ForestGUI();
        
        return gui;
    }
    
    public Forest getForest() {
        return theForest;
    }
}

class PhotoForest extends JPanel{
    private ForestGUI gui;

    public PhotoForest(ForestGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE+10, gui.SIDE*gui.SIZE+10));         
    }


    public void paintComponent(Graphics g){
        Forest theForest=gui.gettheForest();
        super.paintComponent(g);
         
        for (int c=0;c<=theForest.getSize();c++){
            g.drawLine(c*gui.SIDE,0,c*gui.SIDE,theForest.getSize()*gui.SIDE);
        }
        for (int f=0;f<=theForest.getSize();f++){
            g.drawLine(0,f*gui.SIDE,theForest.getSize()*gui.SIDE,f*gui.SIDE);
        }       
        for (int f=0;f<theForest.getSize();f++){
            for(int c=0;c<theForest.getSize();c++){
                if (theForest.getThing(f,c)!=null){
                    g.setColor(theForest.getThing(f,c).getColor());
                    if (theForest.getThing(f,c).shape()==Thing.SQUARE){                  
                        g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);   
                    }else {
                        g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                    }
                    if (theForest.getThing(f,c).isLivingThing()){
                        g.setColor(Color.red);
                        if (((LivingThing)theForest.getThing(f,c)).getEnergy()>=50){
                            g.drawString("+",gui.SIDE*c+6,gui.SIDE*f+15);
                        } else {
                            g.drawString("~",gui.SIDE*c+6,gui.SIDE*f+17);
                        }
                    }    
                }
            }
        }
    }
}
