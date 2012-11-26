package stackpointer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import stackpointer.common.Location;
import stackpointer.common.SXUser;
import stackpointer.stackexchange.Answer;
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
     * @return number of questions added to the database, or -1 means failure
     */  
    public int syncQuestions(List<Question> questionList) {
        int numQuestionsAdded = 0;
        int numUsersAdded = 0;
        Connection connection = null;
        
        if (questionList == null) {
            return 0;
        }
        
        try {
            connection = DBUtils.openConnection(
                    DatabaseConnectionInfo.createDefault());
            if (connection == null) {
                return -1;
            }
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println("Failed to open/initialize database connection");
            System.err.println(ex);
        }
        
        SXUserRepo userRepo = new SXUserRepo(connection);
        QuestionRepo questionRepo = new QuestionRepo(connection);
        
        try {
            for (Question question : questionList) {
                
                if (!isValidForSaving(question)) {
                    // skip invalid questions
                    continue;
                } else if (questionRepo.exists(question.getQid())) {
                    // skip existing questions
                    continue;
                }
                
                // First save off the user
                SXUser user = question.getAskedBy();
                if (!userRepo.exists(user.getSXid())) {
                    SXUserEntity userEntity = translateToEntity(user);
                    boolean userSuccess = userRepo.add(userEntity);
                    if (!userSuccess) {
                        continue;
                    } else {
                        numUsersAdded++;
                    }
                }
                
                // Now we can save off the question
                QuestionEntity questionEntity = translateToEntity(question);
                boolean success = questionRepo.add(questionEntity);
                if (success) {
                    numQuestionsAdded++;
                    // Only if the question succeeds can we save off the tags.
                    saveQuestionTags(connection, question.getQid(), question.getTags());
                    // Save off each answer too.
                    AnswerRepo answerRepo = new AnswerRepo(connection);
                    List<Answer> answers = question.getAnswers();
                    if (answers != null && !answers.isEmpty()) {
                        for (Answer a : answers) {
                            AnswerEntity entity = translateToEntity(a);
                            if (entity.getAssociatedQid() <= 0) {
                                entity.setAssociatedQid(question.getQid());
                            }
                            answerRepo.add(entity);
                        }
                    }
                }
            }
            
            // Now that we've made it through everything we can commit
            connection.commit();
            
        } catch (SQLException ex) {
            try {
                connection.rollback();
                System.err.println("rolling back syncQuestions transaction");
                System.err.println("SQL Exception occurred");
                System.err.println(ex);
            } catch (SQLException rollbackEx) {
                System.err.println("Failed to rollback syncQuestions transaction");
                System.err.println(rollbackEx);
            }
            DBUtils.logMessageToDatabase("SQLException occurred in syncQuestions");
            return -1;
        }
        
        String uMessage = String.format("%d sxusers added to the database", numUsersAdded);
        String qMessage = String.format("%d questions added to the database", numQuestionsAdded);
        System.out.println(uMessage);
        System.out.println(qMessage);
        DBUtils.logMessageToDatabase(qMessage);
        
        return numQuestionsAdded;
    }
    
    /**
     * Save question tags to the database.
     * 
     * @param connection
     * @param qid
     * @param tags
     * @throws SQLException 
     */
    private void saveQuestionTags(Connection connection, int qid, List<String> tags) throws SQLException {
        StringBuilder builder = new StringBuilder();
        
        if (connection == null) {
            return;
        }
        
        if (qid <= 0 || tags == null || tags.isEmpty()) {
            return;
        }
        
        builder.append("INSERT INTO tags VALUES ");
        
        for (String t : tags) {
            if (t == null) {
                continue;
            }           
            if (t.isEmpty()) {
                continue;
            }
            builder.append("(");
            builder.append(qid);
            builder.append(", '");
            builder.append(t);
            builder.append("'),");
        }
        
        // get rid of last comma
        builder.deleteCharAt(builder.length()-1);
        
        String insertText = builder.toString();
        PreparedStatement stmt = connection.prepareStatement(insertText);
        stmt.execute();
    }
    
    /**
     * Verifies that the question is valid for saving
     * @param q Question
     * @return true if the question is valid for saving, otherwise false
     */
    private boolean isValidForSaving(Question q) {
        // null question
        if (q == null) {
            return false;
        }
        // no question id
        if (q.getQid() <= 0) {
            return false;
        }
        
        // no user attached
        SXUser u = q.getAskedBy();
        if (u == null) {
            return false;
        }
        // invalid user id
        if (u.getSXid() <= 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Retrieves the top 100 questions (and associated answers, users, etc)
     * from the database.
     * 
     * @return List of questions, limited to 100
     */
    public List<Question> retrieveTop100Questions() {
        QuestionRepo questionRepo = null;
        Set<Integer> qidCache = new HashSet<Integer>();
        Set<SXUser> userCache = new HashSet<SXUser>();
        List<Question> questionList = new ArrayList<Question>();
        Connection connection = DBUtils.openConnection(
                DatabaseConnectionInfo.createDefault());
        
        try {
            questionRepo = new QuestionRepo(connection);
            List<QuestionEntity> questionEntities = questionRepo.retrieveLast100();
            
            for (QuestionEntity qe : questionEntities) {
                Question q = translateToQuestion(qe);
                qidCache.add(q.getQid());
                
                SXUser u = q.getAskedBy();
                if (u != null) {
                    userCache.add(u);
                }
                
                questionList.add(q);
            }
            
        } catch (Exception ex) {
            System.err.println("Failed to retreive top 100 questions.");
            System.err.println(ex);
            questionList = null;
        }
        
        // Now that we've gathered all of the questions let's go back
        // and populate the user details.
        try {
            populateSXUserDetails(connection, userCache);
        } catch (SQLException ex) {
            System.err.println("Failed to populate sxuser details");
            System.err.println(ex);
            questionList = null;
        }
        
        // Grab the question tags.
        try {
            Map<Integer, List<String>> tagMap = getQuestionTags(connection, qidCache);
            for (Question question : questionList) {
                List<String> tagList = tagMap.get(question.getQid());
                question.setTags(tagList);
            }
        } catch (SQLException ex) {
            System.err.println("Failed to populate question tags");
            System.err.println(ex);
            questionList = null;
        }
        
        // Grab the answers.
        try {
            Map<Integer, Question> qMap = new HashMap<Integer, Question>();
            AnswerRepo answerRepo = new AnswerRepo(connection);
            List<AnswerEntity> answerList = answerRepo.retrieve(qidCache);
            // woudld love to make this part cleaner, but no time now
            // build up the question map
            for (Question q : questionList) {
                q.setAnswers(new ArrayList<Answer>());
                qMap.put(q.getQid(), q);
            }
            // Translate each answer and add it to each 
            for (AnswerEntity answerEntity : answerList) {
                Answer answer = new Answer();
                translateToAnswer(answerEntity, answer);
                // todo - populate user details
                Question associatedQuestion = qMap.get(answerEntity.getAssociatedQid());
                answer.setAnswering(associatedQuestion);
                associatedQuestion.getAnswers().add(answer);
            }
        } catch (SQLException ex) {
            System.err.println("Failed to populate answes");
            System.err.println(ex);
            questionList = null;
        }
        
        return questionList;
    }
    
    /**
     * Populates the detailed data for each of the users in the given set.
     * Because the users are passed by reference, they will automatically have
     * the data populated on the question object as well.
     * 
     * @param connection database connection
     * @param userSet set of users to retrieve details for
     * @throws SQLException if something went wrong
     */
    private void populateSXUserDetails(Connection connection, Set<SXUser> userSet) throws SQLException {
        SXUserRepo userRepo = new SXUserRepo(connection);
        
        // Gather up the user ids
        Set<Integer> userIds = new HashSet<Integer>();
        for (SXUser u : userSet) {
            userIds.add(u.getSXid());
        }
        
        // Retrieve the user entities
        List<SXUserEntity> userEntityList = userRepo.retrieve(userIds);
        
        // Match up the entity with the user
        for (SXUserEntity ue : userEntityList) {
            for (SXUser u : userSet) {
                if (ue.getUid() == u.getSXid()) {
                    translateEntityToSXUser(ue, u);
                }
            }
        }
    }
    
    /**
     * Retrieve question tags from the database.
     * 
     * @param connection Database connection
     * @param questionIdSet Set of question Ids to find associated tags
     * @return Mapping of question Ids to list of tags
     * @throws SQLException Database exception
     */
    private Map<Integer,List<String>> getQuestionTags(Connection connection,
            Set<Integer> questionIdSet) throws SQLException {
        int lastQid = 0;
        List<String> currentTagList = null;
        Map<Integer, List<String>> tagMap = new HashMap<Integer, List<String>>();
        
        if (questionIdSet == null || questionIdSet.isEmpty()) {
            return tagMap;        
        }
        
        for (Integer qid : questionIdSet) {
            List<String> tagList = new ArrayList<String>();
            tagMap.put(qid, tagList);
        }
        
        String listString = DBUtils.collectionToCSVString(questionIdSet);
        
        String queryText =
                "SELECT qid, tag_text " +
                "FROM tags " +
                "WHERE qid IN (" + listString + ") " +
                "ORDER BY qid ASC";
        
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(queryText);

        while (results.next()) {
            int qid = results.getInt("qid");
            String tag = results.getString("tag_text");
            
            if (lastQid != qid) {
                currentTagList = tagMap.get(qid);
            }
            
            currentTagList.add(tag);
        }
        
        return tagMap;
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
        entity.setDisplayName(user.getSXname());
        
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
    
    /**
     * Translate from a database object to a domain model object.
     * 
     * @param entity
     * @param user 
     */
    private void translateEntityToSXUser(SXUserEntity entity, SXUser user) {
        user.setSXid(entity.getUid());
        user.setUserName(entity.getDisplayName());
        
        if ((entity.getLocationText() != null && !entity.getLocationText().isEmpty()) ||
                entity.getLocationLat() != 0.0 ||
                entity.getLocationLon() != 0.0) {
            Location loc = new Location(entity.getLocationText());
            loc.setLat(entity.getLocationLat());
            loc.setLon(entity.getLocationLon());
            user.setLoc(loc);
        } else {
            user.setLoc(null);
        }
    }
    
    private void translateToAnswer(AnswerEntity entity, Answer answer) {
        answer.setAid(entity.getAid());
        answer.setAnsText(entity.getText());
    }
    
    private AnswerEntity translateToEntity(Answer answer) {
        AnswerEntity entity = new AnswerEntity();
        entity.setAid(answer.getAid());
        entity.setText(answer.getAnsText());
        
        SXUser user = answer.getAnsweredBy();
        if (user != null) {
            entity.setPostedByUserId(user.getSXid());
        }
        
        Question associatedQuestion = answer.getAnswering();
        if (associatedQuestion != null) {
            entity.setAssociatedQid(associatedQuestion.getQid());
        }
        
        return entity;
    }

}
