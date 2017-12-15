import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import generated.BookingsJackson;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class JSONUnmarshalling {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String content = new JSONUnmarshalling().load();
        //JSON from file to Object
        BookingsJackson obj = objectMapper.readValue(content, BookingsJackson.class);
        for(int i = 0; i < obj.bookings.length; i++){
            System.out.println(obj.bookings[i]);
        }

        /*
        Map<String, Object> map = objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});

        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            ArrayList list = (ArrayList)entry.getValue();
            for(int i = 0; i < list.size(); i++){
                System.out.println("Booking" + list.get(i));
            }
        }
        String jsonInString = "{'name' : 'mkyong'}";*/


    }

    public String load() throws FileNotFoundException {
        File fileToRead = new File(this.getClass().getResource("schema/556056_Ulrich_Overdieck.json").toString().split(":")[1]);
        String lines = "";
        try( FileReader fileStream = new FileReader( fileToRead );
             BufferedReader bufferedReader = new BufferedReader( fileStream ) ) {

            String line = null;

            while( (line = bufferedReader.readLine()) != null ) {
                lines += line;
            }

        } catch ( FileNotFoundException ex ) {
            //exception Handling
        } catch ( IOException ex ) {
            //exception Handling
        }
        return lines;
    }


}
