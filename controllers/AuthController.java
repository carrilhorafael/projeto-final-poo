package controllers;

import db.QueryInterface;
import models.abstracts.User;

public class AuthController {
    public static boolean register(String[] parameters, int kind){
        User user = new User (parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]);
        return QueryInterface.create("users", user.stringify());
    }
    
    public static User login(String email, String password){
        String user_stringified = QueryInterface.find_by("users", "email", email);
        User user = new User(user_stringified);
        if (user.authenticate(password))
            return user;
        else
            return null;
    }
}
