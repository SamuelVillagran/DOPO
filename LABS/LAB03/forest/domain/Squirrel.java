package domain;

import java.awt.Color;
/**
 * Write a description of class Squirrel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Squirrel extends LivingThing implements Thing {
    private Forest forest;
    private int season; //[pup, juvenile, adult]
    private int tictac;
    /**
     * Constructor for objects of class Squirrel
     */
    public Squirrel(Forest forest,int row, int column) {
        this.forest=forest;
        this.row=row;
        this.column=column; 
        this.color= new Color(139, 69, 19);
        this.season=0;
        this.tictac=0;
        this.forest.setThing(row,column,(Thing)this);    

    }

    @Override
    public void ticTac() {
        
    }
    
    public void changeSeason() {
        
    }
    
    public void getOld() {
        years++;
    }
}