import java.io.*;
import java.net.Socket;
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
        System.out.println(in.nextLine());
        closeConn();
    }

    public void closeConn(){
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
