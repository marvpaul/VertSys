import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client implements WeatherClient{
    private WeatherClient cl;
    private WeatherServer stub;

    public void updateTemperature(MeasurePoint point) throws RemoteException {
        System.out.println("Received an update: ");
        List<MeasurePoint> points = stub.getTemperatures(point._timeStamp);
        printReceivedData(points, point);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start(){
        stub = registerByWeatherServer();

        processInput();
    }

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

    private void processInput(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Please enter a new date:");
            String input = sc.nextLine();

            handleExit(input);

            List<MeasurePoint> response = sendWeatherDataRequest(input);

            printReceivedData(response, null);

        }
    }

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

    private List<MeasurePoint> sendWeatherDataRequest(String inputDate){
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return stub.getTemperatures(df.parse(inputDate));
        } catch (RemoteException e) {
            System.err.println("Cant get response from weather server :/");
        } catch (ParseException e) {
            System.err.println("ERROR: Client cannot parse date. Please enter in following format: yyyy-MM-dd");
        }
        //Just in case :)
        return null;
    }

    private void printReceivedData(List<MeasurePoint> list, MeasurePoint update){
        if(list.size() == 0){
            System.err.println("ERROR: No data received. Perhaps you have entered a invalid date. Date has to be in following format: yyyy-MM-dd");
        } else{
            for (MeasurePoint mp : list){
                if(update != null && mp._timeStamp.compareTo(update._timeStamp) == 0){
                    System.out.println("***" + update + "***");
                }
                System.out.println(mp);
            }
        }
    }
}
