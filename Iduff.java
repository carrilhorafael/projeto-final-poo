
import activerecord.ActiveRecord;
import controllers.DepartmentsController;
import controllers.UsersController;
import models.Teacher;

public class Iduff{
    public static void main (String[] args){
        ViewOperation.welcome();;
        // USERS
        // Cadastro do administrador
        if (ActiveRecord.find_by("users", "role", "Manager") == null){
            String[] rafael_parameters = {"Rafael Carrilho", "111.111.111-11", "rafael@email.com", "123456", "111.111.111", "16022000", "RJ" , "Brasil"};
            UsersController.register(rafael_parameters, 1);
        }

        ViewOperation.routerOperations();
	}
}
