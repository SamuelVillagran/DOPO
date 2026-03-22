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

    public Thing getThing(int r, int c){
        return places[r][c];
    }

    public void setThing(int r, int c, Thing e){
        places[r][c]=e;
    }

    public void someThings(){   
        Tree bread = new Tree(this, 10, 10);
        Tree soul = new Tree(this, 15, 15);
        Squirrel sql = new Squirrel(this, 5, 5);
        Shadow thief = new Shadow(this, 0, 0);
        Shadow lass = new Shadow(this, 7, 12);
        Bear smally = new Bear(this, 5, 4);
        Bear bear = new Bear(this, 15, 16);
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
    
    protected boolean inForest(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    public void ticTac(){
        ArrayList<Thing> movingThings = new ArrayList<>(); // Esta parte fue ayudado por Claude Sonnet 4.6 IA 2026 
        for(Thing[] fileThing : places) {
            for(Thing thing : fileThing){
                if(thing instanceof Squirrel){
                   ((Squirrel) thing).setState(false);
               }
            }
        }
        
        // 1. Identificar qué hay en el bosque actualmente
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (places[r][c] != null) {
                    movingThings.add(places[r][c]); // Esta linea fue ayudado por Claude Sonnet 4.6 IA 2026 
                }
            }
        }
    
        // 2. Ejecutar el movimiento de cada uno (una sola vez)
        for (Thing t : movingThings) { // Esta parte fue ayudado por Claude Sonnet 4.6 IA 2026 
            t.ticTac();
        }
    }
    
    /**
     * Makes every cell of indexRow that is passed like parameter
     * @param indexRow indexRow is the row that going to be fulled of shadow marks
     * @param indexColumn indexColum is the column where is the shadow that produces shadow marks
     */
    public void makeFileBlack(int indexRow,int indexColumn) {
        
        for (int i = 0; i < SIZE; i++) {
            if ( !(i == indexColumn) && isEmpty(indexRow, i)) {
                places[indexRow][i] = new ShadowMark(this, indexRow, i);
            }
        }
    }
    
    /**
     * Delete every trail of shadows
     * @param indexRow indexRow is the index of row that going to be eliminated the shadows
     */
    public void deleteLastShadow(int indexRow) {
        for (int i = 0; i < SIZE; i++) {
            if (places[indexRow][i] instanceof Shadow || places[indexRow][i] instanceof ShadowMark) {
                setThing(indexRow, i, null);
            };
            
        }
    }

    public boolean isLivingThing(int indexRow, int indexColumn) {
        return places[indexRow][indexColumn] instanceof LivingThing;
    }
    
    public void makeDamageEntity(int row, int column, int pointsOfDamage) {
        if (isLivingThing(row, column)) {
            ((LivingThing) places[row][column]).makeDamage(pointsOfDamage);
        }
    }

}
