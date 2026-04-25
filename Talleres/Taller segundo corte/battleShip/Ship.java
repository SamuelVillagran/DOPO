import java.util.Collection;

public class Ship extends Machine implements Nodriz, Prepared{

    private Collection<Sailor> sailors;
    private boolean isDestroyed;
    private String cause;
    
    public Ship(int lonPos, int latPos) {
        super(lonPos, latPos);
        isDestroyed = false;
    }
    
    /**
     * Consults if this is a weak machine. 
     * @return true if this ship has fewer than five sailors; 
     *      false otherwise
     */
    @Override
    public boolean isWeak() {
        return sailors.size() < 5 ? true : false;
    }
    
    @Override
    public void autodestruct(String cause){
        this.cause = cause;
        isDestroyed = true;
    }
    
    @Override
    public boolean isDestroyed(){
        return isDestroyed();
    }
    
    @Override
    public String destructionCause(){
        return cause;
    }
}