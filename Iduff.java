
import activerecord.ActiveRecord;
import controllers.AuthController;
import controllers.UsersController;
import views.CreateDepartmentView;
import views.LoginView;

public class Iduff{
    public static void main (String[] args){
        // USERS
        // Cadastro do administrador
        if (ActiveRecord.find_by("users", "role", "Manager") == null){
            String[] rafael_parameters = {"Rafael Carrilho", "111.111.111-11", "rafael@id.uff.br", "12345678", "111.111.111", "16/02/2000", "RJ" , "Brasil"};
            UsersController.register(rafael_parameters, 1);
        }
        AuthController.login("rafael@id.uff.br", "12345678");
        new CreateDepartmentView();
    }
}