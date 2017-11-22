import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Client implements WeatherClient{

    private WeatherClient cl;
    private WeatherServer stub;
    private List<MeasurePoint> points;

    /**
     * This method is called from the server via RMI each time a Measurepoint was updated
     * In this case the client asks for the hole data on this day and print the data via printReceiveUpdate()
     * @param point the recently updated MeasurePOint
     * @throws RemoteException in case sth during server client communication failed
     */
    public void updateTemperature(MeasurePoint point) throws RemoteException {
        System.out.println("Received an update: ");
        points = stub.getTemperatures(point._timeStamp);
        printReceivedData(point);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }


    public void start(){
        stub = registerByWeatherServer();

        processInput();
    }

    /**
     * Registers the client at the WeatherServer
     * @return the remove WeatherServer object
     */
    private WeatherServer registerByWeatherServer(){
        WeatherServer stub = null;
        try{
            cl = (WeatherClient) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 6713);
            stub = (WeatherServer) registry.lookup("Weather");
            stub.register(cl);
        } catch (Exception e){
            System.err.println("ERROR: Cant register by weather server");
        }
        return stub;
    }

    /**
     * Process the UserInput
     * The user can enter a date and the client will ask the server for data at this date
     */
    private void processInput(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Please enter a new date:");
            String input = sc.nextLine();

            handleExit(input);

            processRequest(input);
        }
    }

    /**
     * Make a request and asking for WeatherData at a given date
     * @param input the date as a string
     */
    private void processRequest(String input){
        points = sendWeatherDataRequest(input);
        if(points != null){
            printReceivedData(null);
        }
    }

    /**
     * In case the user enters exit, the client tries to shutdown
     * @param input
     */
    private void handleExit(String input){
        if(input.equals("exit")){
            try {
                stub.deregister(cl);
            } catch (RemoteException e) {
                System.err.println("Failed to unsubscribe from weather server");
            }
            System.out.println("Client is shutting down ...");
            System.exit(0);
        }
    }

    /**
     * The client parse the inputDate and send a request with a date to the server
     * @param inputDate the date as a string
     * @return in best case a list of MeasurePoints. In case the server sends null, this method also just returns null
     */
    private List<MeasurePoint> sendWeatherDataRequest(String inputDate){
        Date date = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(inputDate);
        }  catch (ParseException e) {
            System.err.println("ERROR: Client cannot parse date. Please enter in following format: yyyy-MM-dd");
        }
        if(date != null){
            try{
                return stub.getTemperatures(date);
            } catch (RemoteException e) {
                System.err.println("ERROR: Cant get response from weather server :/");
            }
        }
        //Just in case :)
        return null;
    }

    /**
     * The print method for MeasurePoints
     * @param update a single MeasurePoint which was updated by the server. In case this param is not null,
     *               the MeasurePoint will be highlighted in console
     */
    private void printReceivedData(MeasurePoint update){
        if(points == null  || points.size() == 0){
            System.err.println("ERROR: No data received. Perhaps there are no existing measurepoints for your requested date?");
        } else{
            for (MeasurePoint mp : points){
                if(update != null && mp._timeStamp.compareTo(update._timeStamp) == 0){
                    System.out.println("***" + update + "***");
                }
                System.out.println(mp);

            }
            ArrayList<Float> floatList = getFloatList();
            System.out.println("Min: " + Collections.min(floatList));
            System.out.println("Max: " + Collections.max(floatList));
            System.out.println("Mean: " + (float)floatList.stream()
                    .mapToDouble(value -> value)
                    .average()
                    .orElse(Double.NaN));
        }
    }

    /**
     * Make a float arraylist from the MeasurePoint arraylist
     * @return float list with all temperatures
     */
    private ArrayList<Float> getFloatList(){
        ArrayList<Float> temps = new ArrayList<Float>();
        for(int i = 0; i < points.size(); i++){
            temps.add(points.get(i).get_temperature());
        }
        return temps;
    }
}
