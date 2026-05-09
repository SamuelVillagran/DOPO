package domain;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.FileReader;


/*No olviden adicionar la documentacion*/
public class Forest extends MainGame implements Serializable {
    static private int SIZE=25;
    private Element[][] places;
    
    public Forest() {
        places=new Thing[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                places[r][c]=null;
            }
        }
        someThings();
    }

    public int  getSize(){
        return SIZE;
    }

    public Element getThing(int r, int c){
        return places[r][c];
    }

    public void setThing(int r, int c, Thing e){
        places[r][c]=e;
    }

    public void someThings(){   
        Tree bread = new DefaultTree(this, 10, 10);
        Tree soul = new DefaultTree(this, 15, 15);
        Squirrel scrat = new Squirrel(this, 5, 5);
        Squirrel sandy = new Squirrel(this, 3, 3);
        Shadow thief = new Shadow(this, 0, 0);
        Shadow lass = new Shadow(this, 7, 12);
        BaobabPrince villagran = new BaobabPrince(this, 11, 11);
        BaobabPrince sanchez = new BaobabPrince(this, 14, 14);
        //DefaultTree bob = new DefaultTree(this, 15,15);
        Bear smally = new Bear(this, 5, 4);
        Bear bear = new Bear(this, 15, 16);
    }
    
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inForest(r,c) && places[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inForest(r+dr,c+dc) && 
                    (places[r+dr][c+dc]!=null) &&  (places[r][c].getClass()==places[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    }
    
    public boolean isEmpty(int r, int c){
        return (inForest(r,c) && places[r][c]==null);
    }    
    
    protected boolean inForest(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    public void ticTac(){
        ArrayList<Element> movingThings = new ArrayList<>(); // Esta parte fue ayudado por Claude Sonnet 4.6 IA 2026 
        for(Element[] fileThing : places) {
            for(Element thing : fileThing){
                if(thing instanceof Squirrel){
                   ((Squirrel) thing).setState(false);
               }
            }
        }
        
        // 1. Identificar qué hay en el bosque actualmente
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (places[r][c] != null) {
                    movingThings.add(places[r][c]); // Esta linea fue ayudado por Claude Sonnet 4.6 IA 2026 
                }
            }
        }
    
        // 2. Ejecutar el movimiento de cada uno (una sola vez)
        for (Element t : movingThings) { // Esta parte fue ayudado por Claude Sonnet 4.6 IA 2026 
            t.ticTac();
        }
    }
    
    /**
     * Makes every cell of indexRow that is passed like parameter
     * @param indexRow indexRow is the row that going to be fulled of shadow marks
     * @param indexColumn indexColum is the column where is the shadow that produces shadow marks
     */
    public void makeFileBlack(int indexRow,int indexColumn) {
        
        for (int i = 0; i < SIZE; i++) {
            if ( !(i == indexColumn) && isEmpty(indexRow, i)) {
                places[indexRow][i] = new ShadowMark(this, indexRow, i);
            }
        }
    }
    
    /**
     * Delete every trail of shadows
     * @param indexRow indexRow is the index of row that going to be eliminated the shadows
     */
    public void deleteLastShadow(int indexRow) {
        for (int i = 0; i < SIZE; i++) {
            if (places[indexRow][i] instanceof Shadow || places[indexRow][i] instanceof ShadowMark) {
                setThing(indexRow, i, null);
            };
            
        }
    }

    public boolean isLivingThing(int indexRow, int indexColumn) {
        return places[indexRow][indexColumn] instanceof LivingThing;
    }
    
    public void makeDamageEntity(int row, int column, int pointsOfDamage) {
        if (isLivingThing(row, column)) {
            ((LivingThing) places[row][column]).makeDamage(pointsOfDamage);
        }
    }
    
    
    /**
     * Opens the specified file.
     * @param file The file that will be open.
     * @return Forest game.
     * @throws ForestException if the method is called, indicates that
     * 		the "open" is in construction.
     */
    public static Forest open00(File file) throws ForestException{
    	throw new ForestException("Open", file.getName());
    }
    
    /**
     * Saves the specified file.
     * @param file the name or path of file to be saved.
     * @throws ForestException if the method is called, indicate that
     * 		the "save" option is in construction.
     */
    public void saveAs00(File file) throws ForestException{
    	throw new ForestException("Save", file.getName());
    }
    
    /**
     * Opens a specified file.
     * @param file the name or path of file to be saved.
     * @return Forest game.
     * @throws IOException if there are problems with the disk or files.
     * @throws ClassNotFoundException if class is not found in the project.
     */
    public static Forest open(File file) throws IOException, ClassNotFoundException {
    	try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
    		return (Forest) in.readObject();
    	}
    }
    
    /**
     * Saves the specified file.
     * @param file the name or path of file to be saved.
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws ForestException if the method is called, indicate that
     * 		the "save" option is in construction.
     */
    public void saveAs(File file) throws IOException {
    	try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
    		out.writeObject(this);
    	}
    }
		
    
    
    /**
     * Imports a file.
     * @param file the name or file to be imported.|
     * @return Forest game.
     * @throws ForestException if the method is called, indicates that
     * 		the "import" option is in construction.
     * @throws IOException 
     */
    public Forest importFile(File file) throws ForestException, IOException{
    	
    	if (file == null) throw new ForestException("Import", file.getName());

    	BufferedReader in = new BufferedReader(new FileReader(file));
    	String line;

    	while ((line = in.readLine()) != null) {
    		boolean isLineEmpty = line.isEmpty();
    		if (!isLineEmpty) {
    			String[] lineIterator = line.split("[, ]+"); // Linea asistida por Gemini IA 2026
    			createThing(lineIterator);
    		}

    	}
    	in.close();
    	return this;
    }
    
    private void createThing(String[] data) {
    	int x = Integer.parseInt(data[1])-1;
		int y = Integer.parseInt(data[2])-1;
    	switch (data[0].toLowerCase()) {
    		case "tree":
    			places[x][y] = new DefaultTree(this, x, y);
    			break;
    		case "squirrel":
    			places[x][y] = new Squirrel(this, x, y);
    			break;
    	}
    }
    
    /**
     * Exports data to the specified file.
     * @param file the name or path if the file to which data should be exported.
     * @throws ForestException if the method is called, indicates that
     * 			the "export" option is under construction.
     */
    public void exportAs(File file) throws ForestException{
    	throw new ForestException("Export", file.getName());
    }
    
    
    
	



}
