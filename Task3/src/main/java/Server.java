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
    ArrayList<WeatherClient> list;

    public Server() throws RemoteException {
        super();
        list = new ArrayList<WeatherClient>();
    }

    public List<MeasurePoint> getTemperatures(Date date) throws RemoteException {
        ArrayList<MeasurePoint> list = new ArrayList<MeasurePoint>();
        list.add(new MeasurePoint(new Date(), 10f));
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
            e.printStackTrace();
        }
    }

    private void start(){
        try{

            LocateRegistry.createRegistry(6713);
            //BInd to remote registry
            Registry registry = LocateRegistry.getRegistry(6713);
            registry.bind("Weather", this);

            System.err.println("Server ready");

            System.out.println("To update an entry, please enter sth in following format: YYYY-mm-DD,h,t");
            System.out.println("F.e. 1997-10-31, 23, -10.0");
            Scanner sc = new Scanner(System.in);
            processUserInput(sc.nextLine());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
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
