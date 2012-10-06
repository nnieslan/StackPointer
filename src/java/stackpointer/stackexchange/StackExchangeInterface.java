package stackpointer.stackexchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONObject;
import stackpointer.database.DatabaseConnectionInfo;
import stackpointer.database.MySQLDatabaseFacade;

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

    public StackExchangeInterface()
    {
        //TODO - Initialize values
    }
    
    public static ArrayList<Question> getQuestionsFromServer()
    {
        ArrayList<Question> servQs = null;
        JSONObject json = null;        
        try {
            URL url = new URL(baseUrl+"questions?key="+sxKey+"&page=1&pagesize=100&order=desc&sort=creation&site=stackoverflow");
            URLConnection conn = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            //input is gzipped
            BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            json = new JSONObject(builder.toString());
            //TODO - parse json into questions
            servQs = new ArrayList<Question>();
        }
        catch (Exception e)
        {
            System.out.println("Error retrieving top 100 questions from StackExchange:\n"+e);
        }
        
        return servQs;
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
