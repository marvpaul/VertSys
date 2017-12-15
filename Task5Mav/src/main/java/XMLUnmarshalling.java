import generated.Bookings;
import jdk.internal.org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLUnmarshalling {
    public static void main( String[] args ) throws JAXBException, SAXException
    {
        try {

            File file = new XMLUnmarshalling().loadFile();
            JAXBContext jaxbContext = JAXBContext.newInstance(Bookings.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Bookings books = (Bookings) jaxbUnmarshaller.unmarshal(file);
            for(int i = 0; i < books.getBooking().size(); i++){
                System.out.println(books.getBooking().get(i));
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public File loadFile(){
        return new File(this.getClass().getResource("foreign_data/556056_Ulrich_Overdieck.xml").toString().split(":")[1]);
    }


}
