import controllers.AuthController;
import models.abstracts.User;

public class Iduff{
    public static void main (String[] args){
        ViewOperation.welcome();
        // String[] rafael_parameters = {"Rafael", "111.111.111-11", "rafael@email.com", "123456", "123456"};
        // String[] paula_parameters = {"Paula", "111.111.111-12", "paula@email.com", "123456", "123456"};
        // String[] debora_parameters = {"Débora", "111.111.111-13", "debora@email.com", "123456", "123456"};
        // AuthController.register(rafael_parameters, 1);
        // AuthController.register(paula_parameters, 1);
        // AuthController.register(debora_parameters, 1);
        User userLogged = AuthController.login("rafael@email.com", "123456");
        if (userLogged != null){
            System.out.println("Você está logado como " + userLogged.getName());
        }
        else{
            System.out.println("Login errado ai!");
        }
    }
}