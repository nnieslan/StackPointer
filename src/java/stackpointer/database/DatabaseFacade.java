/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stackpointer.database;

import java.util.List;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Andrew
 */
public interface DatabaseFacade {
    
    public boolean verifyConnection();
    public List<Question> retrieveQuestions();
    public List<Answer> retrieveAnswers(Long questionId);
}
