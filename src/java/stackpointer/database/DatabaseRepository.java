package stackpointer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Andrew
 */
public abstract class DatabaseRepository<T> {

    protected Connection connection;

    /**
     * Primary constructor
     * 
     * @param connectionInfo Database login information
     */
    public DatabaseRepository(DatabaseConnectionInfo connectionInfo) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("failed to register mysql driver");
            connection = null;
            return;
        }
        
        this.connection = DriverManager.getConnection(
                    connectionInfo.getUrl(),
                    connectionInfo.getUsername(),
                    connectionInfo.getPassword());
    }
    
    /**
     * Alternate constructor - for use when one connection is created and passed
     * in to multiple repositories
     * 
     * @param connection Database connection
     */
    public DatabaseRepository(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Closes the database connection, if open.
     */
    protected void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public abstract boolean add(T entity) throws SQLException;
    public abstract boolean update(T entity)throws SQLException;
    public abstract boolean delete(T entity) throws SQLException;
}
