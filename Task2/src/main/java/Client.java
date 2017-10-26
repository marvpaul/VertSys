import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread{
    String ip, message;
    int port;
    private Socket sock;

    public Client(String ip, int port, String message) {
        this.ip = ip;
        this.port = port;
        this.message = message;
    }

    public void run(){
        try {
            sock = new Socket(ip, port);

            try(PrintWriter serverWriter = new PrintWriter(sock.getOutputStream())){
                serverWriter.println(message);
                serverWriter.flush();
                receiveMess();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiveMess(){
        Scanner in  = null;
        try {
            in = new Scanner( sock.getInputStream() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        String receivedData =in.nextLine();
        processReceivedData(receivedData);


        closeConn();
    }

    public void processReceivedData(String receivedData){
        if(receivedData.contains("ERROR")) {
            System.out.println("OOPS, an error occured!");
            System.out.println(receivedData);
        } else{
            ArrayList<String> list = new ArrayList<String>();
            for (String value : receivedData.split(",")){
                list.add(value);
            }

            //TODO: Calculate some values like mean temp and stuff like that
            //Sth has failed parsing the data
            if(list.size() != 24){
                System.out.println("Sth failed parsing the dates");
            } else{
                for(int i = 0; i <= 23; i++){
                    System.out.println(i + "Uhr : " + list.get(i) + "C");
                }
            }

        }
    }

    public void closeConn(){
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
