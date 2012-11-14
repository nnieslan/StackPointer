package stackpointer.database;

/**
 * @author Andrew
 * 
 * This class models a row from the sxusers table in the database.
 */
public class SXUserEntity {

    int uid;
    int sxid;
    String username;
    String locationText;
    double locationLat;
    double locationLon; 

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSxid() {
        return sxid;
    }

    public void setSxid(int sxid) {
        this.sxid = sxid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocationText() {
        return locationText;
    }

    public void setLocationText(String locationText) {
        this.locationText = locationText;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public double getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(double locationLon) {
        this.locationLon = locationLon;
    }
    
}
