package programa_servidor;

import java.io.*;
import java.net.*;

public class CommandExecuter {
/*
	private String command;

	public CommandExecuter(String command) {
		this.command = command;
	}
*/
	public static String executeCommand(String command, KeyValueStore store) {
		String response = null;

		System.out.println("Comando: " + command);
		String[] commandDecoded = decodeCommand(command);
		System.out.println("metodo: " + commandDecoded[0]);
		System.out.println("param1: " + commandDecoded[1]);
		System.out.println("param2: " + commandDecoded[2]);

		if ("set".equals(commandDecoded[0])) {
			store.set(commandDecoded[1], commandDecoded[2]);
			response = "OK";
		} else if ("get".equals(commandDecoded[0])) {
			System.out.println("El valor es: " + store.get(commandDecoded[1]));
			response = store.get(commandDecoded[1]);
			if( response == null )	response = "Key no existe";
		} else if ("list".equals(commandDecoded[0])) {
			response = "";
			for (String key : store.list()) {
				System.out.println("val: "+key);
				response = response + key + " ";	
			}
		}
		return response;
	}

	private static String[] decodeCommand(String command) {
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
	                } else {
	                        parametro1 = comandoRestante.substring(0, indiceDelimitadorParametros);
	                        parametro2 = comandoRestante.substring(indiceDelimitadorParametros + 1);
	                }
		}

		String[] commandDecoded = {metodo, parametro1, parametro2};

		return commandDecoded;
	}

	public static String test(String line) {
		return line + "-jose";
	}
}
