package stackpointer.jobs;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;
import stackpointer.common.Location;
import stackpointer.database.DatabaseConnectionInfo;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import stackpointer.common.Location;
import stackpointer.common.SXUser;
import stackpointer.googlemaps.GoogleMapsInterface;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import stackpointer.stackexchange.StackExchangeInterface;
import stackpointer.database.SXUserEntity;
import stackpointer.jobs.JobPosting;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import stackpointer.database.DBUtils;
import stackpointer.database.JobsDatabaseFacade;
import stackpointer.database.SXDatabaseFacade;
import stackpointer.stackexchange.Question;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import stackpointer.common.Location;


/**
 * This class is to interact with the Linked In API, including retrieval
 * of data, and parsing it.
 * @author JOE
 */
public class LinkedInInterface {
    private boolean connected;
    private ArrayList<JobPosting> allJobPostings;
    //static String Location = "dayton";
    //static String Location = new stackpointer.common.Location.getLocStr();
  
    //   private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/job-search:(jobs,facets)?facet=location,us:100";
    private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/job-search:(jobs:(id,posting-date,company,position,location-description,description-snippet))?count=20";
    
    //Return Closest 10 Jobs to the SXUser
    public static ArrayList<JobPosting> getJobPostings() throws Exception
    {
        ArrayList<JobPosting> parsedJobs = null;
        JSONObject json = null;   
        OAuthService service = new ServiceBuilder()
                                .provider(LinkedInApi.class)
                                .apiKey("v6xty7mvo61a")
                                .apiSecret("N6Ggy9kfyJc3fVO0")
                             // .callback ("http://localhost:8080/StackPointer/")
                                .build();
        Scanner in = new Scanner(System.in);

        System.out.println("=== LinkedIn's OAuth Workflow ===");
        System.out.println();

        // Obtain the Request Token
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("Retrieved Request Token!");
        System.out.println();

        System.out.println("Authorize Scribe:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        //System.out.println("Paste Verifier");
        //System.out.print(">>");
        //Verifier verifier = new Verifier(in.nextLine());
        //System.out.println();
        String authUrl = service.getAuthorizationUrl(requestToken);
      
        Verifier verifier = new Verifier(getVerifier(authUrl));
        //Verifier verifier = new Verifier(JOptionPane.showInputDialog("Verifier"));

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(requestToken, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(Access Token: " + accessToken + " )");
        System.out.println();

        // Access Linked In URL!
        System.out.println("Contact API URL");
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);

        // Pull results in JSON format
        request.addHeader("x-li-format", "json");
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println(response.getBody());    
        try
        {
            JSONObject linkedInJson = new JSONObject(response.getBody()).getJSONObject("jobs");
            parsedJobs = parseJobsFromJson(linkedInJson);
        }
        catch (JSONException e)
        {
            System.out.println("Error retrieving Jobs from LinkedIn:\n"+e);
        }
        return parsedJobs;
    }
    
    
     public static ArrayList<JobPosting> parseJobsFromJson(JSONObject json)
    {
        ArrayList<JobPosting> parsed = new ArrayList<JobPosting>();
        try
        {
            JSONArray jobs = json.getJSONArray("values");
            for(int j=0; j<jobs.length(); j++)
            {
                JSONObject jJob = jobs.getJSONObject(j);
                JobPosting toAdd = new JobPosting();
                toAdd.setCompany(jJob.getJSONObject("company").getString("name"));
                Location jobLoc = new Location(jJob.getString("locationDescription"));
                toAdd.setLoc(jobLoc);
                toAdd.setLoc(GoogleMapsInterface.geocode(jJob.getString("locationDescription")));
                toAdd.setHeadline(jJob.getJSONObject("position").getString("title"));
                toAdd.setLinkedInId(jJob.getInt("id"));
                toAdd.setDescription(jJob.getString("descriptionSnippet"));
                Date jobDate = new Date(jJob.getJSONObject("postingDate").getInt("year")-1900, 
                        jJob.getJSONObject("postingDate").getInt("month"), 
                        jJob.getJSONObject("postingDate").getInt("day"));       
                toAdd.setDatePosted(jobDate);
                parsed.add(toAdd);
            }
        }
        catch (JSONException e)
        {
            System.out.println("Error parsing JSON Jobs string: "+e);
        }
        return parsed;
    }
     
    public LinkedInInterface()
    {
    //TODO - Initialize values
        allJobPostings = new ArrayList<JobPosting>();
    }

    void establishConnection()
    {
       
        //TODO - input connection steps and set boolean result value
        connected = true;
    }

    //Simple getter function to ensure that connection is valid
    boolean isConnectionEstablished()
    {
        return connected;
    }
    
    //Function to update local linkedIn database
    public boolean updateLocalDatabase() throws Exception
    {
        boolean success = false;
        JobsDatabaseFacade db = new JobsDatabaseFacade();
        allJobPostings  = getJobPostings();
        
        if(allJobPostings!=null)
        {
            if(!allJobPostings.isEmpty())
            {
                int numAdded = db.syncJobPostings(allJobPostings);
                success = numAdded != -1;
            }
        }
        return success;
    }
    
    //Get the local copies of the top JobPostings
    public boolean retrieveJobPostings()
    {
        List<JobPosting> j = null;
        
        JobsDatabaseFacade db = new JobsDatabaseFacade();
        j = db.retrieveAllJobPostings();
        
        if(j != null && !j.isEmpty())
        {   
            allJobPostings.clear();
            allJobPostings.addAll(j);
        }
        
        return true;
    }
    
    //Return the top JobPostings
    public ArrayList<JobPosting> getallJobPostings()
    {
        return allJobPostings;
    }

    //Update the local copies of job postings
    public boolean updateJobPostings()
    {
        JobsDatabaseFacade db = new JobsDatabaseFacade();
        List<JobPosting> j = db.retrieveAllJobPostings();
        if(j != null && !j.isEmpty())
        {
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
        }
        
        return true;
    }
    
    static String getVerifier(String url) throws Exception {
    
      
          //String LINKEDIN_USERNAME = JOptionPane.showInputDialog("Login with LinkedIn Username");
          //String LINKEDIN_PASSWORD = JOptionPane.showInputDialog("Enter LinkedIn Password");
          String LINKEDIN_USERNAME = "stackpointermashup@gmail.com";
          String LINKEDIN_PASSWORD = "lokiju*&";
	  final WebClient webClient = new WebClient();
          webClient.setJavaScriptEnabled(false);

          // Get the first page
          final HtmlPage page1 = webClient.getPage(url);        

          // Get the form that we are dealing with and within that form,
          // find the submit button and the field that we want to change.
          final HtmlForm form = page1.getFormByName("oauthAuthorizeForm");

          final HtmlSubmitInput button = form.getInputByName("authorize");
          final HtmlTextInput textField = form.getInputByName("session_key");
          final HtmlPasswordInput textField2 = form.getInputByName("session_password");
          
          // Change the value of the text field
          textField.setValueAttribute(LINKEDIN_USERNAME);
          textField2.setValueAttribute(LINKEDIN_PASSWORD);

          // Now submit the form by clicking the button and get back the second page.
          final HtmlPage page2 = button.click();

          // Obtain the 5-digit access code from the returned page
          String text = page2.asText();
          int i = 0;
          while (text.charAt(i)>'9' || text.charAt(i)<'0') i++;
          String verifier = text.substring(i, i+5);               
          webClient.closeAllWindows(); 
 
          return verifier;
      }
    
   //Function to update current values from LinkedIn in local database
   //boolean cleanDatabase()
   //{
   //TODO - validate all current local data
   //  return false;
   //}
}