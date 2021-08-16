import db.QueryInterface;
import models.abstracts.User;
public class Iduff{
    public static void main (String[] args){
        QueryInterface.createDatabaseArchives();
        ViewOperation.welcome();
        User.signUp();
        ViewOperation.routerOperations();
        
    }
}