import java.util.ArrayList;

import db.QueryInterface;
import models.abstracts.User;
public class Iduff{
    public static void main (String[] args){
        // QueryInterface.createDatabaseArchives();
        ViewOperation.welcome();
        String line = QueryInterface.find_by("users", "email", "rafael@email.com");
        ArrayList<String> rafaeis = QueryInterface.where("users", "name", "Rafael");
        // User.signUp();
        // ViewOperation.routerOperations();
        
    }
}