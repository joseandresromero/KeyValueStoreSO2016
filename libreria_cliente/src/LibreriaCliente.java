package libreria_cliente;

import java.io.*;
import java.net.*;
 
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
            System.err.println("El servidor no está levantado");
        } catch (IOException e) {
            System.err.println("Error: " + e );
        }
 
    }

    public void leerComando(){
	BufferedReader user = new BufferedReader( new InputStreamReader(System.in));
	if ( cliente != null && os != null && is != null) {
	    try{
	    	String responseLine = "Leyendo";

		while (responseLine != null) {
		    
		    String userInput = user.readLine();
		    os.println(userInput);
	  	    responseLine = is.readLine();
                    System.out.println("Server: " + responseLine);
                    
		    if (responseLine.toUpperCase().indexOf("OK") != -1) {
			break;
                    }
                }
		// clean up:
		// close the output stream
		// close the input stream
		// close the socket
        	os.close();
                is.close();
                cliente.close(); 
	    }catch(UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
	}

    }

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

    public void validaCommand(String command) {
	
	String metodo = null;
	String parametro1 = null;
	String parametro2 = null;

	int indiceDelimitadorMetodo = command.indexOf(' ');

	if (indiceDelimitadorMetodo == -1) {
	    metodo = command;
	} else {
	    metodo = command.substring(0, indiceDelimitadorMetodo);

	    String comandoRestante = command.substring(indiceDelimitadorMetodo + 1);

	    int indiceDelimitadorParametros = comandoRestante.indexOf(' ');

	    if (indiceDelimitadorParametros == -1) {
		parametro1 = comandoRestante;
	    }
	}
    }

}
