package domain;
import java.awt.Color;


public abstract class LivingThing extends Thing {
    
    protected int row,column;    
    protected Color color;
    protected int years;
    private int energy;

    /**Create a new LivingThing
     * 
     */
    public LivingThing(){
        energy=100;
        years=0;
    }

    /**The LivingThing makes one step
     * 
     */
    final boolean step(){
        boolean ok=false;
        if (energy>=1){
            energy-=1;
            ok=true;
        }
        return ok;
    }    
    
    public final int getYears(){
        return years;
    }
    
    /**
     * Makes damage to entitys
     */
    public void makeDamage(int pointsDamage) {
        energy -= pointsDamage;
    }
    
     /**Returns the energy
    @return 
     */   
    public final int getEnergy(){
        return energy;
    }    

    /**It's an LivingThing
     */
    public final boolean isLivingThing(){
        return true;
    }  
    
    public abstract void getOld();
    
}
