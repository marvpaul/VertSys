package Task1;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;

/**
 * Created by marvin on 12.10.17.
 */
public class Task1 {
	/**
	 * Simple method to compare system with server time via NTP
	 * @param args no arguments are required
	 */
	public static void main(String[] args) {
		//Initialize NTP-Client
		NTPUDPClient ntpClient = new NTPUDPClient();

		//Try to parse inet adress
		InetAddress server = null;
		try {
			server = InetAddress.getByName("0.europe.pool.ntp.org");
		} catch (UnknownHostException e) {
			System.out.println("Url not valid!");
		}
		try{
			//Fetch time data
			ntpClient.open();
			TimeInfo tI = ntpClient.getTime(server);
			tI.computeDetails();

			//Print out system and server time
			System.out.println("Server time (UTC): " + tI.getMessage().getReceiveTimeStamp().toUTCString());
			Calendar cal = Calendar.getInstance();
			System.out.println("System time (Local time): " + cal.getTime());

			//Just for testing purposes
			System.out.println(tI.getMessage().getRootDelay());
			System.out.println(tI.getMessage().getLeapIndicator());

		}
		//Exception handling
		catch (SocketException e) {
			System.out.println("Failed to establish connection :(");
		} catch (IOException e) {
			System.out.println("Request failed!");
		}
	}
}
