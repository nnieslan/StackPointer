package stackpointer.database;

/**
 * @author Andrew
 * 
 * This class models a row from the sxusers table in the database.
 */
public class UserEntity {

    int uid;
    String sxid;
    String username;
    String location;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSxid() {
        return sxid;
    }

    public void setSxid(String sxid) {
        this.sxid = sxid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
