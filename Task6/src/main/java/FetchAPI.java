import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;

/**
 * This class offers a method to make a request to coinmarketcap API
 */
public class FetchAPI {
    //For further details see here: https://coinmarketcap.com/api/
    private final String ENTRY_POINT = "https://api.coinmarketcap.com/v1/ticker/";

    /**
     * Perform a request to coinmarketcap api
     * @param query the query to ask for. See documentation for further information
     * @return JSON Array with all coins
     */
    public JSONArray getRequest(String query){
        String urlString = ENTRY_POINT + query;
        URL url;
        URLConnection conn;
        InputStream is;

        //Let's do the request
        try {
            url = new URL(urlString);
            conn = url.openConnection();
            is = conn.getInputStream();
        } catch (Exception e) {
            //Page not found
            return null;
        }

        //Try to parse request to JSONArr and return it
        try {
            int character;
            String result = "";
            while((character = is.read()) != -1){
                result += (char)character;
            }
            return new JSONArray(result);
        } catch (IOException e) {
            //Error while parsing
            return null;
        }
    }
}
