/**
 * Class of capsule submarine
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Submarine extends Machine implements Nodriz, Prepared {

    private boolean bellow8000Meters;
    private Nodriz nodriz;
    
    /**
     * Constructor for objects of class Submarine
     */
    public Submarine(int longitude, int latitude) {
       super(longitude, latitude);
    }
    
    /**
     * For definition ANY submarine is weak
     * @return false
     */
    @Override
    public boolean isWeak() {
        return false;
    }
    
    /**
     * For definition while submarine was bellow 8000 mts in sea can't be destroyed
     * @param longitude The longitude to check against the machine's position.
     * @param latitude The latitude to check against the machine's position.
     * @return false false if submarine is bellow 8000 mts in sea;
     *      true if this is over 8000 mts and this is at the longitude and latitude given.
     */
    @Override
    public boolean isAtThePosition(int longitude, int latitude) {
        return bellow8000Meters && (super.isAtThePosition(longitude, latitude));
    }
    
    public void autodestruct(String instruction) {
        if (nodriz == null) System.out.println("Destroyed, Submarine's nodriz was destroyed");
    }
    
}
