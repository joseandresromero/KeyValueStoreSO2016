package programa_servidor;

import java.io.*;
import java.net.*;
import libreria_cliente.Comando;
import libreria_cliente.Respuesta;

class ClientWorker implements Runnable {
  private Socket cliente;

//Constructor
  ClientWorker(Socket cliente) {
    this.cliente = cliente;
  }

  public void run(){
    String line;
    //BufferedReader in = null;
    //PrintWriter out = null;
/*
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
*/
    BufferedReader in = null;
    PrintWriter out = null;

    try{
   /*
        in = new ObjectInputStream(cliente.getInputStream());
        out= new ObjectOutputStream(cliente.getOutputStream());
*/

      in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
      out = new PrintWriter(cliente.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("in or out failed");
      System.exit(-1);
    }

//    while(true){
      try{
        line = in.readLine();
        System.out.println("Linea recibida del cliente: " + line);
//Send data back to client
        out.println(line);
/*
        	Comando comando = (Comando) in.readObject();
            System.out.println("Comando recibido del cliente: " + comando.getMetodo());
            out.writeObject(new Respuesta(true, "Exitooooo"));
*/
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
