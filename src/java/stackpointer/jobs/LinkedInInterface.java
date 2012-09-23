package stackpointer.jobs;

import java.util.ArrayList;
import java.util.Date;
import stackpointer.common.Location;

/**
 * This class is to interact with the Linked In API, including retrieval
 * of data, and parsing it.
 * @author Nathan
 */
public class LinkedInInterface {
    private boolean connected;
    private ArrayList<JobPosting> allJobPostings;

    LinkedInInterface()
    {
        //TODO - Initialize values
        allJobPostings = new ArrayList<JobPosting>();
    }

    void establishConnection()
    {
        //TODO - input connection steps and set boolean result value
        connected = false;
    }

    //Simple getter function to ensure that connection is valid
    boolean isConnectionEstablished()
    {
        return connected;
    }
    
    //Function to repopulate local LinkedIn user database
    boolean updateLocalDatabase()
    {
        //TODO - access LinkedIn, grab user and friends and save data
        return false;
    }

    //Function to update current values from LinkedIn in local database
    boolean cleanDatabase()
    {
        //TODO - validate all current local data
        return false;
    }

    //Update the local copies of the top 100 questions
    void updateJobPostings()
    {
        //TODO - access database, grab 100 questions, push onto list
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
    }

    //Return the top 100 questions
    ArrayList<JobPosting> getJobPostings()
    {
        return allJobPostings;
    }
}
