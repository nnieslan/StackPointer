package stackpointer.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import stackpointer.common.Location;
import stackpointer.jobs.IJobPostingScorer;
import stackpointer.jobs.JobPosting;
import stackpointer.jobs.JobRelevanceComparator;
import stackpointer.jobs.KeywordJobPostingScorer;
import stackpointer.jobs.Score;

/**
 * @author Andrew
 */
public class JobsDatabaseFacade {
    
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
    
    public List<JobPosting> retrieveByKeyword(String keyword) {
        List<String> keywordList = new LinkedList<String>();
        keywordList.add(keyword);
        return retrieveByKeyword(keywordList);
    }
    
    public List<JobPosting> retrieveByKeyword(List<String> keywordList) {
        List<JobPosting> jobsList = new ArrayList<JobPosting>();
        
        try {
            JobPostingRepo repo = new JobPostingRepo(
                    DatabaseConnectionInfo.createDefault());
            List<JobPostingEntity> entityList = repo.retrieve(keywordList);
            
            for (JobPostingEntity entity : entityList) {
                JobPosting jobPosting = translateToJobPosting(entity);
                jobsList.add(jobPosting);
            }
            
            List<Score> jobScoreList = new ArrayList<Score>();
            IJobPostingScorer scorer = new KeywordJobPostingScorer(keywordList);
            
            // Loop over the jobs from the database and compute a score
            for (JobPosting j : jobsList) {
                Score jobScore = new Score(j);
                jobScore.compute(scorer);
                jobScoreList.add(jobScore);
            }
            
            // Sort the jobs based on the scores we computed
            JobRelevanceComparator comparator = new JobRelevanceComparator(keywordList);
            java.util.Collections.sort(jobScoreList, comparator);
            
            // Clear the job list and re-add the jobs in sorted order
            jobsList.clear();
            for (Score s : jobScoreList) {
                jobsList.add(s.getJobPosting());
            }
            
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        return jobsList;
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
        entity.setHeadline(jobPosting.getHeadline());
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
        
        if (entity.getLocationText() != null && !entity.getLocationText().isEmpty()) {
            Location loc = new Location(entity.getLocationText());
            loc.setLat(entity.getLocationLat());
            loc.setLon(entity.getLocationLon());
            jobPosting.setLoc(loc);
        } else {
            Location loc = new Location("");
            loc.setLat(0.0);
            loc.setLon(0.0);
            jobPosting.setLoc(loc);
        }
        
        return jobPosting;
    }
    
}
