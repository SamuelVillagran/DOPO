package domain;

import java.awt.Color;
/**
 * Write a description of class Shadow here.
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
    
    public void ticTac() { // Ayudado con Claude Sonnet 4.6 IA 2026
        forest.deleteLastShadow(row);
        tictac++;

        if (row == 0) {
            row = 24;
        } else {
            row--;
        }
        forest.setThing(row, column, this); 
        forest.makeFileBlack(row, column);
    }

    public void changeSeason() {
        
    }
    
    @Override
    public int shape() {
        return Thing.ROUND;
    }
}