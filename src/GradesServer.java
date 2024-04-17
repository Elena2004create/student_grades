import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GradesServer {
    public static void main(String[] args) {
        try {
            int port = 27000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен. Ожидание подключений...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("К серверу подключился " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
