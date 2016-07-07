package libreria_cliente;

import java.io.*;
import java.net.*;
import java.util.*;
 
public class LibreriaCliente {

    private Socket cliente = null;

    private String ipServidor;
    private PrintStream os = null;
    private DataInputStream is = null;
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
			System.out.println("cliente offLine");
			break;
		    } else if ( userInput.equalsIgnoreCase("help") )
		    	ayuda();
		    else if ( validarEntrada(userInput) ){
		    
		    	os.println(userInput);
	  	    	responseLine = is.readLine();
			
			if (  userInput.equalsIgnoreCase("list")  )
                    	    listar(responseLine);
			else    
			    System.out.println("Server: " + responseLine);
                    
		    } else
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
	
	String metodo;
	int indDelimitMet = command.indexOf(' ');
	
	if (indDelimitMet == -1) {
	    metodo = command.toUpperCase();

	    switch(metodo){
		case "LIST": return true;
		    //break;
		case "EXIT": return false;
		    //break;
		case "HELP": return false;
		    //break;
	    }	   

	} else if (indDelimitMet != -1)  {
	    metodo = command.substring(0, indDelimitMet).toUpperCase();
	    
	    switch(metodo){
		case "GET": return true;
		    //break;
		case "SET": return true;
		    //break;
		case "DEL": return true;
		    //break;
	    }
	}
	return false;
    }

    
    public void ayuda(){
	System.err.println("* get [key] \n** \t\tRetorna el valor asociado a dicha clave.");

	System.err.println("* set [key] [value] \n** \t\tAlmacena (en memoria) la clave, con el valor "+
				"\n** \t\tasociado. El valor puede contener cualquier "+
				"\n** \t\tcaracter, incluso caracteres especiales, tabs "+
				"\n** \t\ty espaciones en blanco.");

	System.err.println("* del [key] \n** \t\tElimina la clave, con su valor asociado.");

	System.err.println("* list \n** \t\tRetorna la lista de todas las claves almacenadas."+
				"\n** \t\tNO retorna los valores asociados a dichas claves.");

	System.err.println("* exit \n** \t\tTermina la conexión con el servidor y posteriormente, "+
				"\n** \t\ttermina ejecución del programa cliente.");

	System.err.println("* help \n** \t\tMuestra la lista de los comandos soportados, incluyendo "+
				"\n** \t\tuna breve explicación de los mismos.");
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

