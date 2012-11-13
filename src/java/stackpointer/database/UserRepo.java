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
public class UserRepo extends DatabaseRepository<UserEntity> {

    public UserRepo(DatabaseConnectionInfo connectionInfo) throws SQLException {
        super(connectionInfo);
    }

    public UserRepo(Connection connection) {
        super(connection);
    }

    @Override
    public boolean add(UserEntity userEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String insertText =
                    "INSERT INTO sxusers "
                    + "(sxid, display_name, location_text, "
                    + "location_lat, location_lon) "
                    + "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(
                    insertText, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, userEntity.getSxid());
            stmt.setString(2, userEntity.getUsername());
            stmt.setString(3, userEntity.getLocationText());
            stmt.setDouble(4, userEntity.getLocationLat());
            stmt.setDouble(5, userEntity.getLocationLon());
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);

            // Grab the id and store it on the user
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int uid = rs.getInt(1);
                userEntity.setUid(uid);
            } else {
                userEntity.setUid(0);
            }
        }
        
        return success;
    }

    @Override
    public boolean update(UserEntity userEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "UPDATE sxusers " +
                    "SET " +
                    "sxid = ?, " +
                    "display_name = ?, " +
                    "location_text = ?, " +
                    "location_lat = ?, " +
                    "location_lon = ? " +
                    "WHERE uid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            stmt.setString(1, userEntity.getSxid());
            stmt.setString(2, userEntity.getUsername());
            stmt.setString(3, userEntity.getLocationText());
            stmt.setDouble(4, userEntity.getLocationLat());
            stmt.setDouble(5, userEntity.getLocationLon());
            stmt.setInt(6, userEntity.getUid());
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
    }

    @Override
    public boolean delete(UserEntity userEntity) throws SQLException {
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

    public List<UserEntity> retrieve(Set<Integer> userIds) throws SQLException {
        
        if (userIds == null || userIds.isEmpty()) {
            return null;
        }
        
        String listString = DBUtils.collectionToCSVString(userIds);
        
        StringBuilder builder = new StringBuilder();
        builder.append("uid IN (");
        builder.append(listString);
        builder.append(")");
        String whereClause = builder.toString();
        
        List<UserEntity> userList = select(whereClause);
        
        return userList;
    }
    
    private List<UserEntity> select(String whereClause) throws SQLException {
        List<UserEntity> userList = new ArrayList<UserEntity>();
        
        // If no where clause is specified, we no query will be made.
        if (whereClause == null || whereClause.isEmpty()) {
            return null;
        }
        
        String queryText =
                "SELECT uid, sxid, display_name, location_text, " +
                "location_lat, location_lon " +
                "FROM sxusers " +
                "WHERE " + whereClause;
        
        if (super.connection != null && !super.connection.isClosed()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(queryText);

            while (results.next()) {
                int uid = results.getInt("uid");
                String sxid = results.getString("sxid");
                String displayName = results.getString("display_name");
                String locationText = results.getString("location_text");
                double locationLat = results.getDouble("location_lat");
                double locationLon = results.getDouble("location_lon");
                UserEntity userEntity = new UserEntity();
                userEntity.setUid(uid);
                userEntity.setSxid(sxid);
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
