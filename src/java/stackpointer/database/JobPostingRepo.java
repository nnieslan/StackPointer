package stackpointer.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Andrew
 */
public class JobPostingRepo extends DatabaseRepository<JobPostingEntity> {

    public JobPostingRepo(DatabaseConnectionInfo connectionInfo) throws SQLException {
        super(connectionInfo);
    }

    public JobPostingRepo(Connection connection) {
        super(connection);
    }

    @Override
    public boolean add(JobPostingEntity jobPostingEntity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(JobPostingEntity jobPostingEntity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(JobPostingEntity jobPostingEntity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<JobPostingEntity> retrieve() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
