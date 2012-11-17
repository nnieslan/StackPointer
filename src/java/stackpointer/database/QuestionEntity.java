package stackpointer.database;

import java.util.Date;

/**
 * @author Andrew
 * 
 * This class models a row from the questions table in the database.
 */
public class QuestionEntity implements DBEntity {

    private final int MaxTitleLen = 200;
    
    int qid;
    Date postedTimestamp;
    String title;
    String text;
    int postedByUserId;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }
    
    public Date getPostedTimestamp() {
        return postedTimestamp;
    }

    public void setPostedTimestamp(Date postedTimestamp) {
        this.postedTimestamp = postedTimestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostedByUserId() {
        return postedByUserId;
    }

    public void setPostedByUserId(int postedByUserId) {
        this.postedByUserId = postedByUserId;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() {
        if (title != null && title.length() > MaxTitleLen) {
            title = title.substring(0, MaxTitleLen-1);
        }
    }
   
}
