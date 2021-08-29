package controllers;

import java.util.ArrayList;

import models.classes.CourseCoordinator;
import models.classes.DepartmentCoordinator;
import models.classes.Manager;
import models.classes.Teacher;
import models.interfaces.QueryInterface;

public class UsersController {
    public static boolean register(String[] parameters, int kind){
        if (kind == 1){
            Manager manager = new Manager(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7]);   
            return QueryInterface.save("users", manager.stringify());
        }else if (kind == 2){
            DepartmentCoordinator department_coordinator = new DepartmentCoordinator(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7]);   
            return QueryInterface.save("users", department_coordinator.stringify());
        }else if (kind == 3){
            CourseCoordinator course_coordinator = new CourseCoordinator(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7]);   
            return QueryInterface.save("users", course_coordinator.stringify());
        }else if (kind == 4){
            Teacher teacher = new Teacher(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
            if (QueryInterface.save("users", teacher.stringify())){
                String teachers_department = teacher.getId() + " | " + teacher.getDepartment().getId();
                return QueryInterface.save("teachersdepartments", teachers_department);
            }
            return false;
        }else if (kind == 5){
            return true;
        }
        else{
            return false;
        }
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

    public static boolean destroy(int user_id){
        return QueryInterface.delete("classrooms", user_id);
    }
}
