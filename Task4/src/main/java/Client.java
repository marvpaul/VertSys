import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Client{
    String ip, message;
    int port;
    private Socket sock;

    /**
     * Constructor for Client
     * Initialize a socketStream with given data, send them out,
     * wait for response and process this response
     * @param ip the IP-Adress of the server you want to connect to
     * @param port the port of the weather app
     * @param message shound be a date string in following format: YYYY-DD-mm
     */
    public Client(String ip, int port, String message) {
        this.ip = ip;
        this.port = port;
        this.message = message;

        try {
            sock = new Socket(ip, port);

            try(PrintWriter serverWriter = new PrintWriter(sock.getOutputStream())){
                serverWriter.println(message);
                serverWriter.flush();

                //Wait for a response from server
                Scanner in = new Scanner( sock.getInputStream() );
                String receivedData =in.nextLine();

                processReceivedData(receivedData);

                sock.close();
            } catch (IOException e) {
                System.err.println("ERROR: Sth failed while receiving data");
            }
        } catch (IOException e) {
            System.err.println("ERROR: Sth failed while opening socket");
        }
    }

    /**
     * Try to parse the received data, in case of an error just print this error.
     * Otherwise print the received data!
     * @param receivedData the data received from the weather server
     */
    private void processReceivedData(String receivedData){
        if(receivedData.contains("ERROR")) {
            System.err.println("OOPS, an error occurred, sorry but here is no data for you!");
            System.err.println(receivedData);
        } else{
            //Server responded with sth else like an error. Hopefully the weather data, just try to parse
            ArrayList<Float> list = new ArrayList<>();
            for (String value : receivedData.split(",")){
                try{
                    list.add(Float.parseFloat(value));
                } catch (Exception e){
                    System.err.println("ERROR: Seems the received data has invalid format");
                }
            }

            //Sth has failed parsing the data
            if(list.size() != 24){
                System.err.println("ERROR: Sth failed parsing the dates. Perhaps the received package is damaged or dataset on server is inconsistent.");
            } else{
                //Print out the results, that's all
                list = getMaxMinAndMean(list);
                for(int i = 0; i <= 23; i++){
                    System.out.println(i + "Uhr : " + list.get(i) + "C");
                }
                System.out.println("Min: " + list.get(24));
                System.out.println("Max: " + list.get(25));
                System.out.println("Mean: " + list.get(26));
            }

        }
    }

    /**
     * Calculate the min, max and mean temperature for some given temperatures and add them to the end
     * of the existing temps list
     * @param temps list with all temperature
     * @return temps list with min, max and mean temperature
     */
    private ArrayList<Float> getMaxMinAndMean(ArrayList<Float> temps){
        temps.add(Collections.min(temps));
        temps.add(Collections.max(temps));
        temps.add((float)temps.stream()
                .mapToDouble(value -> value)
                .average()
                .orElse(Double.NaN));
        return temps;
    }

}
