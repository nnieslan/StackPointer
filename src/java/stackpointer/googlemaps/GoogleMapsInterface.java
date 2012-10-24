/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.googlemaps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import stackpointer.common.Location;
        
/**
 *
 * @author Phil
 */
public class GoogleMapsInterface {
    static String gMapsUrlBase = "https://maps.googleapis.com/maps/api/";
    
    public static Location geocode(String locString)
    {
        Location toReturn = null;
        try {
            URL url = new URL(gMapsUrlBase+"geocode/json?sensor=false&address="+URLEncoder.encode(locString, "UTF-8"));
            URLConnection conn = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }
            try
            {
                JSONObject json = new JSONObject(builder.toString());
                JSONArray results = json.getJSONArray("results");
                if(!results.isNull(0))
                {
                    JSONObject jLoc = results.getJSONObject(0).
                            getJSONObject("geometry").getJSONObject("location");
                    toReturn = new Location(locString);
                    toReturn.setLat(jLoc.getDouble("lat"));
                    toReturn.setLon(jLoc.getDouble("lng"));
                }
            }
            catch(JSONException e)
            {
                System.out.println("Error parsing JSON geocoding string: "+e);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error geocoding "+locString+":\n"+e);
        }
        return toReturn;
    }
}
