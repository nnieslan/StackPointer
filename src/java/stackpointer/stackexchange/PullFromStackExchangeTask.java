package stackpointer.stackexchange;

import java.util.TimerTask;

/**
 *
 * @author Phil
 */
public class PullFromStackExchangeTask extends TimerTask {

    @Override
    public void run() {
        if(StackExchangeInterface.persistTopQuestions())
        {
            System.out.println("Latest questions successfully persisted");
        }
        else
        {
            System.out.println("Problem persisting questions");
        }
    }
    
}
