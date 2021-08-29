package controllers;

import models.abstracts.User;
import models.classes.CourseCoordinator;
import models.classes.DepartmentCoordinator;
import models.classes.Manager;
import models.interfaces.QueryInterface;

public class AuthController {
    private static User user_logged;

    public static void login(String email, String password){
        String[] user_stringified_params = QueryInterface.find_by("users", "email", email).split(" \\| ");
        if (user_stringified_params != null){
            String user_class = user_stringified_params[9];
            if (user_class.equals("Manager")){
                Manager manager = new Manager(user_stringified_params);                
                if (manager.authenticate(password))
                AuthController.user_logged = manager;
            }else if (user_class.equals("DepartmentCoordinator")){
                DepartmentCoordinator department_coordinator = new DepartmentCoordinator(user_stringified_params);                
                if (department_coordinator.authenticate(password))
                AuthController.user_logged = department_coordinator;
            }else if (user_class.equals("CourseCoordinator")){
                CourseCoordinator course_coordinator = new CourseCoordinator(user_stringified_params);                
                if (course_coordinator.authenticate(password))
                AuthController.user_logged = course_coordinator;
            }
        }
    }
    public static User getLogged(){
        return user_logged;
    }
}
