import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    /*
    This Chat Server works with the Multiple Clients implemented in the class NetClient.
    This is lasting Server until "bye" will be typed on the Client Side.
     */

    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    ChatServer() throws IOException {
        // создаем серверный сокет на порту 1234
        serverSocket = new ServerSocket(1234);
    }

    void sendAll(String message){
        for (Client client: clients){
            client.receive(message);
        }
    }

    public void run() {
        while(true) {
            System.out.println("Waiting...");

            try {// ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                // создаем клиента на своей стороне
                clients.add(new Client(socket, this));

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}