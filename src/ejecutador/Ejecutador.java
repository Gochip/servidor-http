package ejecutador;

import comun.RespuestaHTTP;
import comun.SolicitudHTTP;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class Ejecutador {

    public static final String OK = "200 ok";
    public static final String NOT_FOUND = "404 not found";

    public RespuestaHTTP ejecutarSolicitud(SolicitudHTTP solicitud) {
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            String a = buscarArchivo(solicitud);
            respuesta.setCodigo(OK);
            respuesta.setCuerpo(a);
        } catch (NotFoundException e) {
            respuesta.setCuerpo("NOT FOUND");
            respuesta.setCodigo(NOT_FOUND);
        }
        return respuesta;
    }

    private String buscarArchivo(SolicitudHTTP solicitud) {
        try {
            String ub = solicitud.getArchivo().substring(1);
            if (ub.equals("favicon.ico")) {
                return "1";
            }
            File f = new File(ub);
            if (!f.exists()) {
                throw new NotFoundException();
            }

            StringBuilder ret = new StringBuilder();
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                ret.append(sc.nextLine());
            }
            return ret.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Error 500: Internal error";
        }
    }
}

class NotFoundException extends RuntimeException {

}
