package domain;

import java.awt.Color;
/**
 * This is the class shadow
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shadow implements Thing {

    private int row;
    private int column;
    private Forest forest;
    private int season; //[could be implement diferents season of shadow]
    private int tictac;
    
    /**
     * Constructor for objects of class Shadow
     */
    public Shadow(Forest forest,int row, int column) {
        this.forest=forest;
        this.row=row;
        this.column=column; 
        this.season=0;
        this.tictac=0;
        this.forest.setThing(row,column,(Thing)this);    
        this.forest.makeFileBlack(row, column);
    }
    
    /**tictac defines the movement of shadow
     * 
     */
    public void ticTac() { // Ayudado con Claude Sonnet 4.6 IA 2026, corregido y revisado
        forest.deleteLastShadow(row);
        tictac++;
        row--;
        if (row < 0) {
            row = 24;
            
        } 
        
        forest.setThing(row, column, this); 
        forest.makeFileBlack(row, column);
    }

    
    public int getRow() {
        return row;
    }
    /**
     * Actually shadow doesn't change of season
     */
    public void changeSeason() {
        
    }
    
    /**
     * Defines shape of shadow
     */
    @Override
    public int shape() {
        return Thing.ROUND;
    }
}