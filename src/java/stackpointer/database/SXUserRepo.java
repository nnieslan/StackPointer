package stackpointer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Andrew
 */
public class SXUserRepo extends DatabaseRepository<SXUserEntity> {

    public SXUserRepo(DatabaseConnectionInfo connectionInfo) throws SQLException {
        super(connectionInfo);
    }

    public SXUserRepo(Connection connection) {
        super(connection);
    }
    
    public boolean exists(int uid) {
        boolean exists = false;
        
        if (uid <= 0) {
            return false;
        }
        
        if (connection != null) {
            String queryText =
                    "SELECT COUNT(*) " +
                    "FROM sxusers " +
                    "WHERE uid = ? " +
                    "LIMIT 1";
            
            try {
                PreparedStatement stmt = connection.prepareStatement(queryText);
                stmt.setInt(1, uid);
                
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
    public boolean add(SXUserEntity userEntity) throws SQLException {
        boolean success = false;

        if (userEntity == null || userEntity.getUid() <= 0) {
            return false;
        }
        
        if (connection != null) {
            String insertText =
                    "INSERT INTO sxusers "
                    + "(uid, display_name, location_text, "
                    + "location_lat, location_lon) "
                    + "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertText);
            
            stmt.setInt(1, userEntity.getUid());
            stmt.setString(2, userEntity.getUsername());
            
            if (userEntity.getLocationText() == null || userEntity.getLocationText().isEmpty()) {
              stmt.setNull(3, java.sql.Types.INTEGER);
            } else {
                stmt.setString(3, userEntity.getLocationText());
            }
            
            stmt.setDouble(4, userEntity.getLocationLat());
            stmt.setDouble(5, userEntity.getLocationLon());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
    }

    @Override
    public boolean update(SXUserEntity userEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "UPDATE sxusers " +
                    "SET " +
                    "display_name = ?, " +
                    "location_text = ?, " +
                    "location_lat = ?, " +
                    "location_lon = ? " +
                    "WHERE uid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            stmt.setString(1, userEntity.getUsername());
            stmt.setString(2, userEntity.getLocationText());
            stmt.setDouble(3, userEntity.getLocationLat());
            stmt.setDouble(4, userEntity.getLocationLon());
            stmt.setInt(5, userEntity.getUid());
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
    }

    @Override
    public boolean delete(SXUserEntity userEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "DELETE FROM sxusers " +
                    "WHERE uid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            stmt.setInt(1, userEntity.getUid());
            int rowsDeleted = stmt.executeUpdate();
            success = (rowsDeleted == 1);
        }
        
        // reset the database id
        userEntity.setUid(0);

        return success;
    }

    public List<SXUserEntity> retrieve(Set<Integer> userIds) throws SQLException {
        
        if (userIds == null || userIds.isEmpty()) {
            return null;
        }
        
        String listString = DBUtils.collectionToCSVString(userIds);
        
        StringBuilder builder = new StringBuilder();
        builder.append("uid IN (");
        builder.append(listString);
        builder.append(")");
        String whereClause = builder.toString();
        
        List<SXUserEntity> userList = select(whereClause);
        
        return userList;
    }
    
    private List<SXUserEntity> select(String whereClause) throws SQLException {
        List<SXUserEntity> userList = new ArrayList<SXUserEntity>();
        
        // If no where clause is specified, we no query will be made.
        if (whereClause == null || whereClause.isEmpty()) {
            return null;
        }
        
        String queryText =
                "SELECT uid, display_name, location_text, " +
                "location_lat, location_lon " +
                "FROM sxusers " +
                "WHERE " + whereClause;
        
        if (super.connection != null && !super.connection.isClosed()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(queryText);

            while (results.next()) {
                int uid = results.getInt("uid");
                String displayName = results.getString("display_name");
                String locationText = results.getString("location_text");
                double locationLat = results.getDouble("location_lat");
                double locationLon = results.getDouble("location_lon");
                SXUserEntity userEntity = new SXUserEntity();
                userEntity.setUid(uid);
                userEntity.setUsername(displayName);
                userEntity.setLocationText(locationText);
                userEntity.setLocationLat(locationLat);
                userEntity.setLocationLon(locationLon);
                userList.add(userEntity);
            }
        }
        
        return userList;
    }
}
