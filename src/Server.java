import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8001);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        while(true){
            System.out.println("Waiting for client");
            Socket socket = serverSocket.accept();
            executorService.submit(new ServiceRequestTask(socket));
        }
    }

}
