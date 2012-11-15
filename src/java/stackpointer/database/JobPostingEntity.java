package stackpointer.database;

import java.util.Date;

/**
 * @author Andrew
 * 
 * This class models a row from the jobpostings table in the database.
 */
public class JobPostingEntity {
    
    int jpid;
    Date datePosted;
    String headline;
    String description;
    String company;
    String locationText;
    double locationLat;
    double locationLon;

    public int getJpid() {
        return jpid;
    }

    public void setJpid(int jpid) {
        this.jpid = jpid;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
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
