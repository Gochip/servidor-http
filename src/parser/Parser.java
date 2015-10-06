package parser;

import comun.SolicitudHTTP;
import java.util.Scanner;

/**
 *
 */
public class Parser {

    public SolicitudHTTP parsearSolicitud(String cabecera) {
        Scanner sc = new Scanner(cabecera);
        String metodo = sc.next();
        String archivo = sc.next();
        String parametros = "";
        int ind = archivo.indexOf("?");
        if(ind != -1){
            parametros = archivo.substring(ind + 1, archivo.length());
            archivo = archivo.substring(0, ind);
        }
        SolicitudHTTP solicitud = new SolicitudHTTP();
        solicitud.setMetodo(metodo);
        solicitud.setArchivo(archivo);
        solicitud.setParametros(parametros);
        return solicitud;
    }
}
