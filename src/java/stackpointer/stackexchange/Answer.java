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
    int aid; // our internal unique id
    User answeredBy;
    String ansText;
    Question answering;

    public Answer() {
    }

    public int getAid() {
        return aid;
    }
    
    public void setAid(int aid) {
        this.aid = aid;
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
