package stackpointer.database;

import java.util.List;
import stackpointer.common.User;
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
    public List<User> retrieveUsers();
    
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
    public boolean addUser(User user);
    
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
     * @param jobPosting 
     */
    public boolean addJobPosting(JobPosting jobPosting);
}
