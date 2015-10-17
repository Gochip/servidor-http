package ejecutador;

import comun.Codigos;
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
    public static final String PYTHON3 = "python";

    public RespuestaHTTP ejecutarSolicitud(SolicitudHTTP solicitud) throws Codigos.NotFoundException {
        RespuestaHTTP respuesta = new RespuestaHTTP();
        String a = buscarArchivo(solicitud);
        respuesta.setCodigo(OK);
        respuesta.setCuerpo(a);
        return respuesta;
    }

    private String buscarArchivo(SolicitudHTTP solicitud) throws Codigos.NotFoundException {
        try {
            String ub = solicitud.getArchivo().substring(1);
            if (ub.equals("favicon.ico")) {
                return "1";
            }
            File f = new File(ub);
            if (!f.exists()) {
                throw Codigos.NOT_FOUND;
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

    public String interpretarParametros(String parametros) {
        return parametros.replace('=', ' ').replace('&', ' ');
    }
}
