package domain;
import java.awt.Color;

/**Information about a Tree<br>
<b>(forest,row,column,color)</b><br>
<br>
 */
public class DefaultTree extends Tree {
    /**Create a new Tree(<b>row,column</b>) in the forest <b>forest</b>..
     * @param forest It's the fores where belog this tree
     * @param row It's the specific row where going to be this tree
     * @param column It's the specific column where going to be this tree
     */
    public DefaultTree(Forest forest,int row, int column){
        super(forest, row, column); 
        this.color=Color.PINK;   
    }

    
}
