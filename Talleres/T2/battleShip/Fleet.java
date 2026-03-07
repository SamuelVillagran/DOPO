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
}
