package stackpointer.stackexchange;

import java.util.ArrayList;

/**
 * This class is to interact with the stack exchange API, including retrieval
 * of data, and parting it.
 * @author Phil
 */
public class StackExchangeInterface {
    StackExchangeInterface()
    {
        //TODO - Initialize values
        m_top100Questions = new ArrayList<String>();
    }

    void establishConnection()
    {
        //TODO - input connection steps and set boolean result value
        m_connected = false;
    }

    //Simple getter function to ensure that connection is valid
    boolean isConnectionEstablished()
    {
        return m_connected;
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
        //TODO - access database, grab 100 questions, push onto list
        m_top100Questions.add("one");
        m_top100Questions.add("two");
    }

    //Return the top 100 questions
    ArrayList<String> getTop100Questions()
    {
        return m_top100Questions;
    }

    boolean m_connected;
    ArrayList<String> m_top100Questions;
}
