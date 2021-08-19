import java.net.Socket;

public class ServiceRequestTask implements Runnable{

    Socket socket;

    RequestHandler requestHandler = new RequestHandler();

    public ServiceRequestTask(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        requestHandler.handleRequest(socket);
    }
}
