import java.io.*;
import java.net.Socket;
import java.util.*;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            List<Integer> grades = new ArrayList<>();
            Object inputData = in.readObject();
            ScoresPerMonth scores = (ScoresPerMonth) inputData;
            Collection<List<Integer>> values = scores.getScoresPerMonth();

            for (List<Integer> list : values) {
                for (Integer value : list) {
                    grades.add(value);
                }
            }

            int maxGrade = grades.stream().max(Integer::compareTo).orElse(0);
            int minGrade = grades.stream().min(Integer::compareTo).orElse(0);
            double averageGrade = grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);

            Thread.sleep(3000);
            System.out.println("Данные обработаны и результат отправлен клиенту");

            String processedData = "Максимальная оценка: " + maxGrade +
                    ", Минимальная оценка: " + minGrade +
                    ", Средняя оценка: " + averageGrade;

            out.write(processedData);
            out.newLine();
            out.flush();
            clientSocket.close();
        }
        catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
