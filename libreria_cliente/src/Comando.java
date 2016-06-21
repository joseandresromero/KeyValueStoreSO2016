package libreria_cliente;

import java.io.*;
import java.net.*;

public class Comando implements Serializable { 

    private String metodo;
    private String parametro1;
    private String parametro2;

    public Comando(String metodo, String parametro1, String parametro2) {
        this.metodo = metodo;
        this.parametro1 = parametro1;
        this.parametro2 = parametro2;
    }

    public String getMetodo() {
        return this.metodo;
    }

    public String getParametro1() {
        return this.parametro1;
    }

    public String getParametro2() {
        return this.parametro2;
    }
}
