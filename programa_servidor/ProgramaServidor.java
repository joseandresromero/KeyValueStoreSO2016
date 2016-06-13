import java.io.*;
import java.net.*;

public class ProgramaServidor {
    public static void main (String[] args) {
        System.out.println("Servidor corriendo...");
        ServerSocket mi_servicio = null;

        String linea_recibida;
        DataInputStream entrada;
        PrintStream salida;
        Socket socket_conectado = null;

        int puerto = Integer.parseInt(args[0]);

        try {
            mi_servicio = new ServerSocket(puerto);
        } catch (IOException excepcion) {
            System.out.println(excepcion);
        }

        try {
            socket_conectado = mi_servicio.accept();
            entrada = new DataInputStream(socket_conectado.getInputStream());
            salida = new PrintStream(socket_conectado.getOutputStream());
            linea_recibida = entrada.readLine();
            System.out.println("Comando recibido del cliente: " + linea_recibida);
            salida.println("Te reenvio lo que he recibido:" + linea_recibida );
            salida.close();
            entrada.close();
            socket_conectado.close();
        } catch (IOException excepcion) {
            System.out.println(excepcion);
        }
    }
}
