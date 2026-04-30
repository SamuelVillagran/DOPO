package domain;
import java.awt.Color;
/**
 * Write a description of class BaobabPrince here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BaobabPrince extends Tree{
    private boolean isDangerous;
    /**
     * Constructor for objects of class BaobabPrince
     */
    public BaobabPrince(Forest forest, int row, int column)
    {
        super(forest, row, column);
        this.color = new Color(143, 184, 255);
        this.isDangerous = false;
    }
    
    @Override
    public void ticTac(){
        tictac++;
        color=(tictac % 4==0? new Color(143, 184, 255):
               tictac % 4==1? new Color(105, 160, 255):
               tictac % 4==2? new Color(46, 124, 255):
               new Color(46, 124, 255));
        if (tictac % 4 == 1){
            years+=1;
            if(years >= 10 && !isDangerous) setDangerous(true);
        }
        
        if(isDangerous) generateDamage();
        
        if (tictac % 4 == 3){
            boolean OK=step();
            if (! OK){
                die();
            }
        }
    }
    
    /**
     * Set the state for danger mode.
     * @return true if the baobab has more or equals than 10 year, otherwise returns false.
     */
    private void setDangerous(boolean danger){
        isDangerous = danger;
    }
    
    /**
     * Make damage to neighbors tree but not to its same specie.
     */
    private void generateDamage(){
        for(int r = -1; r < 2; r++){
            for(int c = -1; c < 2; c++){
                int dr = r + row;
                int dc = c + column;
                if(forest.inForest(dr,dc)){
                    Thing thing = forest.getThing(dr, dc);
                    if(thing != null && !(thing instanceof BaobabPrince) && (thing instanceof DefaultTree)){
                        Tree enemy = (Tree) thing;
                        for(int i = 0; i <= 4; i++){
                            if(!enemy.step()){
                                enemy.die();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean getIsDangerous(){
        return isDangerous;
    }
}