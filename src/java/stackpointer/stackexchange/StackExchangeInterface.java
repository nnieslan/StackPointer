package stackpointer.stackexchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONObject;
import stackpointer.common.User;

/**
 * This class is to interact with the stack exchange API, including retrieval
 * of data, and parting it.
 * @author Phil
 */
public class StackExchangeInterface {
    private boolean connected = false;
    private ArrayList<Question> top100Questions;

    StackExchangeInterface()
    {
        //TODO - Initialize values
        top100Questions = new ArrayList<Question>();
    }
    
    private JSONObject getQuestionsFromServer()
    {
        JSONObject json = null;
        if(isConnected())
        {
            try {
                URL url = new URL("https://api.stackexchange.com/2.1/questions?page=1&pagesize=100&order=desc&sort=creation&site=stackoverflow");
                URLConnection conn = url.openConnection();
                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                json = new JSONObject(builder.toString());
            }
            catch (Exception e)
            {
                System.out.println("Error retrieving top 100 questions from StackExchange:\n"+e);
            }
        }
        else
        {
            System.out.println("Not connected. Establish connection first.");
        }
        
        return json;
    }

    void setConnected(boolean c)
    {
        //TODO - input connection steps and set boolean result value
        connected = c;
    }

    //Simple getter function to ensure that connection is valid
    boolean isConnected()
    {
        return connected;
    }
    
    //Function to repopulate local StackExchange user database
    boolean updateLocalDatabase()
    {
        //TODO - access StackExchange, grab user and friends and save data
        return false;
    }

    //Function to update current values from StackExchange in local database
    boolean cleanDatabase()
    {
        //TODO - validate all current local data
        return false;
    }

    //Update the local copies of the top 100 questions
    void updateTopQuestions()
    {
        JSONObject SXjson  = getQuestionsFromServer();
        if(SXjson!=null)
        {
            //TODO - access database, grab 100 questions, push onto list
            top100Questions.add(new Question(new User("asdf","asdf"),"QText",null));
            top100Questions.add(new Question(new User("asdf","asdf"),"QText",null));
        }
    }

    //Return the top 100 questions
    ArrayList<Question> getTop100Questions()
    {
        return top100Questions;
    }


}
