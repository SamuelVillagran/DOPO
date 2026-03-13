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
}