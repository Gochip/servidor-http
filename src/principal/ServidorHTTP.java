package principal;

import comun.RespuestaHTTP;
import comun.SolicitudHTTP;
import ejecutador.Ejecutador;
import java.io.DataOutputStream;
import java.io.IOException;
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
                Scanner sc = new Scanner(s.getInputStream());
                sc.useDelimiter("\\r\\n\\r\\n"); // Hasta una línea en blanco.
                if(sc.hasNext()){
                    cabecera.append(sc.next());
                }else{
                    System.out.println("No se recibió una cabecera");
                    continue;
                }
                

                Parser parser = new Parser();
                SolicitudHTTP solicitud = parser.parsearSolicitud(cabecera.toString());

                Ejecutador ejecutador = new Ejecutador();
                RespuestaHTTP respuesta = ejecutador.ejecutarSolicitud(solicitud);

                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                out.writeUTF(respuesta.getRespuesta());
                s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
