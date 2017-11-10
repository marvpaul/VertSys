import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Server extends UnicastRemoteObject implements WeatherServer{
    private ArrayList<WeatherClient> list;
    private boolean exit;
    private ArrayList<MeasurePoint> data;

    public Server() throws RemoteException {
        super();

        exit = false;
        data = new WeatherCSVReader().getWeatherData();
        list = new ArrayList<WeatherClient>();
    }

    public List<MeasurePoint> getTemperatures(Date date) throws RemoteException {
        System.out.println("Get request!");
        ArrayList<MeasurePoint> list = new ArrayList<MeasurePoint>();
        for(MeasurePoint measurePoint : data){
            if(date.getDate() == measurePoint._timeStamp.getDate()){
               list.add(measurePoint);
            }
        }
        System.out.println("Answer request!");
        return list;
    }

    public boolean register(WeatherClient client) throws RemoteException {
        if(list.add(client)){
            System.out.println("Client joined");
            return true;
        }
        return false;
    }

    public boolean deregister(WeatherClient client) throws RemoteException {
        if(list.remove(client)){
            System.out.println("Client left");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (RemoteException e) {
            System.err.println("ERROR: Sth is going wrong while creating the server via RMI");
        }
    }

    private void start(){
        try{
            //Bind to remote registry
            LocateRegistry.createRegistry(6713);
            Registry registry = LocateRegistry.getRegistry(6713);
            registry.bind("Weather", this);

            System.err.println("Server ready");

            loopAskingForInput();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    private void loopAskingForInput(){
        while(!exit){
            System.out.println("To update an entry, please enter sth in following format: YYYY-mm-DD,h,t");
            System.out.println("F.e. 1997-10-31, 23, -10.0");
            Scanner sc = new Scanner(System.in);
            processUserInput(sc.nextLine());
        }
    }

    private void processUserInput(String input){
        String[] seperated = input.split(",");
        if(seperated.length == 3){
            MeasurePoint point = new MeasurePoint(new Date(), 100f);
            try {
                this.updateClients(point);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else{
            System.err.println("Invalid input");
        }
    }

    private void updateClients(MeasurePoint point) throws RemoteException {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).updateTemperature(point);
        }
    }
}
