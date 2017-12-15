//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 generiert 
// Siehe <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
// xc4nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.12.15 um 06:37:23 PM CET 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse fxFCr booking complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="booking"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="passengers" type="{}personType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="flights" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="departureLocation" type="{}locationType"/&gt;
 *                   &lt;element name="departureDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="departureTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
 *                   &lt;element name="destinationLocation" type="{}locationType"/&gt;
 *                   &lt;element name="destinationDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="destinationTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
 *                   &lt;element name="class" type="{}classType"/&gt;
 *                   &lt;element name="isSecureFlight" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="flightNumber" use="required" type="{}flightNumberType" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="price"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="currency" type="{}currencyType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="orderPerson" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="bookingId" use="required" type="{}orderIdType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "booking", propOrder = {
    "passengers",
    "flights",
    "price"
})
public class Booking {

    @XmlElement(required = true)
    protected List<PersonType> passengers;
    @XmlElement(required = true)
    protected List<Booking.Flights> flights;
    @XmlElement(required = true)
    protected Booking.Price price;
    @XmlAttribute(name = "orderPerson", required = true)
    protected String orderPerson;
    @XmlAttribute(name = "bookingId", required = true)
    protected String bookingId;

    /**
     * Gets the value of the passengers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passengers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassengers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonType }
     * 
     * 
     */
    public List<PersonType> getPassengers() {
        if (passengers == null) {
            passengers = new ArrayList<PersonType>();
        }
        return this.passengers;
    }

    /**
     * Gets the value of the flights property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flights property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlights().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Booking.Flights }
     * 
     * 
     */
    public List<Booking.Flights> getFlights() {
        if (flights == null) {
            flights = new ArrayList<Booking.Flights>();
        }
        return this.flights;
    }

    /**
     * Ruft den Wert der price-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Booking.Price }
     *     
     */
    public Booking.Price getPrice() {
        return price;
    }

    /**
     * Legt den Wert der price-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Booking.Price }
     *     
     */
    public void setPrice(Booking.Price value) {
        this.price = value;
    }

    /**
     * Ruft den Wert der orderPerson-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderPerson() {
        return orderPerson;
    }

    /**
     * Legt den Wert der orderPerson-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderPerson(String value) {
        this.orderPerson = value;
    }

    /**
     * Ruft den Wert der bookingId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Legt den Wert der bookingId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingId(String value) {
        this.bookingId = value;
    }


    /**
     * <p>Java-Klasse fxFCr anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="departureLocation" type="{}locationType"/&gt;
     *         &lt;element name="departureDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="departureTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
     *         &lt;element name="destinationLocation" type="{}locationType"/&gt;
     *         &lt;element name="destinationDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="destinationTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
     *         &lt;element name="class" type="{}classType"/&gt;
     *         &lt;element name="isSecureFlight" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="flightNumber" use="required" type="{}flightNumberType" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "departureLocation",
        "departureDate",
        "departureTime",
        "destinationLocation",
        "destinationDate",
        "destinationTime",
        "clazz",
        "isSecureFlight"
    })
    public static class Flights {

        @XmlElement(required = true)
        protected String departureLocation;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar departureDate;
        @XmlElement(required = true)
        @XmlSchemaType(name = "time")
        protected XMLGregorianCalendar departureTime;
        @XmlElement(required = true)
        protected String destinationLocation;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar destinationDate;
        @XmlElement(required = true)
        @XmlSchemaType(name = "time")
        protected XMLGregorianCalendar destinationTime;
        @XmlElement(name = "class", required = true)
        @XmlSchemaType(name = "string")
        protected ClassType clazz;
        protected boolean isSecureFlight;
        @XmlAttribute(name = "flightNumber", required = true)
        protected String flightNumber;

        /**
         * Ruft den Wert der departureLocation-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDepartureLocation() {
            return departureLocation;
        }

        /**
         * Legt den Wert der departureLocation-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDepartureLocation(String value) {
            this.departureLocation = value;
        }

        /**
         * Ruft den Wert der departureDate-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDepartureDate() {
            return departureDate;
        }

        /**
         * Legt den Wert der departureDate-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDepartureDate(XMLGregorianCalendar value) {
            this.departureDate = value;
        }

        /**
         * Ruft den Wert der departureTime-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDepartureTime() {
            return departureTime;
        }

        /**
         * Legt den Wert der departureTime-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDepartureTime(XMLGregorianCalendar value) {
            this.departureTime = value;
        }

        /**
         * Ruft den Wert der destinationLocation-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDestinationLocation() {
            return destinationLocation;
        }

        /**
         * Legt den Wert der destinationLocation-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDestinationLocation(String value) {
            this.destinationLocation = value;
        }

        /**
         * Ruft den Wert der destinationDate-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDestinationDate() {
            return destinationDate;
        }

        /**
         * Legt den Wert der destinationDate-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDestinationDate(XMLGregorianCalendar value) {
            this.destinationDate = value;
        }

        /**
         * Ruft den Wert der destinationTime-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDestinationTime() {
            return destinationTime;
        }

        /**
         * Legt den Wert der destinationTime-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDestinationTime(XMLGregorianCalendar value) {
            this.destinationTime = value;
        }

        /**
         * Ruft den Wert der clazz-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link ClassType }
         *     
         */
        public ClassType getClazz() {
            return clazz;
        }

        /**
         * Legt den Wert der clazz-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link ClassType }
         *     
         */
        public void setClazz(ClassType value) {
            this.clazz = value;
        }

        /**
         * Ruft den Wert der isSecureFlight-Eigenschaft ab.
         * 
         */
        public boolean isIsSecureFlight() {
            return isSecureFlight;
        }

        /**
         * Legt den Wert der isSecureFlight-Eigenschaft fest.
         * 
         */
        public void setIsSecureFlight(boolean value) {
            this.isSecureFlight = value;
        }

        /**
         * Ruft den Wert der flightNumber-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFlightNumber() {
            return flightNumber;
        }

        /**
         * Legt den Wert der flightNumber-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFlightNumber(String value) {
            this.flightNumber = value;
        }

        @Override
        public String toString() {
            return "Flights{" +
                    "departureLocation='" + departureLocation + '\'' +
                    ", departureDate=" + departureDate +
                    ", departureTime=" + departureTime +
                    ", destinationLocation='" + destinationLocation + '\'' +
                    ", destinationDate=" + destinationDate +
                    ", destinationTime=" + destinationTime +
                    ", clazz=" + clazz +
                    ", isSecureFlight=" + isSecureFlight +
                    ", flightNumber='" + flightNumber + '\'' +
                    '}';
        }
    }


    /**
     * <p>Java-Klasse fxFCr anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="currency" type="{}currencyType"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "amount",
        "currency"
    })
    public static class Price {

        protected double amount;
        @XmlElement(required = true)
        @XmlSchemaType(name = "string")
        protected CurrencyType currency;

        /**
         * Ruft den Wert der amount-Eigenschaft ab.
         * 
         */
        public double getAmount() {
            return amount;
        }

        /**
         * Legt den Wert der amount-Eigenschaft fest.
         * 
         */
        public void setAmount(double value) {
            this.amount = value;
        }

        /**
         * Ruft den Wert der currency-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link CurrencyType }
         *     
         */
        public CurrencyType getCurrency() {
            return currency;
        }

        /**
         * Legt den Wert der currency-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link CurrencyType }
         *     
         */
        public void setCurrency(CurrencyType value) {
            this.currency = value;
        }

        @Override
        public String toString() {
            return "Price{" +
                    "amount=" + amount +
                    ", currency=" + currency +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "passengers=" + passengers +
                ", flights=" + flights +
                ", price=" + price +
                ", orderPerson='" + orderPerson + '\'' +
                ", bookingId='" + bookingId + '\'' +
                '}';
    }
}
