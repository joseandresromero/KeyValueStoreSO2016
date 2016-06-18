package programa_servidor;

import java.io.*;
import java.net.*;

public class ProgramaServidor {
    public static void main (String[] args) {
        System.out.println("Servidor corriendo...");
        ServerSocket servidor = null;

        String linea_recibida;
        DataInputStream entrada;
        PrintStream salida;
        Socket socket_conectado = null;

        int puerto = Integer.parseInt(args[0]);

        try {
            servidor = new ServerSocket(puerto);
        } catch (IOException excepcion) {
            System.out.println(excepcion);
        }

        try {
            socket_conectado = servidor.accept();
            
            ClientThread cliente = new ClientThread(socket_conectado);
            cliente.start()
        } catch (IOException excepcion) {
            System.out.println(excepcion);
        }
    }
}
