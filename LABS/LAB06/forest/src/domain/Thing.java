package domain;
import java.awt.Color;

/*No olviden adicionar la documentacion*/
public abstract class Thing implements Element {
  public static final int SQUARE = 2;
  public static final int ROUND = 1;
  protected Color color;
  protected int row,column;   
  protected Forest forest;
  protected int tictac;
  protected int season;
   
  @Override
  public int shape(){
      return SQUARE;
  }
  
  public boolean isOnlyThing(){
      return true;
  }
  
  public boolean isLivingThing(){
      return false;
  }    
  
  public String getNameThing() {
	  return getClass().getSimpleName();
  }
  
  public int getRow() {
	  return row;
  }
  
  public int getColumn() {
	  return column;
  }
}