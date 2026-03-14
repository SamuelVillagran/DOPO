import java.util.Collection;

public class Ship extends Machine implements Nodriz {


    private Collection<Sailor> sailors;
    
    public Ship(int lonPos, int latPos) {
        super(lonPos, latPos);
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
    
}