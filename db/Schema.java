package db;
import java.io.File;  
import java.io.IOException;  
public class Schema {
    public static void createDatabaseArchives(){
        File users = new File("/home/administrator/Documentos/poo/projeto-final-poo/db/users.dat"); 
        File classes = new File("/home/administrator/Documentos/poo/projeto-final-poo/db/classes.dat"); 
        try{  
            users.createNewFile();
            classes.createNewFile();  
            
        } catch (IOException e){  
        e.printStackTrace();
        }         
    }
}
