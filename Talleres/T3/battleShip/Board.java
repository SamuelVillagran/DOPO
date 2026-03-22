import java.util.ArrayList;

public class Board {

    private ArrayList<Fleet> fleets;
    public static final int LONGITUDE_MAX = 180;
    public static final int LONGITUDE_MIN = 0;
    public static final int LATITUDE_MAX = 90;
    public static final int LATITUDE_MIN = -90;
    
    public ArrayList<Machine> machinesAtPosition(int longitude, int latitude) {
        ArrayList<Machine> machinesAtPos = new ArrayList<>();
        for (Fleet f : fleets) {
            for (Machine m : f.getMachines()) {
                if (m.isAtThePosition(longitude, latitude)) machinesAtPos.add(m);
            }
        }
        return machinesAtPos;
    }
    
    /**
     * Consults the number of fleets that completed a full movement
     * @return int count of fleets that completed a full movement 
     */
    public int toNorth() {
        int count = 0;
        for (Fleet f : fleets) {
            if (f.getFullMovements() > 0) count++;
        }
        return count;
    }
    /**
     * Consults the fleets that have infiltrated pilots. 
     * @return  The fleets with infiltrated pilots
     */
    public ArrayList<Fleet> infiltrated() {
        ArrayList<Fleet> fleetsWithInfiltrated = new ArrayList<>();
        for (Fleet f : fleets) {
            if (f.hasInfiltratedPilots()) fleetsWithInfiltrated.add(f);
        }
    }
    
    public ArrayList<Fleet> getFleets() {
        return fleets;
    }
}