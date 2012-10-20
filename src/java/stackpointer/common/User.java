/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.common;

import java.util.ArrayList;
import java.util.HashSet;
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
    String SXname; //stack exchange display name
    int SXid; //stack exchange uid
    int uid; // our internal unique id
    Location loc;
    Set<User> following;
    List<Question> asked = new ArrayList<Question>();
    List<Answer> answered = new ArrayList<Answer>();

    public User() {
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

    public String getSXname() {
        return SXname;
    }

    public void setSXname(String SXname) {
        this.SXname = SXname;
    }
        
    public int getSXid() {
        return SXid;
    }

    public void setSXid(int SXid) {
        this.SXid = SXid;
    }
    
    public int getUid() {
        return uid;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", SXname=" + SXname + ", loc=" + loc + '}';
    }
    
}
