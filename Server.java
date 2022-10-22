import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Scanner;
import java.io.IOException;

public class Server{

    public static void service(String s){

        new Thread(){
            public void run(){

                System.out.println("Servico prestado pela thread "+this.getId());
                System.out.println(s);

            }
        }.start();

    }

    public static void main(String[] args) {
        
        try {

            ServerSocket server = new ServerSocket(3322);
            System.out.println("\nServidor iniciado na porta 3322");

            Socket cliente = server.accept();
            System.out.println("\nCliente conectado do IP "+cliente.getInetAddress().getHostAddress());

            Scanner entrada = new Scanner(cliente.getInputStream());
            while(entrada.hasNextLine()){
                Server.service(entrada.nextLine());
            }

            entrada.close();
            server.close();

        } catch (IOException e) {
            
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);

        }

    }

}