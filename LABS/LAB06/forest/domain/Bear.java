package domain;

import java.awt.Color;
import java.util.Random;

/**
 * This is a grizzly bear hungry to eat Squirrels
 * and attack Trees
 * 
 * @author Villagran
 * @version 1.0
 */
public class Bear extends LivingThing implements Thing {
    private Forest forest;
    private int tictac;
    private int season; // Hunt 0, hibernation 1
    public static final int DAMAGE = 3;

    /**
     * Constructor for objects of class Bear
     */
    public Bear(Forest forest,int row, int column) {
        this.forest=forest;
        this.row=row;
        this.column=column; 
        this.color= Color.RED;
        this.season=0;
        this.tictac=0;
        this.forest.setThing(row, column, (Thing)this);
    }
    
    
    /**
     * Makes the logic of ticTac when 
     * tictac's button is pressed
     */
    public void ticTac() {
        tictac++;
        
        if (tictac % 4 == 0) {
            getOld(); 
            
            boolean OK = step();
            if (!OK) die();
        } 
        
        move();
    }
    
    
    
    /**
     * Get the bear's color
     * @return color Bear's color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Makes bear older
     */
    public void getOld() {
        years += 2;
    }

    /**
     * When bear is hunting its color is more vivid
     * When bear is hibernating its color is darker
     * This depends of season 
     */
    public void changeSeason() {
        
        season = tictac%4 == 2 ? 1 : 0;
        
        color = season == 1 ?  new Color(102, 51, 0) :
                Color.RED; 
                
    }
    
    /**
     * Makes move bear on a directon of 4x4 cells 
     */
    public void move() {
        Random rd = new Random();
        int delta = rd.nextInt(4) - 2;
        
        do {
            delta = rd.nextInt(4) - 2; // Linea ayudada con IA
        } while (delta == 0);
        
        
        int dr = row + delta, dc = column + delta;
        
        changeSeason();
        
        boolean areThereEntity = forest.isEmpty(dr, dc);
        
        if(areThereEntity && dr != row && dc != column
            && season != 1) {
            forest.setThing(row, column, null);
            forest.setThing(dr, dc, this);
            row = dr;
            column = dc;
            attack();
        }
    }
    
    /**Die, makes bear null in the board
     */
    public void die(){
        forest.setThing(row, column,null);
    }
    
    /**Returns the shape
    @return integer that it's 1, their main is the type of figure round
     */
    public final int shape(){
        return Thing.ROUND;
    }
    
    /**
     * Verify if bear can attack something
     * and attack if this can
     */
    public void attack() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int deltaRow = row - i, deltaColumn = column - j;
                boolean entityInBounds = forest.inForest(deltaRow, deltaColumn);
                boolean selfPosition = i == 0 && j == 0;
                if (entityInBounds && !forest.isEmpty(deltaRow, deltaColumn) && !selfPosition) {
                    forest.makeDamageEntity(deltaRow, deltaColumn, DAMAGE);
                }
            }
        }
    }
    
    /**
     * Get bear's row
     * @return row bear's row
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Get bear's column
     * @return column bear's column
     */
    public int getColumn() {
        return column;
    }
}