package comun;

/**
 *
 */
public class SolicitudHTTP {

    private String metodo;
    private String archivo;
    private String parametros;
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

    public String getParametros() {
        return parametros;
    }

    public void setAgenteUsuario(String agenteUsuario) {
        this.agenteUsuario = agenteUsuario;
    }

    public void setArchivo(String a) {
        this.archivo = a;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

}
