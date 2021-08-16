import java.io.IOException;
import java.nio.file.*;

import db.QueryInterface;
import generalclasses.User;
public class Iduff{
    public static void main (String[] args){
        QueryInterface.createDatabaseArchives();
        ViewOperation.welcome();
        User.signUp();
        ViewOperation.routerOperations();
        
    }
}