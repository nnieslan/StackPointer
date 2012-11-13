package stackpointer.database;

import java.util.List;
import stackpointer.stackexchange.Question;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Andrew
 */
public class SXDatabaseFacade {

    public SXDatabaseFacade() {
        
    }
    
    /**
     * Synchronizes the passed list of questions (and associated answers, users,
     * etc) with the database. New objects are added and existing or duplicates
     * are ignored.
     * 
     * @param questionList List of questions, most likely from the API
     * @return true of the sync was fully successful, otherwise false
     */
    public boolean syncQuestions(List<Question> questionList) {
        throw new NotImplementedException();
    }
    
    /**
     * Retrieves the top 100 questions (and associated answers, users, etc)
     * from the database.
     * 
     * @return List of questions, limited to 100
     */
    public List<Question> retrieveTop100Questions() {
        throw new NotImplementedException();
    }

}
