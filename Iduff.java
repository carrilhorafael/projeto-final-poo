import java.util.ArrayList;

import controllers.AuthController;
import controllers.CoursesController;
import controllers.DepartmentsController;
import controllers.SchoolYearsController;
import controllers.UsersController;
import models.abstracts.User;
import models.classes.Course;
import models.classes.Department;
import models.classes.Manager;
import models.classes.SchoolYear;

public class Iduff{
    public static void main (String[] args){
        ViewOperation.welcome();
        
        // USERS
        // Cadastro do administrador
        String[] rafael_parameters = {"Rafael Carrilho", "111.111.111-11", "rafael@email.com", "123456", "111.111.111", "16022000", "RJ" , "Brasil"};
        UsersController.register(rafael_parameters, 1);
        
        ViewOperation.routerOperations();       
    }
}