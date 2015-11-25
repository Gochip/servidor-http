package parser;

import comun.Codigos;
import comun.SolicitudHTTP;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parsea la solicitud HTTP
 */
public class Parser {

    static final ArrayList<String> metodosHTTP = new ArrayList<>(3);

    static {
        metodosHTTP.add("GET");
        metodosHTTP.add("HEAD");
        metodosHTTP.add("POST");
    }

    /**
     * Parsea la cabecera HTTP.
     *
     * @param cabecera es la cabecera a parsear.
     * @return la solicitud HTTP.
     * @throws comun.Codigos.HTTPException
     */
    public SolicitudHTTP parsearSolicitud(String cabecera) throws Codigos.HTTPException {
        Scanner sc = new Scanner(cabecera);
        String metodo = sc.next();
        if (!esMetodoValido(metodo)) {
            throw Codigos.BAD_REQUEST;
        }
        String archivo = sc.next();
        String parametros = "";
        int ind = -1;
        switch (metodo) {
            case "GET":
                ind = archivo.indexOf("?");
                if (ind != -1) {
                    parametros = archivo.substring(ind + 1, archivo.length());
                    archivo = archivo.substring(0, ind);
                }
                break;
            case "POST":                 
                while(sc.hasNext()){
                    parametros = sc.next();                            
                }
                //System.out.println(parametros);                                
                break;
            default:
                break;
        }
        SolicitudHTTP solicitud = new SolicitudHTTP();
        solicitud.setMetodo(metodo);
        solicitud.setArchivo(archivo);
        solicitud.setParametros(parametros);
        return solicitud;
    }

    /**
     * Este método indica si el método HTTP pasado como parámetro es válido o
     * no. El término válido significa si es soportado por este servidor o no.
     *
     * @param metodo
     * @return true o false indicando si el método es válido o no.
     */
    public boolean esMetodoValido(String metodo) {
        return metodosHTTP.contains(metodo);
    }
}
