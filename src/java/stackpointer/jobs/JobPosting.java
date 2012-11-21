package stackpointer.jobs;

import java.util.Date;
import stackpointer.common.Location;

/**
 *
 * @author Phil
 */
public class JobPosting {
    int linkedInId;
    Location loc;
    Date datePosted;
    String headline;
    String description;
    String company;

    public JobPosting() {
    }

    
    public JobPosting(Location loc, Date datePosted, String headline, String description, String company) {
        this.loc = loc;
        this.datePosted = datePosted;
        this.headline = headline;
        this.description = description;
        this.company = company;
    }
    
    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
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

    public int getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(int linkedInId) {
        this.linkedInId = linkedInId;
    }
    
    public boolean hasLocation() {
        boolean hasLoc = false;
        if(this.getCompany()!=null)
        {
            if(this.getLoc()!=null)
            {
                hasLoc = true;
            }
        }
        return hasLoc;
    }


    @Override
    public String toString() {
        return headline;
    }
    
}
