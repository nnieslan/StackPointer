package stackpointer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import stackpointer.common.User;
import stackpointer.jobs.JobPosting;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Andrew
 */
public class MySQLDatabaseFacade implements DatabaseFacade {
    protected DatabaseConnectionInfo connectionInfo;
    protected Connection connection;

    /**
     * Primary constructor
     * 
     * @param connectionInfo Database login information
     */
    public MySQLDatabaseFacade(DatabaseConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
        this.connection = null;
    }

    /**
     * Open a new connection to the database; meant to be used within the class.
     */
    protected void openConnection() {
        try {
            // Conncetion is already open; no need to open a new one
            if (connection != null && !connection.isClosed()) {
                return;
            }
            
            connection = DriverManager.getConnection(
                    connectionInfo.getUrl(),
                    connectionInfo.getUsername(),
                    connectionInfo.getPassword());
        } catch (SQLException ex) {
            System.err.println("database connection failed to open");
            System.err.println(ex);
            connection = null;
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyConnection() {
        boolean success = false;
        openConnection();

        if (connection == null) {
            return false;
        }

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select version()");

            if (rs.next()) {
                String version = rs.getString(1);
                success = !version.isEmpty();
            }
        } catch (Exception ex) {
            return false;
        } finally {
            closeConnection();
        }

        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> retrieveUsers() {
        List<User> userList = new LinkedList<User>();
        openConnection();
        
        String queryText =
                "SELECT uid, sxid, display_name, location_lid " +
                "FROM sxusers";
        
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(queryText);
                
                while (results.next()) {
                    int uid = results.getInt(1);
                    String sxid = results.getString(2);
                    String displayName = results.getString(3);
                    int lid = results.getInt(4);
                    User user = new User();
                    user.setUserName(displayName);
                    user.setSXid(sxid);
                    userList.add(user);
                }
            } catch (SQLException ex) {
                System.err.println("failed to retrieve users from database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return userList;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Question> retrieveQuestions() {
        List<Question> questionList = new LinkedList<Question>();
        openConnection();
        
        String queryText =
                "SELECT qid, question_text " +
                "FROM questions";
        
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(queryText);
                
                while (results.next()) {
                    int qid = results.getInt(1);
                    String questionText = results.getString(2);
                    Question question = new Question();
                    question.setQid(qid);
                    question.setqText(questionText);
                    questionList.add(question);
                }
            } catch (SQLException ex) {
                System.err.println("failed to retrieve questions from database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return questionList;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Answer> retrieveAnswers(int questionId) {
        List<Answer> answerList = new LinkedList<Answer>();
        
        if (questionId <= 0) {
            throw new IllegalArgumentException("questionId must be a positive integer");
        }
        
        openConnection();
        
        String queryText =
                "SELECT aid, answer_text " +
                "FROM answers " +
                "WHERE qid = " + Integer.toString(questionId);
        
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(queryText);
                
                while (results.next()) {
                    int aid = results.getInt(1);
                    String answerText = results.getString(2);
                    Answer answer = new Answer();
                    answer.setAid(aid);
                    answer.setAnsText(answerText);
                    answerList.add(answer);
                }
            } catch (SQLException ex) {
                System.err.println("failed to retrieve questions from database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return answerList;
    }
    
    /**
     *  {@inheritDoc}
     */
    @Override
    public List<JobPosting> retrieveJobPostings() {
        List<JobPosting> jobList = new LinkedList<JobPosting>();
        openConnection();
        
        String queryText =
                "SELECT jpid, date_posted, headline, description, " +
                "company, location_lid " +
                "FROM jobpostings";
        
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(queryText);
                
                while (results.next()) {
                    long jpid = results.getInt(1);
                    Date datePosted = results.getDate(2);
                    String headline = results.getString(3);
                    String description = results.getString(4);
                    String company = results.getString(5);
                    JobPosting jobPosting = new JobPosting(null, datePosted,
                            headline, description, company);
                    jobList.add(jobPosting);
                }
            } catch (SQLException ex) {
                System.err.println("failed to retrieve job postings from database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return jobList;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public boolean addUser(User user) {
        boolean success = false;
        openConnection();

        if (connection != null) {
            try {
                // TODO: add location as well
                String insertText =
                        "INSERT INTO sxusers " +
                        "(sxid, display_name) " +
                        "VALUES(?, ?)";
                
                PreparedStatement stmt = connection.prepareStatement(
                        insertText, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getSXid());
                stmt.setString(2, user.getRealName());
                int rowsModified = stmt.executeUpdate();
                success = (rowsModified == 1);
                
                // Grab the id and store it on the question
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int uid = rs.getInt(1);
                    user.setUid(uid);
                }
                
            } catch (SQLException ex) {
                System.err.println("failed insert a user into the database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return success;
    }
    
    /**
     *  {@inheritDoc}
     */
    @Override
    public boolean addQuestion(Question question) {
        boolean success = false;
        openConnection();

        if (connection != null) {
            try {
                String insertText =
                        "INSERT INTO questions " +
                        "(question_text, postedby_uid) " +
                        "VALUES(?, ?)";
                
                PreparedStatement stmt = connection.prepareStatement(
                        insertText, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, question.getqText());
                stmt.setInt(2, question.getAskedBy().getUid());
                int rowsModified = stmt.executeUpdate();
                success = (rowsModified == 1);
                
                // Grab the id and store it on the question
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int qid = rs.getInt(1);
                    question.setQid(qid);
                }
                
            } catch (SQLException ex) {
                System.err.println("failed insert a question into the database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return success;
    }
    
    /**
     *  {@inheritDoc}
     */
    @Override
    public boolean addAnswer(Answer answer) {
        boolean success = false;
        openConnection();

        if (connection != null) {
            try {
                String insertText =
                        "INSERT INTO answers " +
                        "(answer_text, qid, postedby_uid) " +
                        "VALUES(?, ?, ?)";
                
                PreparedStatement stmt = connection.prepareStatement(
                        insertText, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, answer.getAnsText());
                stmt.setInt(2, answer.getAnswering().getQid());
                stmt.setInt(3, answer.getAnsweredBy().getUid());
                int rowsModified = stmt.executeUpdate();
                success = (rowsModified == 1);
                
                // Grab the id and store it on the answer
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int aid = rs.getInt(1);
                    answer.setAid(aid);
                }
            } catch (SQLException ex) {
                System.err.println("failed insert an answer into the database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return success;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public boolean addJobPosting(JobPosting jobPosting) {
        boolean success = false;
        openConnection();

        if (connection != null) {
            try {
                // TODO: add location to insert
                String insertText =
                        "INSERT INTO jobpostings " +
                        "(date_posted, headline, description, company) " +
                        "VALUES(?, ?, ?, ?)";
                
                PreparedStatement stmt = connection.prepareStatement(
                        insertText, Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1, new java.sql.Date(jobPosting.getDatePosted().getTime()));
                stmt.setString(2, jobPosting.getHeadline());
                stmt.setString(3, jobPosting.getDescription());
                stmt.setString(4, jobPosting.getCompany());
                int rowsModified = stmt.executeUpdate();
                success = (rowsModified == 1);
                
                // Grab the id and store it on the job posting
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int jpid = rs.getInt(1);
                    jobPosting.setJpid(jpid);
                }
            } catch (SQLException ex) {
                System.err.println("failed insert a job posting into the database");
                System.err.println(ex);
            } finally {
                closeConnection();
            }
        }
        
        return success;
    }
    
    public static void main(String[] args) {
        DatabaseFacade db = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        if (db.verifyConnection()) {
            System.out.println("database connection good");
        } else {
            System.out.println("database connection BAD");
        }
    }
}
