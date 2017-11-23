import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler {
    int port;
    ServerSocket sock;
    Socket clientSock;
    boolean exit;

    /**
     * Constructor for server
     * Initialize a ServerSocket and bind to a given port.
     * Wait for connection, try to find some data which match the request and send them back.
     * Otherwise the server response with an error message
     * @param port the port the serversocket is bind to
     */
    public ServerHandler(int port) {
        exit = false;

        this.port = port;
        try {
            sock = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started");

        try {
            while(!exit){

                MySocketServer s = new MySocketServer(sock.accept());
                Thread t = new Thread(s);
                t.start();
            }
        } catch (IOException e) {
            System.err.println("ERROR: Sth failed while creating server socket :( ");
        }
    }
}
