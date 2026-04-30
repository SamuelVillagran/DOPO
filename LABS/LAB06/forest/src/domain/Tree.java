package domain;
import java.awt.Color;

/**
 * The Tree class represents the standard performance of a Tree.
 */
public abstract class Tree extends LivingThing{
    
    /**Create a new Tree(<b>row,column</b>) in the forest <b>forest</b>..
     * @param forest It's the fores where belog this tree
     * @param row It's the specific row where going to be this tree
     * @param column It's the specific column where going to be this tree
     */
    public Tree(Forest forest,int row, int column){
        this.forest=forest;
        this.row=row;
        this.column=column; 
        this.color=Color.PINK;
        this.season=0;
        this.tictac=0;
        this.forest.setThing(row,column,(Thing)this);    
    }
    
    /**Returns the row
    @return int That maining the specific row where is the tree
     */
    public final int getRow(){
        return row;
    }

    /**Returns the column
    @return int That maining the specifigc column where is the tree
     */
    public final int getColumn(){
        return column;
    }

    /**Returns the color
    @return color Returns color of tree, following tictac
     */
    public final Color getColor(){
        return color;
    }

    /**Returns the shape
    @return integer that it's 1, their main is the type of figure round
     */
    public final int shape(){
        return Thing.ROUND;
    }
      
    /**Die, makes tree null in the board
     */
    public void die(){
        forest.setThing(row, column,null);
    }
    
    /**
     * Change season of tree
     */
    public void changeSeason() {
        season++;
    }
    
    /**ticTac change the state and color of tree
     */
    public void ticTac(){
        tictac++;
        color=(tictac % 4==0? Color.PINK:
               tictac % 4==1? Color.GREEN:
               tictac % 4==2? Color.ORANGE:
               Color.GRAY);
        if (tictac % 4 == 1){
            years+=1;
        }
        if (tictac % 4 == 3){
            boolean OK=step();
            if (! OK){
                die();
            }
        }
    }
}
