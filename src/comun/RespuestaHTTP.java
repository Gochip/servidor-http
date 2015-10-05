package comun;

/**
 *
 */
public class RespuestaHTTP {

    private String cuerpo;
    private String codigo;
    
    public String getRespuesta() {
        String respuesta = "HTTP/1.1 " + codigo + "\n";
        respuesta += "Content-Length: " + cuerpo.length() + "\n";
        respuesta += "Connection: close\n";
        respuesta += "\n" + cuerpo;
        return respuesta;
    }
    
    public void setCuerpo(String a){
        cuerpo = a;
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
}
