package stackpointer.jobs;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.InputStreamReader;
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
import stackpointer.database.MySQLDatabaseFacade;
import java.util.Date;
import stackpointer.common.Location;

/**
 * This class is to interact with the Linked In API, including retrieval
 * of data, and parsing it.
 * @author JOE
 */
public class LinkedInInterface {
    private boolean connected;
    private ArrayList<JobPosting> allJobPostings;
    MySQLDatabaseFacade db = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
    //   private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/job-search:(jobs,facets)?facet=location,us:100";
    private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/job-search:(jobs:(id,posting-date,company,position,location-description,description))";
    public static void main(String[] args)
   {
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
    System.out.println("Paste Verifier");
    System.out.print(">>");
    Verifier verifier = new Verifier(in.nextLine());
    System.out.println();

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
        ArrayList<JobPosting> parsedJobs = parseJobsFromJson(linkedInJson);
        //save parsed jobs... etc.
        System.out.println(parsedJobs);
    }
    catch (JSONException e)
    {
        System.out.println("Error retrieving Jobs from LinkedIn:\n"+e);
    }
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
                toAdd.setHeadline(jJob.getJSONObject("position").getString("title"));
                toAdd.setLinkedInId(jJob.getInt("id"));
                toAdd.setDescription(jJob.getString("description"));
                Date jobDate = new Date(jJob.getJSONObject("postingDate").getInt("year"), 
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
    
    //Function to repopulate local LinkedIn user database
    boolean updateLocalDatabase()
    {
        //TODO - access LinkedIn, grab user and friends and save data
        return false;
    }

    //Function to update current values from LinkedIn in local database
    boolean cleanDatabase()
    {
        //TODO - validate all current local data
        return false;
    }

    //Update the local copies of the top 100 questions
    void updateJobPostings()
    {
         //TODO - access database, grab 100 questions, push onto list
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
        allJobPostings.add(new JobPosting(new Location(0,0,0), new Date(), "Headline1", "Desc1", "Company1"));
    }

    //Return the top 100 questions
    ArrayList<JobPosting> getJobPostings()
    {
        return allJobPostings;
    }
}
