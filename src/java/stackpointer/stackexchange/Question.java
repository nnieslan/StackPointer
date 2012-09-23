/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.stackexchange;

import stackpointer.common.User;

/**
 *
 * @author Phil
 */
public class Question {
    User askedBy;
    int numAnswers=0;
    String qText; //the question text

    public Question(User askedBy, int numAnswers, String qText) {
        this.askedBy = askedBy;
        this.numAnswers = numAnswers;
        this.qText = qText;
    }

    public User getAskedBy() {
        return askedBy;
    }

    public void setAskedBy(User askedBy) {
        this.askedBy = askedBy;
    }

    public int getNumAnswers() {
        return numAnswers;
    }
    
    public boolean isAnswered() {
        return numAnswers!=0;
    }

    public void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }
    
}
