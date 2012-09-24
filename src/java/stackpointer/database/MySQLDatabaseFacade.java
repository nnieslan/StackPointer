package stackpointer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Andrew
 */
public class MySQLDatabaseFacade implements DatabaseFacade {
    DatabaseConnectionInfo connectionInfo;

    /**
     * Primary constructor
     * 
     * @param connectionInfo Database login information
     */
    public MySQLDatabaseFacade(DatabaseConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    /**
     * Open a new connection to the database; meant to be used within the class.
     * 
     * @return the database connection
     */
    protected Connection openConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(
                    connectionInfo.getUrl(),
                    connectionInfo.getUsername(),
                    connectionInfo.getPassword());
        } catch (SQLException ex) {
            System.err.println("database connection failed to open");
            System.err.println(ex);
            connection = null;
        }

        return connection;
    }

    /**
     * Not a very purposeful method, but more to actually query
     * the database to make sure everything is connected properly.
     * 
     * @return true if the database connection was fine and a basic
     * query succeeded, otherwise false
     */
    @Override
    public boolean verifyConnection() {
        Connection connection = openConnection();

        if (connection == null) {
            return false;
        }

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select version()");

            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }

        return false;
    }

    /**
     * Retrieves a list of questions that have been cached in the database.
     * 
     * @return list of questions
     */
    @Override
    public List<Question> retrieveQuestions() {
        List<Question> questionList = new LinkedList<Question>();
        Connection connection = openConnection();
        
        String queryText =
                "SELECT qid, question_text " +
                "FROM questions";
        
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(queryText);
                
                while (results.next()) {
                    long qid = results.getLong(1);
                    String questionText = results.getString(2);
                    Question question = new Question(null, questionText, null);
                    questionList.add(question);
                }
            } catch (SQLException ex) {
                System.err.println("failed to retrieve questions from database");
                System.err.println(ex);
            }
        }
        
        return questionList;
    }
    
    /**
     * Retrieves a list of answers for a given question.
     * 
     * @param questionId id of the question for which you'd like answers to
     * @return list of answers to the requested question
     */
    @Override
    public List<Answer> retrieveAnswers(Long questionId) {
        List<Answer> answerList = new LinkedList<Answer>();
        
        Connection connection = openConnection();
        
        String queryText =
                "SELECT aid, answer_text " +
                "FROM answers " +
                "WHERE qid = " + questionId.toString();
        
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(queryText);
                
                while (results.next()) {
                    long aid = results.getLong(1);
                    String answerText = results.getString(2);
                    Answer answer = new Answer(null, answerText, null);
                    answerList.add(answer);
                }
            } catch (SQLException ex) {
                System.err.println("failed to retrieve questions from database");
                System.err.println(ex);
            }
        }
        
        return answerList;
    }
}
