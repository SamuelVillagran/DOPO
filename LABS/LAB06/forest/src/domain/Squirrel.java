package domain;

import java.awt.Color;
import java.util.Random;

/**
 * Squirrel go to a random position on map.
 * it could reproduce and die at 10 years.
 */
public class Squirrel extends LivingThing {
    private Forest forest;
    private int season; //[pup, juvenile, adult]
    private int tictac;
    private boolean state = false;
    
    /**
     * Constructor for objects of class Squirrel
     */
    public Squirrel(Forest forest,int row, int column) {
        this.forest=forest;
        this.row=row;
        this.column=column; 
        this.color= new Color(212, 94, 0);
        this.season=0;
        this.tictac=0;
        this.forest.setThing(row,column,(Thing)this);    
        this.state = true;
    }

    /**
     * tictac change the state, color, old and station
     * of squirrel when tictoc button is clicked
     */
    @Override
    public void ticTac() {
        if(state) return;
        state = true;
        
        tictac++;
        if (tictac % 4 == 0){
            getOld();
        }
        
        if(years >= 10) return; 
        reproduce();
        move();
    }
    
    /**
     * Check if the Squirrel has been processed en the ticTac method.
     * @returns true if hasBeen processed or if it was born recently.
     */
    public void setState(boolean value){
        state = value;
    }
    
    /**
     * changeSeason change the season of Squirrel when time comes
     */
    public void changeSeason() {
        season++;
    }
    
    /**
     * Returns the specific color of this squirrel
     * @return color color of squirrel
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Makes this squirrel older 
     */
    public void getOld() {
        years++;
        switch(years){
            case 2: color = new Color(184, 83, 0); break;
            case 4: color = new Color(165, 84, 0); break;
            case 6: color = new Color(130, 66, 0); break;
            case 8: color = new Color(99, 51, 0); break;
        }
        if(years >= 10) die(); 
        
    }
    
    /**
     * Assign null to its position.
     */
    public void die(){
        forest.setThing(row, column,null);
    }
    
    /**
     * Returns the row
     * @return int That maining the specific row where is the tree
     */
    public final int getRow(){
        return row;
    }

    /**
     * Returns the column
     * @return int That maining the specifigc column where is the tree
     */
    public final int getColumn(){
        return column;
    }
    
    /**
     * Generates a new Squirrel if there is a space with other Squirrel.
     */
    public void reproduce(){
        for(int dr =-1 ; dr< 2; dr++){
            for(int dc = -1; dc < 2; dc++){
                int emptyRow = row + dr;
                int emptyCol = column + dc;
                int doubleRow = (dr * 2) + row;
                int doubleCol = (dc * 2) + column;
                    
                if(forest.isEmpty(emptyRow, emptyCol)){
                    if(forest.inForest(emptyRow, emptyCol) && forest.inForest(doubleRow, doubleCol)){
                    	Element doublePlace =  forest.getThing(doubleRow, doubleCol);
                        if(doublePlace != null && doublePlace.getClass() == this.getClass()){
                            new Squirrel(forest, emptyRow, emptyCol);
                            return;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Move to a random position;
     */
    public void move(){
        Random random = new Random();
        int dr = random.nextInt(25);
        int dc = random.nextInt(25);
        
        if(forest.isEmpty(dr, dc) && dr != row && dr  != column){
            forest.setThing(row, column, null);
            forest.setThing(dr, dc, this);
            row = dr;
            column = dc;
        }
    }
    
}