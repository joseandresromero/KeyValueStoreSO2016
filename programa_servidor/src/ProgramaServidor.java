package programa_servidor;

import java.io.*;
import java.net.*;

public class ProgramaServidor {
    public static void main (String[] args) {
        ServerSocket servidor = null;        
	KeyValueStore store = null;
	ThreadPool pool = null;
        int puerto = Integer.parseInt(args[0]);

        try {
            servidor = new ServerSocket(puerto);

            System.out.println("Servidor corriendo...");

	    //Instancio el key value store
	    store = new KeyValueStore();

	    pool = new ThreadPool(5);
/*
            while(true) {
                Socket socket_conectado = servidor.accept();

                System.out.println("Se conecto un cliente!! " + socket_conectado.toString());

                ClientThread cliente = new ClientThread(socket_conectado);
                cliente.start();
            }
*/
        } catch (IOException excepcion) {
            System.out.println(excepcion);
            System.exit(-1);
        } catch (Exception excepcion) {
            System.out.println("Ex >>>>>>>>>> " + excepcion);
        }

        while(true){
            ClientWorker w;
            try{
        //server.accept returns a client connection
              w = new ClientWorker(servidor.accept(), store);
              //Thread t = new Thread(w);
              //t.start();
	      pool.execute(w);
            } catch (IOException e) {
              System.out.println("Accept failed: 4444");
              System.exit(-1);
            } catch (Exception e) {
	    	e.printStackTrace();
	    }
          }
    }
}
