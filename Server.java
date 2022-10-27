import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server{

    private static final int port = 3322;

    private ArrayList<Thread> threads = new ArrayList<Thread>();
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
            System.out.println("conexao aceita");
            System.out.println(i);
            out = new PrintWriter(cliSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));
            service();
            i++;
            System.out.println(i);
        }
    }
    
    public void service(){
        new Thread(){
            public void run(){
                try{    
                    String cid = in.readLine();
                    System.out.println("Prestando servico pro cliente "+cid);
                    String msg = in.readLine();
                    System.out.println("Cliente "+cid+" envia: "+msg);
                    out.println("Recebi o seu '"+msg+"'; vc sera servido pela thread "+getId());
                    threads.add(this);
                    Iterator<Thread> i = threads.iterator();
                    while(i.hasNext()){
                        Thread currThread = i.next();
                        final String threadStatus = currThread.isAlive() ? "Ativa" : "Inativa";
                        System.out.println("Thread "+currThread.getId()+"\tStatus: "+threadStatus);
                    }
                    synchronized(this){
                        try {
                            this.wait();
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }
                }catch(IOException e){}
            }
        }.start();
    }

    public void stop() throws IOException{
        in.close();
        out.close();
        cliSocket.close();
        srvSocket.close();
    }

    public static void main(String[] args) throws IOException{

        Server s = new Server();
        s.start();
        
    }
}