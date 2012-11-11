package stackpointer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
    public boolean add(AnswerEntity answerEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String insertText =
                    "INSERT INTO answers "
                    + "(postedTimestamp, answer_text, qid, postedby_uid) "
                    + "VALUES(?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(
                    insertText, Statement.RETURN_GENERATED_KEYS);
            java.sql.Timestamp postedTimestamp = DBUtils.utilDateToSqlTimestamp(
                    answerEntity.getPostedTimestamp());
            stmt.setTimestamp(1, postedTimestamp);
            stmt.setString(2, answerEntity.getText());
            stmt.setInt(3, answerEntity.getAssociatedQid());
            stmt.setInt(4, answerEntity.getPostedByUserId());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);

            // Grab the id and store it on the answer
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int qid = rs.getInt(1);
                answerEntity.setAid(qid);
            } else {
                answerEntity.setAid(0);
            }
        }
        
        return success;
    }

    @Override
    public boolean update(AnswerEntity answerEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "UPDATE answers " +
                    "SET " +
                    "postedTimestamp = ?, " +
                    "answer_text = ?, " +
                    "qid = ?, " +
                    "postedby_uid = ? " +
                    "WHERE aid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            java.sql.Timestamp postedTimestamp = DBUtils.utilDateToSqlTimestamp(
                    answerEntity.getPostedTimestamp());
            stmt.setTimestamp(1, postedTimestamp);
            stmt.setString(2, answerEntity.getText());
            stmt.setInt(3, answerEntity.getAssociatedQid());
            stmt.setInt(4, answerEntity.getPostedByUserId());
            stmt.setInt(5, answerEntity.getAid());
            
            int rowsModified = stmt.executeUpdate();
            success = (rowsModified == 1);
        }
        
        return success;
    }

    @Override
    public boolean delete(AnswerEntity answerEntity) throws SQLException {
        boolean success = false;

        if (connection != null) {
            String updateText =
                    "DELETE FROM answers " +
                    "WHERE aid = ?";

            PreparedStatement stmt = connection.prepareStatement(updateText);
            stmt.setInt(1, answerEntity.getAid());
            int rowsDeleted = stmt.executeUpdate();
            success = (rowsDeleted == 1);
        }
        
        // reset the database id
        answerEntity.setAid(0);

        return success;
    }
    
    public List<AnswerEntity> retrieve(int questionId) throws SQLException {
        
        if (questionId <= 0) {
            return null;
        }
        
        List<AnswerEntity> answerList = select("qid = " + questionId);
        
        return answerList;
    }
    
    public List<AnswerEntity> retrieve(Set<Integer> questionIds) throws SQLException {
        if (questionIds == null || questionIds.isEmpty()) {
            return null;
        }
        
        StringBuilder builder = new StringBuilder();
        String csvList = DBUtils.collectionToCSVString(questionIds);
        builder.append("qid IN (");
        builder.append(csvList);
        builder.append(")");
        String whereClause = builder.toString();
        
        List<AnswerEntity> answerList = select(whereClause);
        
        return answerList;
    }
    
    private List<AnswerEntity> select(String whereClause) throws SQLException {
        List<AnswerEntity> answerList = new ArrayList<AnswerEntity>();
        
        // If no where clause is specified, we no query will be made.
        if (whereClause == null || whereClause.isEmpty()) {
            return null;
        }
        
        String queryText =
                "SELECT aid, postedTimestamp, answer_text, qid, postedby_uid " +
                "FROM answers " +
                "WHERE " + whereClause;
        
        if (super.connection != null && !super.connection.isClosed()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(queryText);

            while (results.next()) {
                int aid = results.getInt("aid");
                Date ts = results.getTimestamp("postedTimestamp");
                String text = results.getString("answer_text");
                int qid = results.getInt("qid");
                int uid = results.getInt("postedby_uid");
                AnswerEntity answerEntity = new AnswerEntity();
                answerEntity.setAid(aid);
                answerEntity.setAssociatedQid(qid);
                answerEntity.setPostedByUserId(uid);
                answerEntity.setPostedTimestamp(ts);
                answerEntity.setText(text);
                answerList.add(answerEntity);
            }
        }
        
        return answerList;
    }
    
}
