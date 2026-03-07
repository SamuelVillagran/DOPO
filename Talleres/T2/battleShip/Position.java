public class Position {

    private int longitude;

    private int latitude;

    public Position(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    /**
     * Moves this machine at delta logitude and latitude respective
     * @param deltaLon The longitude delta the machine will advance.
     * @param deltaLat The latitude delta the machine will advance.
     */
    public void movePlus(int deltaLon,int deltaLat) {
        if (deltaLon+longitude >= 0 && deltaLon+longitude <= 180) { 
            longitude += deltaLon; 
        } 
        
        if (deltaLat+latitude <= 90 && deltaLat+latitude >= -90) {
            latitude += deltaLat;
        } 
        
    }
    
    
    public int getLongitude() {
        return longitude;
    }
    
    public int getLatitude() {
        return latitude;
    }
}
