import java.util.ArrayList;

public class AircraftCarrier extends Ship {

    private int number;
    private int capacity;
    private ArrayList<Plane> airPlanes;

    /**
     * Construct's AircraftCarrier
     */
    public AircraftCarrier(int longitude, int latitude) {
        super(longitude, latitude);
    }
    
    /**
     * Consults if this is a weak AircraftCarrier.  
    
     * @return true if it is a weak ship or any of its airplanes in the air is weak.
     *      false  otherwise.
     */
    @Override
    public boolean isWeak() {
        return super.isWeak() || anyAirPlaneIsWeak() ? true : false;
    }
    
    /*
     * Verify if any air plane is weak.
     * @return true if some plane of this aircraftcarrier is weak;
     *      false otherwise.
     */
    private boolean anyAirPlaneIsWeak() {
        for (Plane p : airPlanes) {
            if (p.isWeak()) return true;
        }
        return false;
    }
}
