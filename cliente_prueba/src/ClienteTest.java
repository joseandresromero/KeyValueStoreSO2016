package cliente_prueba;

import java.io.*;
import java.net.*;
import libreria_cliente.Comando;
import libreria_cliente.Respuesta;
 
public class ClienteTest {
    
    public static void main (String[] args) {
        Socket cliente = null;
/*
        DataInputStream entrada = null;
        DataOutputStream salida = null;
*/
        PrintWriter salida = null;
        BufferedReader entrada = null;
        String ipServidor = args[0];
        
        try {

            cliente = new Socket(ipServidor, Integer.parseInt(args[1]));
 /*          System.out.println("Socket creado");

            entrada = new DataInputStream(cliente.getInputStream());

            System.out.println("despues de la entrada");

            salida = new DataOutputStream(cliente.getOutputStream());*/

            salida = new PrintWriter(cliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        } catch (UnknownHostException excepcion) {
            System.out.println("El servidor no está levantado");
        } catch (Exception e) {
            System.out.println("Error: " + e );
        }
        
        try {
            
            salida.println("Mi prueba");

            String line = entrada.readLine();
            System.out.println("Text received: " + line);
     /*
            Respuesta respuesta;
            salida.writeObject(new Comando("metodo get", "primero", null));
            respuesta = (Respuesta) entrada.readObject();
            System.out.println("SERVIDOR DICE: " + respuesta.getRespuesta());
            salida.close();
            os.close();
            entrada.close();
            is.close();
            cliente.close();*/
        } catch (UnknownHostException excepcion) {
            System.out.println("No encuentro el servidor en la dirección" + ipServidor);
        } catch (IOException excepcion) {
            System.out.println("Error de entrada/salida");
        } catch (Exception e) {
            System.out.println("Error: " + e );
        }
    }
}
