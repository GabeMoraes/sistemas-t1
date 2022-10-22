import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {

        String entrada;
        boolean repeat = true;
        char option;

        try {
            
            Socket cliente = new Socket("127.0.0.1",3322);
            // setta para que todo println que seja passado para 'saida' seja passado como output do cliente
            PrintStream saida = new PrintStream(cliente.getOutputStream());
            // pega string do teclado, salva na var 'entrada'
            Scanner teclado = new Scanner(System.in);
            Scanner keyboard = new Scanner(System.in);

            //loop para ficar entrando com dados
            while(repeat){

                System.out.println("Enviar msg ao servidor? (S/N)");
                option = teclado.next().charAt(0);
                // le opcao em 'option'
                switch(option){
                    
                    case 'S':
                    case 's':
                    System.out.println("Digite a msg: ");
                    // le a msg do teclado e guarda em 'entrada'
                    entrada = keyboard.nextLine();
                    // passa a string contida em 'entrada' para a stream 'saida' e também pro output de cliente
                    saida.println(entrada);
                    break;

                    case 'N':
                    case 'n':
                    repeat = false;
                    break;

                    default:
                    System.out.println("Opcao invalida");
                    break;

                }

            }

            teclado.close();
            keyboard.close();
            cliente.close();

        } catch (Exception e) {
            
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);

        }

    }

}
