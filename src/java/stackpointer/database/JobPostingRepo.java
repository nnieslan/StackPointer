package stackpointer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                    + "(date_posted, linkedinid, headline, description, "
                    + "company, location_text, location_lat, location_lon) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(
                    insertText, Statement.RETURN_GENERATED_KEYS);
            
            java.sql.Date postedDate = DBUtils.utilDateToSqlDate(
                    jobPostingEntity.getDatePosted());
            stmt.setDate(1, postedDate);
            stmt.setInt(2, jobPostingEntity.getLinkedinId());
            stmt.setString(3, jobPostingEntity.getHeadline());
            stmt.setString(4, jobPostingEntity.getDescription());
            stmt.setString(5, jobPostingEntity.getCompany());
            stmt.setString(6, jobPostingEntity.getLocationText());
            stmt.setDouble(7, jobPostingEntity.getLocationLat());
            stmt.setDouble(8, jobPostingEntity.getLocationLon());
            
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
                    "linkedinid = ?, " +
                    "headline = ?, " +
                    "description = ?, " +
                    "company = ?, " +
                    "location_text = ?, " +
                    "location_lat = ?, " +
                    "location_lon = ? " +
                    "WHERE jpid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            java.sql.Date postedDate = DBUtils.utilDateToSqlDate(
                    jobPostingEntity.getDatePosted());
            
            stmt.setDate(1, postedDate);
            stmt.setInt(2, jobPostingEntity.getLinkedinId());
            stmt.setString(3, jobPostingEntity.getHeadline());
            stmt.setString(4, jobPostingEntity.getDescription());
            stmt.setString(5, jobPostingEntity.getCompany());
            stmt.setString(6, jobPostingEntity.getLocationText());
            stmt.setDouble(7, jobPostingEntity.getLocationLat());
            stmt.setDouble(8, jobPostingEntity.getLocationLon());
            stmt.setInt(9, jobPostingEntity.getJpid());
            
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
                "company, location_text, location_lat, location_lon " +
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
                String locationText = results.getString("location_text");
                double locationLat = results.getDouble("location_lat");
                double locationLon = results.getDouble("location_lon");
                JobPostingEntity jobEntity = new JobPostingEntity();
                jobEntity.setJpid(jpid);
                jobEntity.setDatePosted(datePosted);
                jobEntity.setHeadline(headline);
                jobEntity.setDescription(description);
                jobEntity.setCompany(company);
                jobEntity.setLocationText(locationText);
                jobEntity.setLocationLat(locationLat);
                jobEntity.setLocationLon(locationLon);
                jobList.add(jobEntity);
            }
        }
        
        return jobList;
    }

}
