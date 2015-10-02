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

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public void setAgenteUsuario(String agenteUsuario) {
        this.agenteUsuario = agenteUsuario;
    }

    public void setArchivo( String a){
        this.archivo = a;
    }
    
}
