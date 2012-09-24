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
public class Answer {
    User answeredBy;
    String ansText;
    Question answering;

    public Answer(User answeredBy, String ansText, Question answering) {
        this.answeredBy = answeredBy;
        this.ansText = ansText;
        this.answering = answering;
    }

    public User getAnsweredBy() {
        return answeredBy;
    }

    public void setAnsweredBy(User answeredBy) {
        this.answeredBy = answeredBy;
    }

    public String getAnsText() {
        return ansText;
    }

    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }

    public Question getAnswering() {
        return answering;
    }

    public void setAnswering(Question answering) {
        this.answering = answering;
    }
    
    
}