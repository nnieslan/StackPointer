package stackpointer.database;

import java.util.Date;

/**
 * @author Andrew
 * 
 * This class models a row from the answers table in the database.
 */
public class AnswerEntity implements DBEntity {

    int aid;
    String text;
    int associatedQid;
    int postedByUserId;
    Date postedTimestamp;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAssociatedQid() {
        return associatedQid;
    }

    public void setAssociatedQid(int associatedQid) {
        this.associatedQid = associatedQid;
    }

    public int getPostedByUserId() {
        return postedByUserId;
    }

    public void setPostedByUserId(int postedByUserId) {
        this.postedByUserId = postedByUserId;
    }
    
    public Date getPostedTimestamp() {
        return postedTimestamp;
    }

    public void setPostedTimestamp(Date postedTimestamp) {
        this.postedTimestamp = postedTimestamp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() {
        
    }
}
