package programa_servidor;

import java.io.*;
import java.net.*;
import libreria_cliente.Comando;
import libreria_cliente.Respuesta;

public class ClientWorker implements Runnable {
  private Socket cliente;
  private KeyValueStore store;

//Constructores

  ClientWorker(KeyValueStore store) {
    this.store = store;
    this.cliente = null;
  }

  ClientWorker(Socket cliente, KeyValueStore store) {
    this.cliente = cliente;
    this.store = store;
  }

  public void setCliente(Socket cliente) {
    this.cliente = cliente;
  }

  public void run(){
    String line;
    DataInputStream is;
    PrintStream os;

//    while(true){
      try{
	is = new DataInputStream(cliente.getInputStream());
        os = new PrintStream(cliente.getOutputStream());
	while (true) {
//      is = new DataInputStream(cliente.getInputStream());
//        os = new PrintStream(cliente.getOutputStream());
	        line = is.readLine();

//		if (line != null) {
	        	System.out.println("Linea recibida del cliente: " + line);
			String response = CommandExecuter.executeCommand(line, store);
//			String response = CommandExecuter.test(line);
			//Send data back to client
		        os.println(response);
//		}
	}
       } catch (IOException e) {
        System.out.println("Read failed");
        System.exit(-1);
       } catch (Exception e) {
        System.out.println("Execp >>>>>> " + e);
        System.exit(-1);
       }
//    }
  }
}
