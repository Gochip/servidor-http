package comun;

/**
 *
 */
public class SolicitudHTTP {
    
    private String metodo;
    private String archivo;
    private String agenteUsuario;

    public String getMetodo() {
        return metodo;
    }

    public String getArchivo() {
        return archivo;
    }

    public String getAgenteUsuario() {
        return agenteUsuario;
    }
    
    public void setArchivo( String a){
        a = " ";
        archivo = a;
    }
    
}
