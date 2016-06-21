package libreria_cliente;

import java.io.*;
import java.net.*;
 
public class LibreriaCliente {

    private Socket cliente;
    private DataInputStream entrada;
    private DataOutputStream salida;

    private String ipServidor;
    private int puerto;

    public LibreriaCliente(String ipServidor, int puerto) {
        this.ipServidor = ipServidor;
        this.puerto = puerto;

        try {

            cliente = new Socket(ipServidor, puerto);
            //asignamos este numero de puerto
            entrada = new DataInputStream(cliente.getInputStream());
            // será lo que enviaremos al servidor       
            salida = new DataOutputStream(cliente.getOutputStream());
            // será lo que nos devuelva el servidor     

        } catch (UnknownHostException excepcion) {
            System.err.println("El servidor no está levantado");
        } catch (Exception e) {
            System.err.println("Error: " + e );
        }
    }

    public String ejecutarComando(Comando comando) {
        try {
            String linea_recibida;
            salida.writeBytes("mi comando de prueba\n");
            linea_recibida = entrada.readLine();
            System.out.println("SERVIDOR DICE: " + linea_recibida);
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

        return null;
    }
}
