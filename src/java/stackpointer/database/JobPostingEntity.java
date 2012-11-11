package stackpointer.database;

import java.util.Date;

/**
 * @author Andrew
 * 
 * This class models a row from the jobpostings table in the database.
 */
public class JobPostingEntity {
    
    int jpid;
    int linkedinId;
    Date datePosted;
    String headline;
    String description;
    String company;
    String location;

    public int getJpid() {
        return jpid;
    }

    public void setJpid(int jpid) {
        this.jpid = jpid;
    }
    
    public int getLinkedinId() {
        return linkedinId;
    }

    public void setLinkedinId(int linkedinId) {
        this.linkedinId = linkedinId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
