public class Plane extends Machine implements Prepared {

    private String plate;
    private boolean inAir;
    private Sailor pilot;
    private Sailor copilot;

    public Plane(int lonPos, int latPos) {
        super(lonPos, latPos);
    }
    
    /**
     * Verifies if the given longitude and latitude match this 
     * machine's position and checks if the plane is grounded.
     * @param longitude The longitude to verify against the machine's position.
     * @param latitude The latitude to verify against the machine's position.
     * @return true if the plane is not in the air and the given 
     * coordinates match the machine's position; 
     *      false otherwise.
     */
    @Override
    public boolean isAtThePosition(int longitude, int latitude) {
        Position thisLocation = getLocation();
        return !inAir && (thisLocation.atThePosition(longitude, latitude)) ? true : false;
    }
    
    /**
     * Consults if this is a weak Plane.  
     * @return true if this plane has no main pilot;
     *      false  otherwise.
     */
    @Override
    public boolean isWeak() {
        return pilot==null ? true : false;
    }
    
    public void autodestruct(String instruction) {
        if (instruction.equals("auto-destruct")) {
            System.out.println("Destroying...");
            System.out.println("Destroyed...");
            System.out.println("Auto-destruct was a instruction given by this, this machine was destroyed.");
        }
    }
}
