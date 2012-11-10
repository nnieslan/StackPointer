package stackpointer.database;

import java.util.Date;

/**
 * @author Andrew
 */
public class JobPostingEntity {
    
    int jpid;
    Date postedTimestamp;
    String headline;
    String description;
    String company;
    int locationId;

    public int getJpid() {
        return jpid;
    }

    public void setJpid(int jpid) {
        this.jpid = jpid;
    }

    public Date getPostedTimestamp() {
        return postedTimestamp;
    }

    public void setPostedTimestamp(Date postedTimestamp) {
        this.postedTimestamp = postedTimestamp;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
}
