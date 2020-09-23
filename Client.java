import java.io.*;
import java.net.*;

//Client Biblioteca

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        
        int PORT = 39939;
        InetAddress serverAddr = InetAddress.getByName("127.0.0.1");

        Socket socket = new Socket(serverAddr,PORT);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

       while(true)
       {
            System.out.println(in.readLine());
            String s = stdin.readLine();
            out.println(s);
            if(s.equals("end")) break;
            System.out.println(in.readLine());
        }

        socket.close();
        in.close();
        stdin.close();
        out.close();
    }
}