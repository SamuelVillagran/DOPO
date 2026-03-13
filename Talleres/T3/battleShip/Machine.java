import java.util.ArrayList;

public class Machine {

    private Position location;

    /**
     * Constructor's machine
     */
    public Machine(int lonPos, int latPos) {
        location = new Position(lonPos, latPos);
    }

    /**
     * Moves this machine at delta logitude and latitude respective
     * @param deltaLon The longitude delta the machine will advance.
     * @param deltaLat The latitude delta the machine will advance.
     */
    public void move(int deltaLon,int deltaLat) {
        location.movePlus(deltaLat, deltaLon);
    }
    
    public int getLatitudeMachine() {
        return location.getLatitude();
    }
    
    public int getLongitudeMachine() {
        return location.getLongitude();
    }
    
    /**
     * Defines if a machine has weak caracteristics
     * @return false This is the definition of a machine
     */
    public boolean isWeak() {
        return false;
    }
    
    /**
     * Verifies if this machine is located at the specified 
     * longitude and latitude coordinates.
     * @param longitude The longitude to check against the machine's position.
     * @param latitude The latitude to check against the machine's position.
     * @return true if the given coordinates match the machine's position;
     *      false if the given coordinates are diferent to this machine's position.
     */
    public boolean isAtThePosition(int longitude, int latitude) {
        return location.atThePosition(longitude, latitude);
    }
    
    public Position getLocation() {
        return location;
    }
}
