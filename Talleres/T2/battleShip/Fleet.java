import java.util.ArrayList;

public class Fleet {

    private String name;
    private ArrayList<Machine> machines;
    private ArrayList<Sailor> sailors;
    private Board board;
    
    public Fleet() {
        machines = new ArrayList<>();
    }
    
    /**
     * Moves all machines the defined distance. The world board is circular.
     * @param dLon The longitude delta the machine will advance.
     * @param dLat The latitude delta the machine will advance.
     */
    public void advance(int dLon, int dLat) {
        for (Machine m : machines) {
            m.move(dLon, dLat);
        }
    }
    
    public void addMachine(int lonPos, int latPos) {
        machines.add(new Machine(lonPos, latPos));
    }
    
    public Machine getASpeciefiedMachine(int index) {
        return machines.get(index);
    }
    
    /**
     * Consults the machines that may be affected by an explosion at the given position.  
     * Many machines can be at one coordinate. Airplanes in the air are not destroyed. 
     * @param longitude The longitude used to verify if a machine is at that location.
     * @param latitude The latitude used to verify if a machine is at that location.
     * @return An ArrayList of machines located at the specified longitude and latitude.
     */
    public ArrayList<Machine> willBeDestroyed(int longitude, int latitude) {
        ArrayList<Machine> res = new ArrayList<>();
        for (Machine m : machines) {
            if (m.isAtThePosition(longitude, latitude)) res.add(m);    
        }
        return res;
    }
    
    /**
     * Consults the weak machines of a fleet. A ship is weak if it has fewer than five sailors; 
     * an airplane, if it has no main pilot; and an aircraft carrier, 
     * if it is a weak ship or any of its airplanes in the air is weak.
     * @return ArrayList with the weak machines
     */
    public ArrayList<Machine> weakMechines() {
        ArrayList<Machine> res = new ArrayList<>();
        for (Machine m : machines) {
            if (m.isWeak()) res.add(m);
        }
        return res;
    }
}
