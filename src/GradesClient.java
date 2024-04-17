import java.io.*;
import java.net.Socket;

public class GradesClient {
    public static void main(String[] args) {
        try {
            String host = "127.0.0.1";
            int port = 27000;

            Socket socket = new Socket(host, port);
            System.out.println("Подключение к серверу...");

            ScoresPerMonth scoresPerMonth = new ScoresPerMonth();
            ScoresPerMonth dataToSend = scoresPerMonth.generateData();

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeObject(dataToSend);
            out.flush();
            System.out.println("Данные отправлены на сервер");

            String processedData = in.readLine();
            System.out.println("Полученные обработанные данные от сервера: " + processedData);

            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
