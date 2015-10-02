package comun;

/**
 *
 */
public class RespuestaHTTP {

    private String cuerpo;
    
    public String getRespuesta() {
        String respuesta = "HTTP/1.1 200 OK\n";
        respuesta += "Content-Length: " + cuerpo.length() + "\n";
        respuesta += "Connection: close\n";
        respuesta += "\n" + cuerpo;
        return respuesta;
    }
    
    public void setCuerpo(String a){
        cuerpo = a;
    }
}
