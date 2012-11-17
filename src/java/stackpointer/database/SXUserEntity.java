package stackpointer.database;

/**
 * @author Andrew
 * 
 * This class models a row from the sxusers table in the database.
 */
public class SXUserEntity implements DBEntity {
    
    private final int MaxDisplayNameLen = 50;
    
    int uid;
    String displayName;
    String locationText;
    double locationLat;
    double locationLon; 

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() {
        if (displayName != null && displayName.length() > MaxDisplayNameLen) {
            displayName = displayName.substring(0, MaxDisplayNameLen-1);
        }
    }
    
}
