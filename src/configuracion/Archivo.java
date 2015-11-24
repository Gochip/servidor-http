
package configuracion;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
 
public class Archivo{
    
    public static void lectura(String archivo){
        try{
            File f = new File(archivo);
            Scanner b = new Scanner(f);
            String lin="";
            while(b.hasNextLine()) {
                
                
                b.useDelimiter("=");
                lin = b.next();
                System.out.println(lin);
                
            }            
            b.close();            
        }catch(IOException e){            
            e.printStackTrace();
        }        
    }
    
    
}



