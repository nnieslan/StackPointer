package stackpointer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Andrew
 */
public class JobPostingRepo extends DatabaseRepository<JobPostingEntity> {

    public JobPostingRepo(DatabaseConnectionInfo connectionInfo) throws SQLException {
        super(connectionInfo);
    }

    public JobPostingRepo(Connection connection) throws SQLException {
        super(connection);
    }
    
    public boolean exists(int linkedInId) {
        boolean exists = false;
        
        if (linkedInId <= 0) {
            return false;
        }
        
        if (connection != null) {
            String queryText =
                    "SELECT COUNT(*) " +
                    "FROM jobpostings " +
                    "WHERE linkedinid = ? " +
                    "LIMIT 1";
            
            try {
                PreparedStatement stmt = connection.prepareStatement(queryText);
                stmt.setInt(1, linkedInId);
                
                ResultSet results = stmt.executeQuery();
                if (results.next()) {
                    int numRows = results.getInt(1);
                    exists = (numRows > 0);
                } else {
                    exists = false;
                }
            } catch (SQLException ex) {
                System.err.println(ex);
                return false;
            }
        }
        
        return exists;
    }

    @Override
    public boolean add(JobPostingEntity jobPostingEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String insertText =
                    "INSERT INTO jobpostings "
                    + "(date_posted, headline, description, company, location) "
                    + "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(
                    insertText, Statement.RETURN_GENERATED_KEYS);
            
            java.sql.Date postedDate = DBUtils.utilDateToSqlDate(
                    jobPostingEntity.getDatePosted());
            stmt.setDate(1, postedDate);
            stmt.setString(2, jobPostingEntity.getHeadline());
            stmt.setString(3, jobPostingEntity.getDescription());
            stmt.setString(4, jobPostingEntity.getCompany());
            stmt.setString(5, jobPostingEntity.getLocation());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);

            // Grab the id and store it on the job posting
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int uid = rs.getInt(1);
                jobPostingEntity.setJpid(uid);
            } else {
                jobPostingEntity.setJpid(0);
            }
        }
        
        return success;
    }

    @Override
    public boolean update(JobPostingEntity jobPostingEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "UPDATE jobpostings " +
                    "SET " +
                    "date_posted = ?, " +
                    "headline = ?, " +
                    "description = ?, " +
                    "company = ?, " +
                    "location = ? " +
                    "WHERE jpid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            java.sql.Date postedDate = DBUtils.utilDateToSqlDate(
                    jobPostingEntity.getDatePosted());
            
            stmt.setDate(1, postedDate);
            stmt.setString(2, jobPostingEntity.getHeadline());
            stmt.setString(3, jobPostingEntity.getDescription());
            stmt.setString(4, jobPostingEntity.getCompany());
            stmt.setString(5, jobPostingEntity.getLocation());
            stmt.setInt(6, jobPostingEntity.getJpid());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
    }

    @Override
    public boolean delete(JobPostingEntity jobPostingEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "DELETE FROM jobpostings " +
                    "WHERE jpid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            stmt.setInt(1, jobPostingEntity.getJpid());
            int rowsDeleted = stmt.executeUpdate();
            success = (rowsDeleted == 1);
        }
        
        // reset the database id
        jobPostingEntity.setJpid(0);

        return success;
    }
    
    public List<JobPostingEntity> retrieve() throws SQLException {
        // retrieve all
        List<JobPostingEntity> jobList = select("1=1");
        return jobList;
    }
    
    private List<JobPostingEntity> select(String whereClause) throws SQLException {
        List<JobPostingEntity> jobList = new ArrayList<JobPostingEntity>();
        
        // If no where clause is specified, we no query will be made.
        if (whereClause == null || whereClause.isEmpty()) {
            return null;
        }
        
        String queryText =
                "SELECT jpid, date_posted, headline, description, " +
                "company, location " +
                "FROM jobpostings " +
                "WHERE " + whereClause;
        
        if (super.connection != null && !super.connection.isClosed()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(queryText);

            while (results.next()) {
                int jpid = results.getInt("jpid");
                Date datePosted = results.getDate("date_posted");
                String headline = results.getString("headline");
                String description = results.getString("description");
                String company = results.getString("company");
                String location = results.getString("location");
                JobPostingEntity jobEntity = new JobPostingEntity();
                jobEntity.setJpid(jpid);
                jobEntity.setDatePosted(datePosted);
                jobEntity.setHeadline(headline);
                jobEntity.setDescription(description);
                jobEntity.setCompany(company);
                jobEntity.setLocation(location);
                jobList.add(jobEntity);
            }
        }
        
        return jobList;
    }

}
