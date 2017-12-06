import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySocketServer implements Runnable{
    private ArrayList<MeasurePoint> data;
    private static final Logger LOGGER = Logger.getLogger( MySocketServer.class.getName() );
    private static volatile int requests = 0;

    private Socket clientSock;
    public MySocketServer(Socket sock, ArrayList<MeasurePoint> data) {
        LOGGER.setLevel(Level.INFO);
        this.data = data;
        this.clientSock = sock;
    }

    public void run() {
        MySocketServer.requests++;
        while (requests  < 1) { }
        long threadId = Thread.currentThread().getId();
        LOGGER.info("Thread # " + threadId + " is doing this task" + "\nIP Address: " + clientSock.getInetAddress()
                + "\nPort: " +clientSock.getPort());
        processReq();
    }

    /**
     * Process a request from client and sends back some temperature data
     * in case the client had send a valid date
     */
    public void processReq(){
        //Using auto cloable for reader ;)
        try( BufferedReader reader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()))){
            String mess = reader.readLine();
            String returnMess = getWeatherDataForCertainDate(mess);

            PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
            writer.println(returnMess);
            writer.flush();

            //Close sockets
            clientSock.close();
        } catch (IOException e) {
            System.err.println("ERROR: Problem while opening / close a stream");
        }
    }


    /**
     * This reads some values out of a csv and returns as ArrayList
     * @param key a date, format: "YYYY-MM-dd"
     * @return A list of temperatures, sorted from 0 - 23, seperated by ,
     */
    public String getWeatherDataForCertainDate(String key){
        try {
            String temperatureList = "";
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for(MeasurePoint measurePoint : data){
                String measurePointDay = df.format(measurePoint._timeStamp);
                if(measurePointDay.equals(key)){
                    temperatureList += measurePoint.get_temperature() + ", ";
                }
            }
            if(temperatureList != ""){
                //Just remove the last seperator and return the list
                return temperatureList.substring(0, temperatureList.length() - 1);
            } else{
                return "ERROR: Failed to loading data from server!";
            }
        } catch (Exception e) {
            return "ERROR: Failed to loading data from server!";
        }
    }
}
