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
		System.out.println("Comando: " + command);
		String[] commandDecoded = decodeCommand(command);
		System.out.println("metodo: " + commandDecoded[0]);
		System.out.println("param1: " + commandDecoded[1]);
		System.out.println("param2: " + commandDecoded[2]);

		if ("set".equals(commandDecoded[0])) {
			store.set(commandDecoded[1], commandDecoded[2]);
		} else if ("get".equals(commandDecoded[0])) {
			System.out.println("El valor es: " + store.get(commandDecoded[1]));
		}
		return null;
	}

	private static String[] decodeCommand(String command) {
		String metodo = null;
		String parametro1 = null;
		String parametro2 = null;

		int indiceDelimitadorMetodo = command.indexOf(' ');
		metodo = command.substring(0, indiceDelimitadorMetodo);

		String comandoRestante = command.substring(indiceDelimitadorMetodo + 1);

		int indiceDelimitadorParametros = comandoRestante.indexOf(' ');

		if (indiceDelimitadorParametros == -1) {
			parametro1 = comandoRestante;
		} else {
			parametro1 = comandoRestante.substring(0, indiceDelimitadorParametros);
			parametro2 = comandoRestante.substring(indiceDelimitadorParametros + 1);
		}

		String[] commandDecoded = {metodo, parametro1, parametro2};

		return commandDecoded;
	}
}
