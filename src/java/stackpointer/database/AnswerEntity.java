package stackpointer.database;

/**
 * @author Andrew
 */
public class AnswerEntity {

    int aid;
    String text;
    int associatedQid;
    int postedByUserId;

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
    
}
