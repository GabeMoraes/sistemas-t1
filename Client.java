import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

@SuppressWarnings("unused")
public class Client {

    private static final String hostIP = "127.0.0.1";
    private static final int port = 3322;

    Socket cliSocket;
    PrintStream out;
    BufferedReader in;
    
    public void start(){
         //todo

    }
    
    public static void main(String[] args) {

        System.out.println("hello world");
        /*try {
            //todo

        } catch (Exception e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }*/
    }
}
