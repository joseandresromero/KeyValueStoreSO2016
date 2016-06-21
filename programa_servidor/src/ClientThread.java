package programa_servidor;

import java.io.*;
import java.net.*;
import libreria_cliente.Comando;
import libreria_cliente.Respuesta;

public class ClientThread extends Thread {
    
    private Socket cliente;
    private InputStream is;
    private OutputStream os;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private String ipServidor;

    public ClientThread(Socket cliente) {
        this.cliente = cliente;
        this.ipServidor = ipServidor;
        System.out.println("Constructor");
        try {
            System.out.println("Dentro del try");
            System.out.println("input stream: " + cliente.getInputStream().toString());

            is = cliente.getInputStream();
            entrada = new ObjectInputStream(is);

            System.out.println("Despues de entrada");

            os = cliente.getOutputStream();
            salida = new ObjectOutputStream(os);

System.out.println("Se creo el thread!!");

        } catch (UnknownHostException excepcion) {
            System.out.println("El servidor no esta levantado");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Comando comando = (Comando) entrada.readObject();
                System.out.println("Comando recibido del cliente: " + comando.getMetodo());
                salida.writeObject(new Respuesta(true, "Exitooooo"));
                salida.close();
                os.close();
                entrada.close();
                is.close();
                cliente.close();
            } catch (Exception excepcion) {
                System.out.println(excepcion);
            }
        }            
    }
    
    
}
