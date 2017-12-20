import com.fasterxml.jackson.databind.ObjectMapper;
import generated.BookingsJackson;

import java.io.*;

public class JSONUnmarshalling {

    private static final String PATH_TO_JSON = "foreign_data/556056_Ulrich_Overdieck.json";

    public static void main(String[] args) throws IOException {
        //Load JSON from disk
        String content = new JSONUnmarshalling().load();

        //Create Jackson ObjectMapper and parse the JSON
        ObjectMapper objectMapper = new ObjectMapper();
        BookingsJackson obj = objectMapper.readValue(content, BookingsJackson.class);

        //Print out each booking
        for(int i = 0; i < obj.bookings.length; i++){
            System.out.println(obj.bookings[i]);
        }
    }

    /**
     * Loads the JSON
     * @return a string which contains file content
     * @throws FileNotFoundException in case the file doesn't exists
     */
    public String load() throws FileNotFoundException {
        //get JSON file
        File fileToRead = new File(this.getClass().getResource(PATH_TO_JSON).toString().split(":")[1]);
        String lines = "";
        try( FileReader fileStream = new FileReader( fileToRead );
             BufferedReader bufferedReader = new BufferedReader( fileStream ) ) {
            String line;
            while( (line = bufferedReader.readLine()) != null ) {
                lines += line;
            }
        } catch ( IOException ex ) {
            System.err.println("Sth went wrong while reading the file");
        }
        return lines;
    }


}
