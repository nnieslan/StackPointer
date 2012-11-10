package stackpointer.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(QuestionEntity questionEntity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(QuestionEntity questionEntity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
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
                "postedByUserId " +
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
            Date postedTimestamp = results.getDate("postedTimestamp");
            q.setPostedTimestamp(postedTimestamp);
            String title = results.getString("title");
            q.setTitle(title);
            String text = results.getString("question_text");
            q.setText(text);
            int postedByUid = results.getInt("postedByUserId");
            q.setPostedByUserId(postedByUid);
            questionList.add(q);
        }
        
        return questionList.size();
    }
}
