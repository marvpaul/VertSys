import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String measurePointDay = df.format(measurePoint._timeStamp);
            if(df.format(date).equals(measurePointDay)){
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
        MeasurePoint mp = parseMeasurePointFromUserInput(input);
        if(saveUpdate(mp)){
            try {
                this.updateClients(mp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else{
            System.err.println("ERROR: The entered date is not inside the weather servers database. Please enter an existing date");
        }
    }

    private boolean saveUpdate(MeasurePoint mp){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i)._timeStamp.compareTo(mp._timeStamp) == 0){
                data.set(i, mp);
                return true;
            }
        }
        return false;
    }

    private MeasurePoint parseMeasurePointFromUserInput(String input){
        try {
            String[] seperated = input.split(",");
            if (seperated.length == 3) {

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date measurePointDay = df.parse(seperated[0]);

                int hour = Integer.parseInt(seperated[1]);

                Date measureTime = new Date();

                measureTime.setTime(measurePointDay.getTime() + hour * 60 * 60 * 1000);

                float temp = Float.parseFloat(seperated[2]);

                MeasurePoint point = new MeasurePoint(measureTime, temp);

                return point;

            }
        } catch (Exception e) {
            System.err.println("ERROR: Failed while parsing ...");
        }

        return null;
    }

    private void updateClients(MeasurePoint point) throws RemoteException {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).updateTemperature(point);
        }
    }
}
