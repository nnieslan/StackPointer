package stackpointer.database;

import java.util.Collection;

/**
 *
 * @author Andrew
 */
public class DBUtils {
    
    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }
    
    public static String collectionToCSVString(Collection collection) {
        StringBuilder builder = new StringBuilder();
        builder.append(collection.toString());
        builder.deleteCharAt(0);
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
    
}
