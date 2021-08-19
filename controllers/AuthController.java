package controllers;

import db.QueryInterface;
import models.abstracts.User;
import models.classes.Manager;

public class AuthController {
    public static boolean register(String[] parameters, int kind){
        if (kind == 1){
            Manager manager = new Manager(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7]);
            
            return QueryInterface.create("users", manager.stringify());
        }
        return false;
    }
    
    public static User login(String email, String password){
        String user_stringified = QueryInterface.find_by("users", "email", email);
        if (user_stringified != null){
            User user = new User(user_stringified);
            
            if (user.authenticate(password))
                return user;
        }
        return null;
    }
}
