package programa_cliente;

import java.io.*;
import java.net.*;
import libreria_cliente.LibreriaCliente;
 
public class ProgramaCliente {
 
    public static void main(String args[]) {
	
	String ip = args[0];
	int puerto = Integer.parseInt(args[1]);	

        LibreriaCliente lc = new LibreriaCliente( ip, puerto );
        
	lc.leerComando();
    }
}
