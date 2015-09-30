package ejecutador;

import comun.RespuestaHTTP;
import comun.SolicitudHTTP;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
            String ub = solicitud.getArchivo();
            File f = new File(ub);
            if (!f.exists())
                er=1;

            FileInputStream fi = new FileInputStream(ub);
            DataInputStream file = new DataInputStream(fi);
            String ret = file.readUTF();
            
            return ret;
        }catch(IOException e){
            if ( er==1 )
                return "Error 404: Page not found";
            return "Error 500: Internal error";
        }        
    }    
}