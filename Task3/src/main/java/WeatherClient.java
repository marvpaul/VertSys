import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WeatherClient extends Remote {
    void updateTemperature(MeasurePoint point) throws RemoteException;
}
