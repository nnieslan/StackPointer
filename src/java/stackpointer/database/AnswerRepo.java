package stackpointer.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author Andrew
 */
public class AnswerRepo extends DatabaseRepository<AnswerEntity> {

    public AnswerRepo(DatabaseConnectionInfo connectionInfo) throws SQLException {
        super(connectionInfo);
    }

    public AnswerRepo(Connection connection) {
        super(connection);
    }

    @Override
    public boolean add(AnswerEntity entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(AnswerEntity entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(AnswerEntity entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<AnswerEntity> retrieve(int questionId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<AnswerEntity> retrieve(Set<Integer> questionIds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
