package programa_servidor;

import java.io.*;
import java.net.*;
import libreria_cliente.Comando;
import libreria_cliente.Respuesta;

public class ClientThread extends Thread {
    
    private Socket cliente;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private String ipServidor;

    public ClientThread(Socket cliente) {
        this.cliente = cliente;
        this.ipServidor = ipServidor;
        
        try {
            
            entrada = new ObjectInputStream(cliente.getInputStream());
            salida = new ObjectOutputStream(cliente.getOutputStream());

        } catch (UnknownHostException excepcion) {
            System.err.println("El servidor no esta levantado");
        } catch (Exception e) {
            System.err.println("Error: " + e );
        }
    }

    @Override
    public void run() {
        try {
            Comando comando = (Comando) entrada.readObject();
            System.out.println("Comando recibido del cliente: " + comando.getMetodo());
            salida.writeObject(new Respuesta(true, "Exitooooo"));
            salida.close();
            entrada.close();
            cliente.close();
        } catch (Exception excepcion) {
            System.out.println(excepcion);
        }
    }
    
    
}
