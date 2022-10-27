import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class Client {

    private static final String hostIP = "127.0.0.1";
    private static final int port = 3322;
    private static int cliCount = 0;

    private String cid;
    private Socket cliSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String srvResp;
    
    public void start() throws IOException{

        cliSocket = new Socket(hostIP,port);
        System.out.println("socket criado");
        cliCount++;
        out = new PrintWriter(cliSocket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));

        cid = Integer.toString(cliCount);
        out.println(cid);
    }

    public String talk(String msg) throws IOException{
        out.println(msg);
        String resp = null;
        try{
            resp = in.readLine();
        } catch(SocketException e){
            e.printStackTrace();
        }
        return resp;
    }

    public void stop() throws IOException{
        in.close();
        out.close();
        cliSocket.close();
    }
    
    public static void main(String[] args) throws IOException{

        Client c = new Client();
        //Scanner kbEntry = new Scanner(System.in);
        c.start();
        //System.out.println("Digite uma msg: ");
        c.srvResp = c.talk("Pode me emprestar um servico?");
        System.out.println(c.srvResp);
        c.stop();
        //kbEntry.close();
    }
}