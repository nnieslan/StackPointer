package stackpointer.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import stackpointer.common.Location;
import stackpointer.common.SXUser;
import stackpointer.stackexchange.Question;

/**
 * @author Andrew
 */
public class SXDatabaseFacade {

    public SXDatabaseFacade() {
        
    }
    
    /**
     * Synchronizes the passed list of questions (and associated answers, users,
     * etc) with the database. New objects are added and existing or duplicates
     * are ignored.
     * 
     * @param questionList List of questions, most likely from the API
     * @return number of questions added to the database
     */
    public int syncQuestions(List<Question> questionList) {
        int numAdded = 0;
        
        if (questionList == null) {
            return 0;
        }
        
        Connection connection = DBUtils.openConnection(
                DatabaseConnectionInfo.createDefault());
        if (connection == null) {
            return -1;
        }
        
        QuestionRepo questionRepo = new QuestionRepo(connection);
        
        try {
            for (Question question : questionList) {
                
                if (question == null || question.getQid() <= 0) {
                    // skip invalid questions
                    continue;
                } else if (questionRepo.exists(question.getQid())) {
                    // skip existing questions
                    continue;
                }
                
                QuestionEntity questionEntity = translateToEntity(question);
                boolean success = questionRepo.add(questionEntity);
                if (success) {
                    numAdded++;
                }
            }
        } catch (SQLException ex) {
            String message = String.format("%d questions added to the database before failing", numAdded);
            DBUtils.logMessageToDatabase(message);
            System.err.println(ex);
            return -1;
        }
        
        String message = String.format("%d questions added to the database", numAdded);
        DBUtils.logMessageToDatabase(message);
        
        return numAdded;
    }
    
    /**
     * Retrieves the top 100 questions (and associated answers, users, etc)
     * from the database.
     * 
     * @return List of questions, limited to 100
     */
    public List<Question> retrieveTop100Questions() {
        QuestionRepo questionRepo = null;
        List<Question> questionList = new ArrayList<Question>();
        
        try {
            questionRepo = new QuestionRepo(DatabaseConnectionInfo.createDefault());
            
            List<QuestionEntity> questionEntities = questionRepo.retrieveLast100();
            
            for (QuestionEntity qe : questionEntities) {
                Question q = translateToQuestion(qe);
                questionList.add(q);
            }
            
        } catch (Exception ex) {
            System.err.println("Failed to retreive top 100 questions.");
            System.err.println(ex);
            questionList = null;
        }
        
        return questionList;
    }
    
    /**
     * Translate from a domain model object to a database object.
     * 
     * @param question
     * @return 
     */
    private QuestionEntity translateToEntity(Question question) {
        QuestionEntity entity = new QuestionEntity();
        
        entity.setQid(question.getQid());
        entity.setPostedTimestamp(question.getPostedTimestamp());
        entity.setTitle(question.getqTitle());
        entity.setText(question.getqText());
        
        SXUser poster = question.getAskedBy();
        if (poster != null) {
            entity.setPostedByUserId(poster.getSXid());
        } else {
            entity.setPostedByUserId(0);
        }
        
        return entity;
    }
    
    /**
     * Translate from a database object to a domain model object.
     * 
     * @param questionEntity
     * @return 
     */
    private Question translateToQuestion(QuestionEntity questionEntity) {
        Question question = new Question();
        
        question.setQid(questionEntity.getQid());
        question.setPostedTimestamp(questionEntity.getPostedTimestamp());
        question.setqText(questionEntity.getText());
        question.setqTitle(questionEntity.getTitle());
        
        if (questionEntity.getPostedByUserId() > 0) {
            SXUser user = new SXUser();
            user.setSXid(questionEntity.getPostedByUserId());
            question.setAskedBy(user);
        } else {
            question.setAskedBy(null);
        }
        
        question.setAnswers(null);
        
        return question;
    }
    
    /**
     * Translate from a domain model object to a database object.
     * 
     * @param user
     * @return 
     */
    private SXUserEntity translateToEntity(SXUser user) {
        SXUserEntity entity = new SXUserEntity();
        
        entity.setUid(user.getSXid());
        entity.setUsername(user.getUserName());
        
        Location location = user.getLoc();
        if (location != null) {
            entity.setLocationText(location.getLocStr());
            entity.setLocationLat(location.getLat());
            entity.setLocationLon(location.getLon());
        } else {
            entity.setLocationText(null);
            entity.setLocationLat(0.0);
            entity.setLocationLon(0.0);
        }
        
        return entity;
    }

}
