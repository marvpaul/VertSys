import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    int port;
    ServerSocket sock;
    Socket clientSock;

    /**
     * Constructor for server
     * Initialize a ServerSocket and bind to a given port.
     * Wait for connection, try to find some data which match the request and send them back.
     * Otherwise the server response with an error message
     * @param port the port the serversocket is bind to
     */
    public Server(int port) {
        this.port = port;
        try {
            sock = new ServerSocket(port);
            clientSock = sock.accept();

            //Connection established, do some fancy stuff here
            processReq();
        } catch (IOException e) {
            System.err.println("ERROR: Sth failed while creating server socket :( ");
        }
    }

    /**
     * Process a request from client and sends back some temperature data
     * in case the client had send a valid date
     */
    public void processReq(){
        //Using auto cloable for reader ;)
        try( BufferedReader reader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()))){
            String mess = reader.readLine();
            String returnMess = getData(mess);

            PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
            writer.println(returnMess);
            writer.flush();

            //Close sockets
            clientSock.close();
            sock.close();
        } catch (IOException e) {
            System.err.println("ERROR: Problem while opening / close a stream");
        }
    }

    /**
     * This reads some values out of a csv and returns as ArrayList
     * @param key a date, format: "YYYY-MM-dd"
     * @return A list of temperatures, sorted from 0 - 23, seperated by ,
     */
    public String getData(String key){
        //Source: https://commons.apache.org/proper/commons-csv/user-guide.html
        try {
            //Load weather data
            System.out.println(this.getClass().getClassLoader().getResource("").getPath());
            InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("weather.csv");
            Reader in = new InputStreamReader(inStream);
            //First row is header
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            String values = "";

            //Read each record and search for the value for a given key
            for (CSVRecord record : records) {
                try{
                    values += record.get(key) + ",";
                }
                catch (Exception e){
                    return "ERROR: Invalid date, please enter a date with format: YYYY-mm-dd";
                }
            }

            //Clonse streams :)
            inStream.close();
            in.close();

            //Just remove the last seperator and return the list
            return values.substring(0, values.length() - 1);
        } catch (Exception e) {
            return "ERROR: Failed to loading data from server!";
        }
    }
}
