/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.common;

import java.util.List;
import java.util.Set;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Phil
 */
public class User {
    String realName;
    String userName;
    String SXid; //stack exchange id
    Location loc;
    Set<User> following;
    List<Question> asked;
    List<Answer> answered;

    public User(String realName, String userName) {
        this.realName = realName;
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSXid() {
        return SXid;
    }

    public void setSXid(String SXid) {
        this.SXid = SXid;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public List<Question> getAsked() {
        return asked;
    }

    public void setAsked(List<Question> asked) {
        this.asked = asked;
    }

    public List<Answer> getAnswered() {
        return answered;
    }

    public void setAnswered(List<Answer> answered) {
        this.answered = answered;
    }
    
    public void addQuestion(Question q) {
        asked.add(q);
    }
    
    public void addAnswer(Answer a) {
        answered.add(a);
    }
}
