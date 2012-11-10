package stackpointer.database;

import java.sql.Connection;
import java.sql.SQLException;

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
    public boolean add(JobPostingEntity entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(JobPostingEntity entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(JobPostingEntity entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
