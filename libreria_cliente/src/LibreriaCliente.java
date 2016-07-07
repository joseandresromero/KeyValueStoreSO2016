package libreria_cliente;

import java.io.*;
import java.net.*;
import java.util.*;
 
public class LibreriaCliente {

    private DataInputStream entrada;
    private DataOutputStream salida;

    private Socket cliente = null;
    private PrintStream os = null;
    private DataInputStream is = null;

    private String ipServidor;
    private int puerto;

    public LibreriaCliente(String ipServidor, int puerto) {
        
	this.ipServidor = ipServidor;
        this.puerto = puerto;

        try {

            cliente = new Socket(ipServidor, puerto);
            os = new PrintStream(cliente.getOutputStream());
            is = new DataInputStream(cliente.getInputStream());	     

        } catch (UnknownHostException excepcion) {
            System.err.println("ERROR: El servidor no está levantado");
        } catch (IOException e) {
            System.err.println("ERROR: " + e );
        }
 
    }

    public void leerComando(){
	BufferedReader user = new BufferedReader( new InputStreamReader(System.in));
	if ( cliente != null && os != null && is != null) {
	    try{
	    	String responseLine = "Leyendo";

		while (responseLine != null) {
		    
		    String userInput = user.readLine();
		    
		    if ( userInput.equalsIgnoreCase("exit") ){
			break;
                    } else if ( validarEntrada(userInput) ){
		    
		    	os.println(userInput);
	  	    	responseLine = is.readLine();
			if (  userInput.equalsIgnoreCase("list")  )
                    	    listar(responseLine);
			else    
			    System.out.println("Server: " + responseLine);
                    
		    }else if ( userInput.equalsIgnoreCase("help") )
		    	ayuda();
		    else
			System.out.println("ERROR: Comando invalido.");	    
                }
		// clean up:
		// close the output stream
		// close the input stream
		// close the socket
        	os.close();
                is.close();
                cliente.close(); 
	    }catch(UnknownHostException e) {
                System.err.println("ERROR: Trying to connect to unknown host " + e);
            } catch (IOException e) {
                System.err.println("ERROR: IOException - " + e);
            }
	}

    }

    public boolean validarEntrada(String command) {
	
	String metodo = null;
	String parametro1 = null;
	String parametro2 = null;

	int indDelimitMet = command.indexOf(' ');

	if (indDelimitMet == -1) {
	    metodo = command;
	    //System.err.println(metodo);
	    switch(metodo){
		case "list": return true;
		    //break;
		case "exit": return false;
		    //break;
		case "help": return false;
		    //break;
	    }	   

	} else if (indDelimitMet != -1)  {
	    metodo = command.substring(0, indDelimitMet);
	    //System.err.println(metodo);
	    switch(metodo){
		case "get": return true;
		    //break;
		case "set": return true;
		    //break;
		case "del": return true;
		    //break;
	    }
	}
	return false;
    }

    
    public void ayuda(){
	System.err.println("get key \n* Retorna el valor asociado a dicha clave.");
	System.err.println("set key value \n* Almacena (en memoria) la clave, con el valor "+
				"asociado. El valor puede contener cualquier caracter, incluso "+
				"caracteres especiales, tabs y espaciones en blanco.");
	System.err.println("del key \n* Elimina la clave, con su valor asociado.");
	System.err.println("list \n* Retorna la lista de todas las claves almacenadas."+
				" NO retorna los valores asociados a dichas claves.");
	System.err.println("exit \n* Termina la conexión con el servidor y posteriormente, "+
				"termina ejecución del programa cliente.");
	System.err.println("help \n* Muestra la lista de los comandos soportados, incluyendo "+
				"una breve explicación de los mismos.");
    }

    
    public void listar( String lista ){
    	
	StringTokenizer stTexto = new StringTokenizer(lista);
	
	while( stTexto.hasMoreTokens() ){
            System.out.println("Server: " + stTexto.nextToken().trim());
    	}
    }
}
/*
    public void ejecutarComando(Comando comando) {

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

        //return null;
    }
*/    

