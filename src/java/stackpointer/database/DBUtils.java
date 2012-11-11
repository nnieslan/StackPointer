package stackpointer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Andrew
 */
public class DBUtils {
    
    /**
     * Converts a util date to sql timestamp
     * @param utilDate
     * @return 
     */
    public static java.sql.Timestamp utilDateToSqlTimestamp(java.util.Date utilDate) {
        return new java.sql.Timestamp(utilDate.getTime());
    }
    
    /**
     * * Converts a util date to sql date
     * @param utilDate
     * @return 
     */
    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
    
    /**
     * Converts a collection to a comma separated value list string.
     * For example, a  list of integers would translate to a string
     * looking like this: "33, 8, 13, 18, 978, 4"
     * @param collection any type of java collection
     * @return comma separated value string
     */
    public static String collectionToCSVString(Collection collection) {
        StringBuilder builder = new StringBuilder();
        builder.append(collection.toString());
        builder.deleteCharAt(0);
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
    
    /**
     * Logs a message to the database table splog
     * @param message any text you'd like, limited to 200 characters
     */
    public static void logMessageToDatabase(String message) {
        logMessageToDatabase(message, DatabaseConnectionInfo.createDefault());
    }
    
    /**
     * Logs a message to the database table splog
     * @param message any text you'd like, limited to 200 characters
     * @param connectionInfo alternate specified database connection info
     */
    public static void logMessageToDatabase(String message,
            DatabaseConnectionInfo connectionInfo) {
        
        final int MaxLen = 200;
        
        if (message == null || message.isEmpty()) {
            return;
        }
        else if (message.length() > MaxLen) {
            message = message.substring(0, MaxLen-1);
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    connectionInfo.getUrl(),
                    connectionInfo.getUsername(),
                    connectionInfo.getPassword());
            
            String insertText =
                    "INSERT INTO splog "
                    + "(message) "
                    + "VALUES(?)";
            
            PreparedStatement stmt = connection.prepareStatement(insertText);
            stmt.setString(1, message);
            stmt.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            System.err.println("failed to register mysql driver");
        } catch (SQLException ex) {
            System.err.println("sql exception");
            System.err.println(ex);
        } catch (Exception ex) {
            System.err.println("unknown exception");
            System.err.println(ex);
        }
    }
    
}
