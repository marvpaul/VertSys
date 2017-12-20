package generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/*
Generated from https://timboudreau.com/blog/json/read
 */
public final class BookingsJackson {
    public final Booking bookings[];

    @JsonCreator
    public BookingsJackson(@JsonProperty("bookings") Booking[] bookings){
        this.bookings = bookings;
    }

    public static final class Booking {
        public final String orderPerson;
        public final String bookingId;
        public final Passenger passengers[];
        public final Flight flights[];
        public final Price price[];

        @Override
        public String toString() {
            return "Booking{" +
                    "passengers=" + Arrays.toString(passengers) +
                    ", flights=" + Arrays.toString(flights) +
                    ", price=" + Arrays.toString(price) +
                    ", orderPerson='" + orderPerson + '\'' +
                    ", bookingId='" + bookingId + '\'' +
                    '}';
        }

        @JsonCreator
        public Booking(@JsonProperty("-orderPerson") String orderPerson, @JsonProperty("-bookingId") String bookingId, @JsonProperty("passengers") Passenger[] passengers, @JsonProperty("flights") Flight[] flights, @JsonProperty("price") Price[] price){
            this.orderPerson = orderPerson;
            this.bookingId = bookingId;
            this.passengers = passengers;
            this.flights = flights;
            this.price = price;


        }

        public static final class Passenger {
            public final String lastName;
            public final String firstName;
            public final String address;
            public final String postCode;
            public final String city;
            public final String country;

            @JsonCreator
            public Passenger(@JsonProperty("lastName") String lastName, @JsonProperty("firstName") String firstName, @JsonProperty("address") String address, @JsonProperty("postCode") String postCode, @JsonProperty("city") String city, @JsonProperty("country") String country){
                this.lastName = lastName;
                this.firstName = firstName;
                this.address = address;
                this.postCode = postCode;
                this.city = city;
                this.country = country;
            }

            @Override
            public String toString() {
                return "Passenger{" +
                        "lastName='" + lastName + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", address='" + address + '\'' +
                        ", postCode='" + postCode + '\'' +
                        ", city='" + city + '\'' +
                        ", country='" + country + '\'' +
                        '}';
            }
        }

        public static final class Flight {
            public final String flightNumber;
            public final String departureLocation;
            public final String departureDate;
            public final String departureTime;
            public final String destinationLocation;
            public final String destinationDate;
            public final String destinationTime;
            public final String _class;
            public final String isSecureFlight;

            @JsonCreator
            public Flight(@JsonProperty("-flightNumber") String flightNumber, @JsonProperty("departureLocation") String departureLocation, @JsonProperty("departureDate") String departureDate, @JsonProperty("departureTime") String departureTime, @JsonProperty("destinationLocation") String destinationLocation, @JsonProperty("destinationDate") String destinationDate, @JsonProperty("destinationTime") String destinationTime, @JsonProperty("class") String _class, @JsonProperty("isSecureFlight") String isSecureFlight){
                this.flightNumber = flightNumber;
                this.departureLocation = departureLocation;
                this.departureDate = departureDate;
                this.departureTime = departureTime;
                this.destinationLocation = destinationLocation;
                this.destinationDate = destinationDate;
                this.destinationTime = destinationTime;
                this._class = _class;
                this.isSecureFlight = isSecureFlight;
            }

            @Override
            public String toString() {
                return "Flight{" +
                        "flightNumber='" + flightNumber + '\'' +
                        ", departureLocation='" + departureLocation + '\'' +
                        ", departureDate='" + departureDate + '\'' +
                        ", departureTime='" + departureTime + '\'' +
                        ", destinationLocation='" + destinationLocation + '\'' +
                        ", destinationDate='" + destinationDate + '\'' +
                        ", destinationTime='" + destinationTime + '\'' +
                        ", _class='" + _class + '\'' +
                        ", isSecureFlight='" + isSecureFlight + '\'' +
                        '}';
            }
        }

        public static final class Price {
            public final String amount;
            public final String currency;

            @JsonCreator
            public Price(@JsonProperty("amount") String amount, @JsonProperty("currency") String currency){
                this.amount = amount;
                this.currency = currency;
            }

            @Override
            public String toString() {
                return "Price{" +
                        "amount='" + amount + '\'' +
                        ", currency='" + currency + '\'' +
                        '}';
            }
        }
    }
}
