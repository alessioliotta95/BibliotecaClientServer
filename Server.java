import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server{

    public static ArrayList<String> libri = new ArrayList<>();
    public static void ElencoLibri()
    {
        libri.add("LibroA");
        libri.add("LibroA");
        libri.add("LibroB");
        libri.add("LibroC");
        libri.add("LibroD");
        libri.add("LibroD");
        libri.add("LibroC");
        libri.add("LibroC");
    }

    public static boolean Conta (String libro, int n)
    {
        int count = 0;
        for (String s : libri) {
            if(s.equals(libro)) count ++;
        }
        if(n<=count) return true;
        else return false;
    }
    public static void main(String[] args) throws IOException {
        
        ElencoLibri();

        int PORT = 39939;
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("> In attesa di messaggi..");
        Socket clientSocket = serverSocket.accept();

        System.out.println("> Client connesso: " + clientSocket);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);

        while(true)
        {
            out.println("> Sintassi: <TitoloLibro,NumeroCopie>");

            String packet = in.readLine();
            if(packet.equals("end")) break;
            String [] packets = packet.split(",");
            out.println("> Riposta server -> " + Conta(packets[0],Integer.parseInt(packets[1])));
        }

        clientSocket.close();
        serverSocket.close();
        in.close();
        out.close();
    }
}