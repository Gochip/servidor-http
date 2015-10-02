package parser;

import comun.SolicitudHTTP;
import java.util.Scanner;

/**
 *
 */
public class Parser {

    public SolicitudHTTP parsearSolicitud(String cabecera) {
        System.out.println(cabecera);
        Scanner sc = new Scanner(cabecera);
        String metodo = sc.next();
        String archivo = sc.next();
        SolicitudHTTP solicitud = new SolicitudHTTP();
        solicitud.setMetodo(metodo);
        solicitud.setArchivo(archivo);
        return solicitud;
    }
}
