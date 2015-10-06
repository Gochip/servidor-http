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
    public static final String PYTHON3 = "python";

    public RespuestaHTTP ejecutarSolicitud(SolicitudHTTP solicitud) {
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            String a = buscarArchivo(solicitud);
            respuesta.setCodigo(OK);
            respuesta.setCuerpo(a);
        } catch (NotFoundException e) {
            respuesta.setCuerpo(PaginaNotFound.html);
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
            StringBuilder cuerpoRespuesta = new StringBuilder();
            String nombreArchivo = f.getName();
            int ind = nombreArchivo.lastIndexOf(".");
            String extension = nombreArchivo.substring(ind + 1, nombreArchivo.length());
            
            if (extension.equals("py")) {
                String parametros = interpretarParametros(solicitud.getParametros());
                String comando = PYTHON3 + " " + f.getAbsolutePath() + " " + parametros;
                System.out.println(comando);
                Process p = Runtime.getRuntime().exec(comando);
                
                Scanner sc = new Scanner(p.getErrorStream());
                while (sc.hasNextLine()) {
                    cuerpoRespuesta.append(sc.nextLine());
                }
                sc = new Scanner(p.getInputStream());
                while (sc.hasNextLine()) {
                    cuerpoRespuesta.append(sc.nextLine());
                }
            } else {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    cuerpoRespuesta.append(sc.nextLine());
                }
            }
            return cuerpoRespuesta.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Error 500: Internal error";
        }
    }
    
    public String interpretarParametros(String parametros){
        return parametros.replace('=', ' ').replace('&', ' ');
    }
}

class NotFoundException extends RuntimeException {

}

class PaginaNotFound{
    public static String html = "<!DOCTYPE html>\n<html><head><meta charset='utf-8' /></head><body><h1>Página no encontrada</h1></body></html> ";
}