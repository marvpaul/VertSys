import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client implements WeatherClient, Serializable{
    private boolean exit;
    private WeatherClient cl;
    public void updateTemperature(MeasurePoint point) throws RemoteException {
        System.out.println("Received an update: ");
        System.out.println(point.get_temperature());
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start(){
        exit = false;
        WeatherServer stub = registerByWeatherServer();

        processInput(stub);
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

    private void processInput(WeatherServer stub){
        while(!exit){
            List<MeasurePoint> response = null;
            try {
                response = stub.getTemperatures(new Date());
            } catch (RemoteException e) {
                System.err.println("Cant get response from weather server :/");
            }
            System.out.println(response);
            System.out.println("Please enter a new date:");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("exit")){
                try {
                    stub.deregister(cl);
                } catch (RemoteException e) {
                    System.err.println("Failed to unsubscribe from weather server");
                }
                exit = true;
            }
        }

    }
}
