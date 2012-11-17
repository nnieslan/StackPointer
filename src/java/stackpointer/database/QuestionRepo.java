package stackpointer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew
 */
public class QuestionRepo extends DatabaseRepository<QuestionEntity> {
    
    public QuestionRepo(DatabaseConnectionInfo connectionInfo) throws SQLException {
        super(connectionInfo);
    }

    public QuestionRepo(Connection connection) {
        super(connection);
    }
    
    public boolean exists(int qid) {
        boolean exists = false;
        
        if (qid <= 0) {
            return false;
        }
        
        if (connection != null) {
            String queryText =
                    "SELECT COUNT(*) " +
                    "FROM questions " +
                    "WHERE qid = ? " +
                    "LIMIT 1";
            
            try {
                PreparedStatement stmt = connection.prepareStatement(queryText);
                stmt.setInt(1, qid);
                
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
    public boolean add(QuestionEntity questionEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            questionEntity.prepare();
            
            String insertText =
                    "INSERT INTO questions "
                    + "(qid, postedTimestamp, title, question_text, postedby_uid) "
                    + "VALUES(?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(insertText);
            
            stmt.setInt(1, questionEntity.getQid());
            
            if (questionEntity.getPostedTimestamp() == null) {
                stmt.setNull(2, java.sql.Types.TIMESTAMP);
            } else {
                java.sql.Timestamp postedTimestamp = DBUtils.utilDateToSqlTimestamp(
                    questionEntity.getPostedTimestamp());
                stmt.setTimestamp(2, postedTimestamp);
            }
            
            if (questionEntity.getTitle() == null) {
                stmt.setNull(3, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(3, questionEntity.getTitle());
            }
            
            if (questionEntity.getText() == null) {
                stmt.setNull(4, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(4, questionEntity.getText());
            }
            
            stmt.setInt(5, questionEntity.getPostedByUserId());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
    }

    @Override
    public boolean update(QuestionEntity questionEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "UPDATE questions " +
                    "SET " +
                    "postedTimestamp = ?, " +
                    "title = ?, " +
                    "question_text = ?, " +
                    "postedby_uid = ? " +
                    "WHERE qid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            java.sql.Timestamp postedTimestamp = DBUtils.utilDateToSqlTimestamp(
                    questionEntity.getPostedTimestamp());
            stmt.setTimestamp(1, postedTimestamp);
            stmt.setString(2, questionEntity.getTitle());
            stmt.setString(3, questionEntity.getText());
            stmt.setInt(4, questionEntity.getPostedByUserId());
            stmt.setInt(5, questionEntity.getQid());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
    }

    public boolean delete(int qid) throws SQLException {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQid(qid);
        return delete(questionEntity);
    }
    
    @Override
    public boolean delete(QuestionEntity questionEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "DELETE FROM questions " +
                    "WHERE qid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            stmt.setInt(1, questionEntity.getQid());
            int rowsDeleted = stmt.executeUpdate();
            success = (rowsDeleted == 1);
        }
        
        // reset the database id
        questionEntity.setQid(0);

        return success;
    }
    
    /**
     * Retrieve the last 100 questions that have been posted
     * @return List of questions (limited to 100)
     * @throws SQLException 
     */
    public List<QuestionEntity> retrieveLast100() throws SQLException {
        List<QuestionEntity> questionList = new ArrayList<QuestionEntity>(100);
        
        if (connection == null || connection.isClosed()) {
            throw new SQLException("connection is null or closed");
        }
        
        String queryText =
                "SELECT qid, postedTimestamp, title, question_text, " +
                "postedby_uid " +
                "FROM questions " +
                "ORDER BY postedTimestamp DESC " +
                "LIMIT 100";
        
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(queryText);
        readResultSet(results, questionList);
        
        return questionList;
    }
    
    /**
     * Factor out the common functionality of reading in a question
     * given a query results set. QuestionEntity objects will be created
     * and added to the questionList that is passed in as a parameter.
     * @param results query result set
     * @param questionList list of question entities
     * @return number of objects retrieved
     * @throws SQLException 
     */
    protected int readResultSet(ResultSet results,
            List<QuestionEntity> questionList) throws SQLException {
        
        while (results.next()) {
            QuestionEntity q = new QuestionEntity();
            int qid = results.getInt("qid");
            q.setQid(qid);
            java.util.Date postedTimestamp = results.getDate("postedTimestamp");
            q.setPostedTimestamp(postedTimestamp);
            String title = results.getString("title");
            q.setTitle(title);
            String text = results.getString("question_text");
            q.setText(text);
            int postedByUid = results.getInt("postedby_uid");
            q.setPostedByUserId(postedByUid);
            questionList.add(q);
        }
        
        return questionList.size();
    }

}
