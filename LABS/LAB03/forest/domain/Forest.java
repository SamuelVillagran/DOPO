package domain;
import java.util.*;

/*No olviden adicionar la documentacion*/
public class Forest{
    static private int SIZE=25;
    private Thing[][] places;
    
    public Forest() {
        places=new Thing[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                places[r][c]=null;
            }
        }
        someThings();
    }

    public int  getSize(){
        return SIZE;
    }

    public Thing getThing(int r,int c){
        return places[r][c];
    }

    public void setThing(int r, int c, Thing e){
        places[r][c]=e;
    }

    public void someThings(){   
        Tree bread = new Tree(this, 10, 10);
        Tree soul = new Tree(this, 15, 15);
        Squirrel sql = new Squirrel(this, 5, 5);
        Shadow shadow = new Shadow(this, 0, 0);
    }
    
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inForest(r,c) && places[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inForest(r+dr,c+dc) && 
                    (places[r+dr][c+dc]!=null) &&  (places[r][c].getClass()==places[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    }
   

    public boolean isEmpty(int r, int c){
        return (inForest(r,c) && places[r][c]==null);
    }    
        
    private boolean inForest(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
   
    public void ticTac(){
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++){
                Thing thing = places[r][c];
                if (thing != null) {
                    thing.ticTac();
                }
            }
    }
    }
    
    public void makeFileBlack(int indexRow,int indexColumn) {
        
        for (int i = 0; i < SIZE; i++) {
            if ( !(i == indexColumn) && isEmpty(indexRow, i)) {
                places[indexRow][i] = new ShadowMark(this, indexRow, i);
            }
        }
    }
    
    public void deleteLastShadow(int indexRow) {
        for (int i = 0; i < SIZE; i++) {
            if (places[indexRow][i] instanceof Shadow || places[indexRow][i] instanceof ShadowMark) {
                setThing(indexRow, i, null);
            };
            
        }
    }

}
