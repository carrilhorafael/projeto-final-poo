package controllers;

import models.abstracts.User;
import models.classes.Manager;
import models.interfaces.QueryInterface;

public class AuthController {
    
    public static User login(String email, String password){
        String[] user_stringified_params = QueryInterface.find_by("users", "email", email).split(" \\| ");
        if (user_stringified_params != null){
            String user_class = user_stringified_params[9];
            if (user_class.equals("Manager")){
                Manager manager = new Manager(user_stringified_params);                
                if (manager.authenticate(password))
                return manager;
            }
        }
        return null;
    }
}
