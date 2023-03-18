import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Host {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9099;
        try {
            String str = "Something";
            Socket socket = new Socket(host, port);
            OutputStreamWriter osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            osw.write(str, 0, str.length());
        }catch (IOException ex){
            ex.getMessage();
        }


    }
}
