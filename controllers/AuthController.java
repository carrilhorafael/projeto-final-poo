package controllers;

import activerecord.ActiveRecord;
import models.CourseCoordinator;
import models.DepartmentCoordinator;
import models.Manager;
import models.Student;
import models.Teacher;
import models.abstracts.User;

public class AuthController {
    private static User user_logged;

    public static boolean login(String email, String password){
        String user_stringified = ActiveRecord.find_by("users", "email", email);
        if (user_stringified != null){
            String user_class = user_stringified.split(" \\| ")[9];
            if (user_class.equals("Manager")){
                Manager manager = Manager.serialize(user_stringified);
                if (manager.authenticate(password)) {
                    AuthController.user_logged = manager;
                    return true;
                }
            }else if (user_class.equals("DepartmentCoordinator")){
                DepartmentCoordinator department_coordinator = DepartmentCoordinator.serialize(user_stringified);
                if (department_coordinator.authenticate(password)){
                    AuthController.user_logged = department_coordinator;
                    return true;
                }
            }else if (user_class.equals("CourseCoordinator")){
                CourseCoordinator course_coordinator = CourseCoordinator.serialize(user_stringified);
                if (course_coordinator.authenticate(password)){
                    AuthController.user_logged = course_coordinator;
                    return true;
                }
            }else if (user_class.equals("Teacher")){
                Teacher teacher = Teacher.serialize(user_stringified);
                if (teacher.authenticate(password)){
                    AuthController.user_logged = teacher;
                    return true;
                }
            }else if (user_class.equals("Student")){
                Student student = Student.serialize(user_stringified);
                if(student.authenticate(password)){
                    AuthController.user_logged = student;
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean logout(){
        user_logged = null;
        return true;
    }
    public static User getUserLogged() {
        return user_logged;
    }
}
