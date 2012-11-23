package stackpointer.stackexchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stackpointer.common.SXUser;
import stackpointer.database.SXDatabaseFacade;
import stackpointer.googlemaps.GoogleMapsInterface;

/**
 * This class is to interact with the stack exchange API, including retrieval
 * of data, and parting it.
 * @author Phil
 */
public class StackExchangeInterface {
    private static ArrayList<Question> top100Questions = new ArrayList<Question>();
    final static String baseUrl = "https://api.stackexchange.com/2.1/";
    final static String sxKey = "ubwVxucHGeVndxd5knjnMg((";
    final static String qFilter = "!WnfFymBLMEKO)0sTDgTEhkA051aV27yclvUi-EY";
    final static String uFilter = "!)RwcIFebx-BtQG9xWNeFFySy";

    public StackExchangeInterface()
    {
        //TODO - Initialize values
    }
    
    public static ArrayList<Question> getQuestionsFromServer()
    {
        ArrayList<Question> servQs = null;
        JSONObject json = null;        
        try {
            URL url = new URL(baseUrl+"questions?key="+sxKey+"&page=1&pagesize=100&order=desc&sort=creation&site=stackoverflow&filter="+qFilter);
            URLConnection conn = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            //input is gzipped
            BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            json = new JSONObject(builder.toString());
            servQs = parseQuestionsFromJson(json);
        }
        catch (Exception e)
        {
            System.out.println("Error retrieving top 100 questions from StackExchange:\n"+e);
        }
        
        return servQs;
    }
    
    public static ArrayList<Question> parseQuestionsFromJson(JSONObject json)
    {
        ArrayList<Question> parsed = new ArrayList<Question>();
        HashMap<Integer, SXUser> owners = new HashMap<Integer, SXUser>();
        try
        {
            JSONArray questions = json.getJSONArray("items");
            for(int q=0; q<questions.length(); q++)
            {
                JSONObject jQuestion = questions.getJSONObject(q);
                SXUser owner = parseUserFromJson(jQuestion.getJSONObject("owner"));
                Question toAdd = new Question();
                toAdd.setAskedBy(owner);
                toAdd.setqTitle(jQuestion.getString("title"));
                toAdd.setqText(jQuestion.getString("body"));
                toAdd.setQid(jQuestion.getInt("question_id"));
                toAdd.setPostedTimestamp(new Date((jQuestion.getLong("creation_date")*1000)));
                toAdd.setUrl(jQuestion.getString("link"));
                if(jQuestion.has("tags"))
                {
                    JSONArray jTags = jQuestion.getJSONArray("tags"); 
                    List<String> tags = new ArrayList<String>();
                    for(int i=0; i<jTags.length(); i++)
                    {
                        tags.add(jTags.getString(i));
                    }
                    toAdd.setTags(tags);
                }
                if(jQuestion.getBoolean("is_answered"))
                {
                    List<Answer> answers  = parseAnswersFromJson(jQuestion.getJSONArray("answers"));
                    toAdd.setAnswers(answers);
                    for(Answer a:toAdd.getAnswers())
                    {
                        a.setAnswering(toAdd);
                    }
                }
                owner.addQuestion(toAdd);
                owners.put(owner.getSXid(), owner);
                parsed.add(toAdd);
            }
            populateLocations(owners);
        }
        catch (JSONException e)
        {
            System.out.println("Error parsing JSON question string: "+e);
        }
        return parsed;
    }
    
    public static SXUser parseUserFromJson(JSONObject json)
    {
        SXUser user = new SXUser();
        try
        {
            user.setSXname(json.getString("display_name"));
            user.setSXid(json.getInt("user_id"));
        }
        catch(JSONException e)
        {
            System.out.println("Error parsing JSON question string: "+e);
        }
        return user;
    }
    
    public static ArrayList<Answer> parseAnswersFromJson(JSONArray ans)
    {
        ArrayList<Answer> toReturn = new ArrayList<Answer>();
        Answer answer;
        JSONObject jAns;
        try
        {
            for (int a=0; a<ans.length(); a++)
            {
                jAns = ans.getJSONObject(a);
                answer = new Answer();
                answer.setAid(jAns.getInt("answer_id"));
                answer.setAnsText(jAns.getString("body"));
                answer.setAnsweredBy(parseUserFromJson(jAns.getJSONObject("owner")));
                answer.setAccepted(jAns.getBoolean("is_accepted"));
                answer.setScore(jAns.getInt("score"));
                toReturn.add(answer);
            }
        }
        catch (JSONException e)
        {
            System.out.println("Error parsing JSON answer: "+e);
        }
        
        return toReturn;
    }
    
    public static void populateLocations(HashMap<Integer, SXUser> owners)
    {
        StringBuilder ownerIds = new StringBuilder();
        for (SXUser o : owners.values())
        {
            ownerIds.append(o.getSXid()).append(';'); //semicolon separated list of ids
        }
        ownerIds.deleteCharAt(ownerIds.length()-1); //remove trailing semicolon
        try {
            URL url = new URL(baseUrl+"users/"+ownerIds+"?key="+sxKey+"&site=stackoverflow&filter="+uFilter);
            URLConnection conn = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            //input is gzipped
            BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());
            
            JSONArray users = json.getJSONArray("items");
            for(int u=0; u<users.length(); u++)
            {
                JSONObject jUser = users.getJSONObject(u);
                if(jUser.has("location"))
                {
                    owners.get(jUser.getInt("user_id")).setLoc(GoogleMapsInterface.geocode(jUser.getString("location")));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error populating user locations from StackExchange:\n"+e);
        }
    }

    //Function to save current values from StackExchange in local database
    public static boolean persistTopQuestions()
    {
        boolean success = false;
        SXDatabaseFacade db = new SXDatabaseFacade();
        top100Questions  = getQuestionsFromServer();
        if(top100Questions!=null)
        {
            if(!top100Questions.isEmpty())
            {
                int numAdded = db.syncQuestions(top100Questions);
                success = numAdded != -1;
            }
        }
        return success;
    }

    //Get the local copies of the top 100 questions
    public void retrieveTopQuestions()
    {
        SXDatabaseFacade db = new SXDatabaseFacade();
        List<Question> q = db.retrieveTop100Questions();
        if(q != null && !q.isEmpty())
        {   
            top100Questions.clear();
            top100Questions.addAll(q);
        }
    }

    //Return the top 100 questions
    public ArrayList<Question> getTop100Questions()
    {
        return top100Questions;
    }


}
