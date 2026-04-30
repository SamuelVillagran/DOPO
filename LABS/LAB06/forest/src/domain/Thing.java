package domain;
import java.awt.Color;

/*No olviden adicionar la documentacion*/
public abstract class Thing implements Element {
  public static final int SQUARE = 2;
  public static final int ROUND = 1;
  protected int tictac;
  protected int season;
   
  @Override
  public int shape(){
      return SQUARE;
  }
  
  public Color getColor(){
      return Color.black;
  }
  
  public boolean isOnlyThing(){
      return true;
  }
  
  public boolean isLivingThing(){
      return false;
  }    
    
  public abstract void changeSeason(); 
  
}
