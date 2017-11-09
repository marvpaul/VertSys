import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface WeatherServer extends Remote{

    List<MeasurePoint> getTemperatures(Date date) throws RemoteException;

    boolean register(WeatherClient client) throws RemoteException;

    boolean deregister(WeatherClient client) throws RemoteException;




}
