package parser;

import comun.Codigos;
import comun.SolicitudHTTP;
import java.util.Scanner;

/**
 * Parsea la solicitud HTTP
 */
public class Parser {

    /**
     * Parsea la cabecera HTTP.
     *
     * @param cabecera es la cabecera a parsear.
     * @return la solicitud HTTP.
     * @throws comun.Codigos.HTTPException
     */
    public SolicitudHTTP parsearSolicitud(String cabecera) throws Codigos.HTTPException{
        Scanner sc = new Scanner(cabecera);
        String metodo = sc.next();
        if (!esMetodoValido(metodo)) {
            throw Codigos.BAD_REQUEST;
        }
        String archivo = sc.next();
        String parametros = "";
        int ind = archivo.indexOf("?");
        if (ind != -1) {
            parametros = archivo.substring(ind + 1, archivo.length());
            archivo = archivo.substring(0, ind);
        }
        SolicitudHTTP solicitud = new SolicitudHTTP();
        solicitud.setMetodo(metodo);
        solicitud.setArchivo(archivo);
        solicitud.setParametros(parametros);
        return solicitud;
    }

    public boolean esMetodoValido(String metodo) {
        boolean ok = false;
        switch (metodo) {
            case "GET":
            case "POST":
                ok = true;
        }
        return ok;
    }
}
