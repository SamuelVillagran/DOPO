public class Machine {

    private Position location;

    
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
    
}
