import java.io.IOException;
import java.nio.file.*;

import controllers.DataController;
import generalclasses.User;
public class Iduff{
    public static void main (String[] args){
        DataController.createDatabaseArchives();
        ViewOperation.welcome();
        User.signUp();
        ViewOperation.routerOperations();
        
    }
}