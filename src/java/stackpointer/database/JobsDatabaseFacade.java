package stackpointer.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import stackpointer.common.Location;
import stackpointer.jobs.JobPosting;

/**
 * @author Andrew
 */
public class JobsDatabaseFacade {
    
    public JobsDatabaseFacade() {
        
    }
    
    /**
     * Synchronizes the passed list of job postings with the database.
     * New jobs postings are added and existing or duplicates are ignored.
     * 
     * @param jobsList List of job postings, likely retrieved from the API
     * @return the number of new jobs added to the database, or -1 for failure
     */
    public int syncJobPostings(List<JobPosting> jobsList) {        
        int numAdded = 0;
        Connection connection = null;
        
        if (jobsList == null) {
            return 0;
        }
        
        try {
            connection = DBUtils.openConnection(
                    DatabaseConnectionInfo.createDefault());
            if (connection == null) {
                return -1;
            }
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println("Failed to open/initialize database connection");
            System.err.println(ex);
        }
        
        try {            
            JobPostingRepo repo = new JobPostingRepo(connection);
            
            for (JobPosting jobPosting : jobsList) {
                // Skip adding or updating jobs that we already have
                if (!repo.exists(jobPosting.getLinkedInId())) {
                    JobPostingEntity entity = translateToEntity(jobPosting);
                    repo.add(entity);
                    numAdded++;
                }
            }
            
        } catch (SQLException ex) {
            try {
                connection.rollback();
                System.err.println("rolling back syncJobPostings transaction");
                System.err.println("SQL Exception occurred");
                System.err.println(ex);
            } catch (SQLException rollbackEx) {
                System.err.println("Failed to rollback syncJobPostings transaction");
                System.err.println(rollbackEx);
            }
            DBUtils.logMessageToDatabase("SQLException occurred in syncJobPostings");
            return -1;
        }
        
        String message = String.format("%d jobs added to the database", numAdded);
        System.out.println(message);
        DBUtils.logMessageToDatabase(message);

        return numAdded;
    }
    
    /**
     * Retrieves all job postings stored in the database.
     * At some point we'll put a filter on this given that we don't need
     * to retrieve every job under the sun.
     * 
     * @return List of job postings from the database
     */
    public List<JobPosting> retrieveAllJobPostings() {
        List<JobPosting> jobsList = new ArrayList<JobPosting>();
        
        try {
            JobPostingRepo repo = new JobPostingRepo(
                    DatabaseConnectionInfo.createDefault());
            List<JobPostingEntity> entityList = repo.retrieve();
            
            for (JobPostingEntity entity : entityList) {
                JobPosting jobPosting = translateToJobPosting(entity);
                jobsList.add(jobPosting);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        return jobsList;
    }
    
    /**
     * Translate from a domain model object to a database object.
     *
     * @param jobPosting
     * @return 
     */
    private JobPostingEntity translateToEntity(JobPosting jobPosting) {
        JobPostingEntity entity = new JobPostingEntity();
        
        entity.setJpid(jobPosting.getLinkedInId());
        entity.setDatePosted(jobPosting.getDatePosted());
        entity.setHeadline(jobPosting.getLoc().toString());
        entity.setCompany(jobPosting.getCompany());
        entity.setDescription(jobPosting.getDescription());
        
        Location location = jobPosting.getLoc();
        if (location != null) {
            entity.setLocationText(location.getLocStr());
            entity.setLocationLat(location.getLat());
            entity.setLocationLon(location.getLon());
        } else {
            entity.setLocationText(null);
            entity.setLocationLat(0.0);
            entity.setLocationLon(0.0);
        }
        
        return entity;
    }
    
    /**
     * Translate from a database object to a domain model object.
     * 
     * @param entity
     * @return 
     */
    private JobPosting translateToJobPosting(JobPostingEntity entity) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setLinkedInId(entity.getJpid());
        jobPosting.setDatePosted(entity.getDatePosted());
        jobPosting.setHeadline(entity.getHeadline());
        jobPosting.setCompany(entity.getCompany());
        jobPosting.setDescription(entity.getDescription());
        return jobPosting;
    }
    
}
