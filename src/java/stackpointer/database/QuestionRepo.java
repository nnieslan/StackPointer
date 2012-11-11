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
    
    @Override
    public boolean add(QuestionEntity questionEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String insertText =
                    "INSERT INTO questions "
                    + "(postedTimestamp, title, question_text, postedby_uid) "
                    + "VALUES(?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(
                    insertText, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date postedTimestamp = DBUtils.utilDateToSqlDate(
                    questionEntity.getPostedTimestamp());
            stmt.setDate(1, postedTimestamp);
            stmt.setString(2, questionEntity.getTitle());
            stmt.setString(3, questionEntity.getText());
            stmt.setInt(4, questionEntity.getPostedByUserId());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);

            // Grab the id and store it on the question
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int qid = rs.getInt(1);
                questionEntity.setQid(qid);
            } else {
                questionEntity.setQid(0);
            }
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
            java.sql.Date postedTimestamp = DBUtils.utilDateToSqlDate(
                    questionEntity.getPostedTimestamp());
            stmt.setDate(1, postedTimestamp);
            stmt.setString(2, questionEntity.getTitle());
            stmt.setString(3, questionEntity.getText());
            stmt.setInt(4, questionEntity.getPostedByUserId());
            stmt.setInt(5, questionEntity.getQid());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
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
