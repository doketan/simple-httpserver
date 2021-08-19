
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler {
    ResourceLoader resourceLoader = new ResourceLoader();
    public void handleRequest(Socket socket){
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            String request = HttpUtils.getRequest(socket);
            String uri = HttpUtils.getRequestUri(request);
            System.out.println(" Request for :"+uri);

            InputStream in = resourceLoader.getResource(uri);

            if(in == null){
                System.out.println("resource not found");
                HttpResponseUtils.sendResourceNotFound(outputStream);
                return;
            }

            System.out.println("sending response ");
            HttpResponseUtils.sendSuccessResponse(in,outputStream);
        } catch (Exception e){
            e.printStackTrace();
            if (outputStream != null) {
                try {
                    System.out.println("Sending internal error ");
                    HttpResponseUtils.sendInternalError(outputStream);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        } finally {
            try {
                socket.close();
            } catch (Exception e){

            }
        }
    }

}
