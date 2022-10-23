import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server{

    private static final int port = 3322;

    private ServerSocket srvSocket;
    private Socket cliSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start() throws IOException{
        int i = 0;
        srvSocket = new ServerSocket(port);
        System.out.println("Serv iniciado na porta: "+port);
        while(i < 4){
            cliSocket = srvSocket.accept();
            out = new PrintWriter(cliSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));
            service(in.readLine());
            i++;
        }
    }
    
    public void service(String s){
        new Thread(){
            public void run(){
                System.out.println("Servico prestado pela thread "+this.getId());
                System.out.println("Cliente envia: "+s);
                out.println("Recebi o seu "+s);
            }
        }.start();
    }

    public void stop() throws IOException{
        in.close();
        out.close();
        cliSocket.close();
        srvSocket.close();
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException{

        Server s = new Server();
        s.start();
        s.stop();
    }
}