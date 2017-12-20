import generated.Bookings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLUnmarshalling {

    private static final String PATH_TO_XML = "foreign_data/556056_Ulrich_Overdieck.xml";

    public static void main( String[] args ) throws JAXBException
    {
        try {
            //Load file from disk
            File file = new XMLUnmarshalling().loadFile();

            //Create JAXB instance
            JAXBContext jaxbContext = JAXBContext.newInstance(Bookings.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //Parsing the loaded file
            Bookings books = (Bookings) jaxbUnmarshaller.unmarshal(file);

            //Print out each booking
            for(int i = 0; i < books.getBooking().size(); i++){
                System.out.println(books.getBooking().get(i));
            }

        } catch (JAXBException e) {
            System.err.println("Sth went wrong while mapping from XML to classes");
            e.printStackTrace();
        }
    }

    /**
     * Load the XML file
     * @return File with XML
     */
    public File loadFile(){
        return new File(this.getClass().getResource(PATH_TO_XML).toString().split(":")[1]);
    }


}
