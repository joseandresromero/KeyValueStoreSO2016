package libreria_cliente;

import java.io.*;
import java.net.*;

public class Respuesta implements Serializable {

    private boolean success;
    private Object respuesta;

    public Respuesta(boolean success, String respuesta) {
        this.success = success;
        this.respuesta = respuesta;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Object respuesta) {
        this.respuesta = respuesta;
    }    
}
