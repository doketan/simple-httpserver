import java.io.InputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpUtils {
    public static String getRequest(Socket socket) throws Exception {
        StringBuilder requestString = new StringBuilder();
        byte[] requestByte = new byte[50000];
        InputStream in = socket.getInputStream();
        int n = in.read(requestByte);

        String request1 = null;
        if(n!=1){
            request1 = new String(requestByte,0,n);
            requestString.append(request1);
        }
        if(request1 != null && request1.contains("multipart/form-data")){
            n = in.read(requestByte);
            if(n!=1){
                requestString.append(new String(requestByte, 0,n));
            }
        }
        return requestString.toString();
    }

    public static String getRequestUri(String request){
        StringTokenizer stk = new StringTokenizer(request);
        String method = stk.hasMoreTokens() ? stk.nextToken() : null;
        String uri = stk.hasMoreTokens() ? stk.nextToken():null;
        return  uri;

    }
}
