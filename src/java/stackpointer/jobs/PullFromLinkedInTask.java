package stackpointer.jobs;

import java.util.ArrayList;
import java.util.TimerTask;
import stackpointer.database.JobsDatabaseFacade;

/**
 * @author Andrew
 */
public class PullFromLinkedInTask extends TimerTask {

    @Override
    public void run() {
        try {    
            
            ArrayList<JobPosting> jobPostingList = LinkedInInterface.getJobPostings();
            
            if (jobPostingList != null && !jobPostingList.isEmpty()) {
                
                JobsDatabaseFacade db = new JobsDatabaseFacade();
                int numAdded = db.syncJobPostings(jobPostingList);
            } else {
                System.out.println("No job postings were retrieved from the LinkedIn API");
            }
            
        } catch (Exception ex) {
            System.err.println("Failed to retrieve job postings from the LinkedIn API");
            System.err.println(ex);
        }
    }
    
}
