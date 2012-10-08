/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.stackexchange;

import java.util.List;
import stackpointer.common.User;

/**
 *
 * @author Phil
 */
public class Question {
    int qid; // our internal unique id
    User askedBy;
    String qTitle; //the question title
    String qText; //the question text
    List <Answer> answers;

    public Question() {
    }
    
    public int getQid() {
        return qid;
    }
    
    public void setQid(int qid) {
        this.qid = qid;
    }

    public User getAskedBy() {
        return askedBy;
    }

    public void setAskedBy(User askedBy) {
        this.askedBy = askedBy;
    }

    public int getNumAnswers() {
        return answers.size();
    }
    
    public boolean isAnswered() {
        return !answers.isEmpty();
    }

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" + "askedBy=" + askedBy + ", qTitle=" + qTitle + ", qText=" + qText + '}';
    }
    
}
