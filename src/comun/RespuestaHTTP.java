package comun;

/**
 *
 */
public class RespuestaHTTP {

    public String getRespuesta() {
        String respuesta = "HTTP/1.1 200 OK\n";
        respuesta += "Content-Length: 9\n";
        respuesta += "Connection: close\n";
        respuesta += "\n123456789";
        return respuesta;
    }
}
