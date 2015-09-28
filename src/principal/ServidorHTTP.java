package principal;

import comun.RespuestaHTTP;
import comun.SolicitudHTTP;
import ejecutador.Ejecutador;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String linea = null;
                while ((linea = in.readLine()) != null) {
                    cabecera.append(linea);
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
