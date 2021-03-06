package stackpointer.common;

/**
 * represents a location, either for a job or a user's location
 * @author Phil
 */
public class Location {
    String locStr; //the stack exchange string user provided as location
    double lat;
    double lon;
    int zip;
    
    public Location(String locStr)
    {
        this.locStr = locStr;
    }
    
    /**
     * init all
     * @param lat
     * @param lon
     * @param zip 
     */
    public Location(double lat, double lon, int zip) {
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
    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    
    public String getLocStr() {
        return locStr;
    }

    public void setLocStr(String locStr) {
        this.locStr = locStr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        
        Location otherLocation = (Location)o;
        return this.locStr.equals(otherLocation.locStr);
    }
    
    public String toDebugString() {
        return "Location{" + "locStr=" + locStr + ", lat=" + lat + ", lon=" + lon + ", zip=" + zip + '}';
    }

    @Override
    public String toString() {
        return locStr;
    }
}
