package principal;

import comun.Codigos;
import comun.RespuestaHTTP;
import comun.SolicitudHTTP;
import ejecutador.Ejecutador;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import parser.Parser;

/**
 *
 */
public class ServidorHTTP {

    private static final int PUERTO = 8081;

    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(PUERTO);
            while (true) {
                Socket s = ss.accept();
                System.out.println("ACEPTADO");

                StringBuilder cabecera = new StringBuilder();
                System.out.println(s);
                InputStream is = s.getInputStream();
                Scanner sc = new Scanner(is);
                System.out.println();
                //sc.useDelimiter("\\r\\n\\r\\n"); // Hasta una línea en blanco.                                
                sc.useDelimiter("\\z"); // Hasta final del string.
                if (sc.hasNext()) {
                    cabecera.append(sc.next());
                } else {                    
                        System.out.println("No se recibió una cabecera");
                        continue;                    
                }                
                System.out.println(cabecera);
                RespuestaHTTP respuesta = null;
                try {
                    Parser parser = new Parser();
                    SolicitudHTTP solicitud = parser.parsearSolicitud(cabecera.toString());
                    Ejecutador ejecutador = new Ejecutador();
                    respuesta = ejecutador.ejecutarSolicitud(solicitud);
                } catch (Codigos.HTTPException e) {
                    respuesta = new RespuestaHTTP();
                    respuesta.setCodigo(e.toString());
                    respuesta.setCuerpo(e.cuerpo);
                }

                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                out.writeUTF(respuesta.getRespuesta());
                s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
