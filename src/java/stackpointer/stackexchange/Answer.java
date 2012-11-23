/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.stackexchange;

import stackpointer.common.SXUser;

/**
 *
 * @author Phil
 */
public class Answer {
    int aid; // our internal unique id
    SXUser answeredBy;
    String ansText;
    Question answering;
    boolean accepted;
    int score;

    public Answer() {
    }

    public int getAid() {
        return aid;
    }
    
    public void setAid(int aid) {
        this.aid = aid;
    }
    
    public SXUser getAnsweredBy() {
        return answeredBy;
    }

    public void setAnsweredBy(SXUser answeredBy) {
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
