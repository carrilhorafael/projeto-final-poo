import activerecord.ActiveRecord;
import controllers.UsersController;
import views.LoginView;

public class Iduff{
    public static void main (String[] args){
        // USERS
        // Cadastro do administrador
        if (ActiveRecord.find_by("users", "role", "Manager") == null){
            String[] rafael_parameters = {"Rafael Carrilho", "111.111.111-11", "rafael@id.uff.br", "111.111.111", "16/02/2000", "RJ" , "Brasil"};
            UsersController.register(rafael_parameters, 1);
        }
        new LoginView();
    }
}