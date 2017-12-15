//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 generiert 
// Siehe <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
// xc4nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.12.15 um 06:37:23 PM CET 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Bookings_QNAME = new QName("", "bookings");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Booking }
     * 
     */
    public Booking createBooking() {
        return new Booking();
    }

    /**
     * Create an instance of {@link Bookings }
     * 
     */
    public Bookings createBookings() {
        return new Bookings();
    }

    /**
     * Create an instance of {@link PersonType }
     * 
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link Booking.Flights }
     * 
     */
    public Booking.Flights createBookingFlights() {
        return new Booking.Flights();
    }

    /**
     * Create an instance of {@link Booking.Price }
     * 
     */
    public Booking.Price createBookingPrice() {
        return new Booking.Price();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bookings }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Bookings }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "bookings")
    public JAXBElement<Bookings> createBookings(Bookings value) {
        return new JAXBElement<Bookings>(_Bookings_QNAME, Bookings.class, null, value);
    }

}
