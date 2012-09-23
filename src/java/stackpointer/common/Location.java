package stackpointer.common;

/**
 * represents a location, either for a job or a user's location
 * @author Phil
 */
public class Location {
    float lat;
    float lon;
    int zip;
    
    /**
     * init all
     * @param lat
     * @param lon
     * @param zip 
     */
    public Location(float lat, float lon, int zip) {
        this.lat = lat;
        this.lon = lon;
        this.zip = zip;
    }

    /**
     * init zip only
     * @param zip 
     */
    public Location(int zip) {
        this.zip = zip;
    }

    /**
     * init lat lon only
     * @param lat
     * @param lon 
     */
    public Location(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
