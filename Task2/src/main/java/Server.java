import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    int port;
    ServerSocket sock;
    Socket clientSock;
    public Server(int port) {
        this.port = port;
    }

    public void run(){
        try {
            sock = new ServerSocket(port);
            waitForConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForConnection(){
        try {
            clientSock = sock.accept();

            //Connection established, do some fancy stuff here
            processReq();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processReq(){
        //Using auto cloable for reader
        try( BufferedReader reader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()))){
            String mess = reader.readLine();
            String returnMess = getData(mess);

            PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
            writer.println(returnMess);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeClientCon();
    }

    /**
     * This reads some values out of a csv and returns as ArrayList
     * @param key a date, format: "YYYY-MM-dd"
     * @return A list of temperatures, sorted from 0 - 23, seperated by ,
     */
    public String getData(String key){
        //Source: https://commons.apache.org/proper/commons-csv/user-guide.html
        try {
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
            //Just remove the last seperator and return the list :)
            return values.substring(0, values.length() - 1);
        } catch (Exception e) {
            return "ERROR: Failed to loading data from server!";
        }
    }

    public void closeClientCon(){
        try{
            clientSock.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
