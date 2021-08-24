package controllers;

import java.util.ArrayList;

import models.abstracts.User;
import models.classes.Manager;
import models.interfaces.QueryInterface;

public class UsersController {
    public static boolean register(String[] parameters, int kind){
        if (kind == 1){
            Manager manager = new Manager(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7]);
            
            return QueryInterface.save("users", manager.stringify());
        }
        return false;
    }
    

    // public static ArrayList <User> index(){
    //     ArrayList<String> users_stringifieds = QueryInterface.all("users");
    //     ArrayList<User> response = new ArrayList<>();
    //     if (users_stringifieds != null){
    //         users_stringifieds.forEach(cs -> {
    //             User user = new User(cs);
    //             response.add(user);
    //         });
    //         return response;
    //     }
    //     return null;
    // }

    public static boolean destroy(User user){
        return QueryInterface.delete("classrooms", user.stringify());
    }
}
