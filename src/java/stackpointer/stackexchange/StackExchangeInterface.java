package stackpointer.stackexchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stackpointer.common.Location;
import stackpointer.common.User;
import stackpointer.database.DatabaseConnectionInfo;
import stackpointer.database.MySQLDatabaseFacade;
import stackpointer.googlemaps.GoogleMapsInterface;

/**
 * This class is to interact with the stack exchange API, including retrieval
 * of data, and parting it.
 * @author Phil
 */
public class StackExchangeInterface {
    private ArrayList<Question> top100Questions = new ArrayList<Question>();
    MySQLDatabaseFacade db = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
    final static String baseUrl = "https://api.stackexchange.com/2.1/";
    final static String sxKey = "ubwVxucHGeVndxd5knjnMg((";
    final static String qFilter = "!*dP_kUhzr8b7po)234wUBU01ttKHwGgK3Nzyb";
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
        HashMap<Integer, User> owners = new HashMap<Integer, User>();
        try
        {
            JSONArray questions = json.getJSONArray("items");
            for(int q=0; q<questions.length(); q++)
            {
                JSONObject jQuestion = questions.getJSONObject(q);
                User owner = parseUserFromJson(jQuestion.getJSONObject("owner"));
                Question toAdd = new Question();
                toAdd.setAskedBy(owner);
                toAdd.setqTitle(jQuestion.getString("title"));
                toAdd.setqText(jQuestion.getString("body"));
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
    
    public static User parseUserFromJson(JSONObject json)
    {
        User user = new User();
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
    
    public static void populateLocations(HashMap<Integer, User> owners)
    {
        StringBuilder ownerIds = new StringBuilder();
        for (User o : owners.values())
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
    public boolean persistTopQuestions()
    {
        boolean success = false;
        top100Questions  = getQuestionsFromServer();
        if(top100Questions!=null)
        {
            if(!top100Questions.isEmpty())
            {
                for(Question q:top100Questions)
                {
                    if(db.addQuestion(q)==false)
                    {
                        System.out.println("Error saving question "+q);
                    }
                }
                success = true;
            }
        }
        return success;
    }

    //Get the local copies of the top 100 questions
    public void retrieveTopQuestions()
    {
        List<Question> q = db.retrieveQuestions();
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
