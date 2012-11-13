package stackpointer.database;

import java.util.List;
import stackpointer.common.SXUser;
import stackpointer.jobs.JobPosting;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Andrew
 */
public interface DatabaseFacade {
    
    /**
     * Not a very purposeful method, but more to actually query
     * the database to make sure everything is connected properly.
     * 
     * @return true if the database connection was fine and a basic
     * query succeeded, otherwise false
     */
    public boolean verifyConnection();
    
    /**
     * Retrieves a list of users.
     * 
     * @return list of users
     */
    public List<SXUser> retrieveUsers();
    
    /**
     * Retrieves a list of questions that have been cached in the database.
     * 
     * @return list of questions
     */
    public List<Question> retrieveQuestions();
    
    /**
     * Retrieves a list of answers for a given question.
     * 
     * @param questionId id of the question for which you'd like answers to
     * @return list of answers to the requested question
     */
    public List<Answer> retrieveAnswers(int questionId);
    
    /**
     * Retrieves a list of all job postings.
     * 
     * @return list of job postings
     */
    public List<JobPosting> retrieveJobPostings();
    
    /**
     * Inserts a new user record into the database.
     * 
     * @param user object containing all of the data for the user
     * @return 
     */
    public boolean addUser(SXUser user);
    
    /**
     * Inserts a new question record into the database.
     * 
     * @param question object containing all of the data for the question
     */
    public boolean addQuestion(Question question);
    
    /**
     * Inserts a new answer record into the database.
     * 
     * @param answer object containing all of the data for the answer
     */
    public boolean addAnswer(Answer answer);
    
    /**
     * Inserts a new job posting record into the database
     * 
     * @param jobPosting object containing all of the data for the job posting
     */
    public boolean addJobPosting(JobPosting jobPosting);
    
    /**
     * Updates an existing user in the database
     * 
     * @param user the user data to be reflected in the database
     * 
     * @return true if the user existed and was successfully updated,
     * otherwise false
     */
    public boolean updateUser(SXUser user);
    
    /**
     * Updates an existing user in the database
     * 
     * @param question the question data to be reflected in the database
     * 
     * @return true if the question existed and was successfully updated,
     * otherwise false
     */
    public boolean updateQuestion(Question question);
    
    /**
     * Updates an existing answer in the database
     * 
     * @param answer the answer data to be reflected in the database
     * 
     * @return true if the answer existed and was successfully updated,
     * otherwise false
     */
    public boolean updateAnswer(Answer answer);
    
    /**
     * Updates an existing job posting in the database
     * 
     * @param jobPosting the job posting data to be reflected in the database
     * 
     * @return true if the job posting existed and was successfully updated,
     * otherwise false
     */
    public boolean updateJobPosting(JobPosting jobPosting);
    
    /**
     * Deletes an existing user from the database
     * 
     * @param user the user to be deleted
     * 
     * @return true if the user existed and was successfully deleted,
     * otherwise false
     */
    public boolean deleteUser(SXUser user);
    
    /**
     * Deletes an existing question and corresponding answers from the database
     * 
     * @param question the question to be deleted
     * 
     * @return true if the question existed and was successfully deleted,
     * otherwise false
     */
    public boolean deleteQuestion(Question question);
    
    /**
     * Deletes an existing answer from the database
     * 
     * @param answer the answer to be deleted
     * 
     * @return true if the answer existed and was successfully deleted,
     * otherwise false
     */
    public boolean deleteAnswer(Answer answer);
    
    /**
     * Deletes an existing job posting from the database
     * 
     * @param jobPosting the job posting to be deleted
     * 
     * @return true if the job posting existed and was successfully deleted,
     * otherwise false
     */
    public boolean deleteJobPosting(JobPosting jobPosting);
}
