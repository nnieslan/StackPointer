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
    String qText; //the question text
    List <Answer> answers;

    public Question(User askedBy, String qText, List<Answer> answers) {
        this.askedBy = askedBy;
        this.qText = qText;
        this.answers = answers;
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
        return this.qText;
    }
}
