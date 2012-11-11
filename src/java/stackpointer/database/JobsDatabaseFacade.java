package stackpointer.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import stackpointer.jobs.JobPosting;

/**
 * @author Andrew
 */
public class JobsDatabaseFacade {

    private JobPostingRepo repo = null;
    
    public JobsDatabaseFacade() throws SQLException {
        repo = new JobPostingRepo(DatabaseConnectionInfo.createDefault());
    }
    
    public boolean syncJobPostings(List<JobPosting> jobsList) {        
        try {
            for (JobPosting jobPosting : jobsList) {
                // Skip adding or updating jobs that we already have
                if (!repo.exists(jobPosting.getLinkedInId())) {
                    JobPostingEntity entity = translateToEntity(jobPosting);
                    repo.add(entity);
                }
            }
            
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }

        return true;
    }
    
    public List<JobPosting> retrieveAllJobPostings() {
        List<JobPosting> jobsList = new ArrayList<JobPosting>();
        
        try {
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
    
    private JobPostingEntity translateToEntity(JobPosting jobPosting) {
        JobPostingEntity entity = new JobPostingEntity();
        entity.setJpid(jobPosting.getJpid());
        entity.setLinkedinId(jobPosting.getLinkedInId());
        entity.setDatePosted(jobPosting.getDatePosted());
        entity.setHeadline(jobPosting.getLoc().toString());
        entity.setCompany(jobPosting.getCompany());
        entity.setDescription(jobPosting.getDescription());
        return entity;
    }
    
    private JobPosting translateToJobPosting(JobPostingEntity entity) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJpid(entity.getJpid());
        jobPosting.setLinkedInId(entity.getLinkedinId());
        jobPosting.setDatePosted(entity.getDatePosted());
        jobPosting.setHeadline(entity.getHeadline());
        jobPosting.setCompany(entity.getCompany());
        jobPosting.setDescription(entity.getDescription());
        return jobPosting;
    }
    
}
