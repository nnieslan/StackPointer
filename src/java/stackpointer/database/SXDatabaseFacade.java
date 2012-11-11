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
    
    public boolean syncQuestions(List<Question> questionList) {
        throw new NotImplementedException();
    }
    
    public List<Question> retrieveTop100Questions() {
        throw new NotImplementedException();
    }

}
