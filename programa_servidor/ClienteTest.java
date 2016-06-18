package programa_servidor;

import java.io.*;
import java.net.*;
import libreria_cliente.Comando;
import libreria_cliente.Respuesta;
 
public class ClienteTest {
    
    public static void main (String[] args) {
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        
        String ipServidor = args[0];
        
        try {

            cliente = new Socket(ipServidor, Integer.parseInt(args[1]));
            //asignamos este numero de puerto
            entrada = new ObjectInputStream(cliente.getInputStream());
            // será lo que enviaremos al servidor       
            salida = new ObjectOutputStream(cliente.getOutputStream());
            // será lo que nos devuelva el servidor     

        } catch (UnknownHostException excepcion) {
            System.err.println("El servidor no está levantado");
        } catch (Exception e) {
            System.err.println("Error: " + e );
        }
        
        try {
            Respuesta respuesta;
            salida.writeObject(new Comando("metodo get", "primero"));
            respuesta = (Respuesta) entrada.readObject();
            System.out.println("SERVIDOR DICE: " + respuesta.getRespuesta());
            salida.close();
            entrada.close();
            cliente.close();
        } catch (UnknownHostException excepcion) {
            System.err.println("No encuentro el servidor en la dirección" + ipServidor);
        } catch (IOException excepcion) {
            System.err.println("Error de entrada/salida");
        } catch (Exception e) {
            System.err.println("Error: " + e );
        }
    }
}
