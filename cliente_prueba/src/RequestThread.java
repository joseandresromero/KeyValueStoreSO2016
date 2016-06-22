package cliente_prueba;

import java.io.*;
import java.net.*;

public class RequestThread extends Thread {
    private Socket cliente;
    private String lineResponse;
    private PrintWriter salida;
    private BufferedReader entrada;
    private String userInput;

    public RequestThread(Socket cliente, String userInput, String lineResponse) {
        this.cliente = cliente;
        this.userInput = userInput;
        this.lineResponse = lineResponse;

        try {

            this.salida = new PrintWriter(cliente.getOutputStream(), true);
            this.entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (Exception e) {
            System.out.println("Error: " + e );
        }
    }

    @Override
    public void run() {
        try {
            salida.println(userInput);

            this.lineResponse = entrada.readLine();
        } catch (Exception e) {
            System.out.println("Error: " + e );
        }
    }
    
    
}
