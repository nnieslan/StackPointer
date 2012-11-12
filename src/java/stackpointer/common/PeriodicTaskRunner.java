package stackpointer.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import stackpointer.stackexchange.PullFromStackExchangeTask;

/**
 *
 * @author Phil
 */
public class PeriodicTaskRunner implements ServletContextListener {
    
    private ScheduledExecutorService scheduler;
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Initializing periodic task runner.");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new PullFromStackExchangeTask(), 1, 10, TimeUnit.MINUTES);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Shutting down periodic task runner.");
        scheduler.shutdownNow();
    }
}
