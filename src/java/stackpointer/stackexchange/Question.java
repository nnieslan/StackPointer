/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.stackexchange;

import java.util.Date;
import java.util.List;
import stackpointer.common.SXUser;

/**
 *
 * @author Phil
 */
public class Question {
    int qid; // our internal unique id
    SXUser askedBy;
    Date postedTimestamp;
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

    public SXUser getAskedBy() {
        return askedBy;
    }
    
    public Date getPostedTimestamp() {
        return postedTimestamp;
    }
    
    public void setPostedTimestamp(Date postedTimestamp) {
        this.postedTimestamp = postedTimestamp;
    }

    public void setAskedBy(SXUser askedBy) {
        this.askedBy = askedBy;
    }

    public int getNumAnswers() {
        return answers.size();
    }
    
    public boolean isAnswered() {
        if(answers == null)
        {
            return false;
        }
        else
        {
            return !answers.isEmpty();
        }
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
    
    public boolean hasLocation() {
        boolean hasLoc = false;
        if(this.getAskedBy()!=null)
        {
            if(this.getAskedBy().getLoc()!=null)
            {
                hasLoc = true;
            }
        }
        return hasLoc;
    }

    @Override
    public String toString() {
        return "Question{" + "askedBy=" + askedBy + ", qTitle=" + qTitle + ", qText=" + qText + '}';
    }
}
