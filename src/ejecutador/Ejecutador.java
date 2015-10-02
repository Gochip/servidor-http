package ejecutador;

import comun.RespuestaHTTP;
import comun.SolicitudHTTP;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class Ejecutador {
    public RespuestaHTTP ejecutarSolicitud(SolicitudHTTP solicitud){      
        
        String a = buscarArchivo(solicitud);
        RespuestaHTTP respuesta = new RespuestaHTTP();
        respuesta.setCuerpo(a);
        return respuesta;
    }
    
    
    private String buscarArchivo(SolicitudHTTP solicitud){  
        int er = 0;
        try{
            String ub = solicitud.getArchivo().substring(1);
            if(ub.equals("favicon.ico")){
                return "1";
            }
            File f = new File(ub);
            if (!f.exists())
                er=1;

            System.out.println("ok");
            StringBuilder ret = new StringBuilder();
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                ret.append(sc.nextLine());
            }
            return ret.toString();
        }catch(IOException e){
            System.out.println(e.getMessage());
            if ( er==1 )
                return "Error 404: Page not found";
            return "Error 500: Internal error";
        }        
    }    
}