import java.util.ArrayList;

import controllers.AuthController;
import controllers.SchoolYearController;
import db.QueryInterface;
import models.abstracts.User;
import models.classes.SchoolYear;

public class Iduff{
    public static void main (String[] args){
        ViewOperation.welcome();
        String[] rafael_parameters = {"Rafael Carrilho", "111.111.111-11", "rafael@email.com", "123456", "123456"};
        String[] paula_parameters = {"Paula Fernandes", "111.111.111-12", "paula@email.com", "123456", "123456"};
        String[] debora_parameters = {"Débora Barbosa", "111.111.111-13", "debora@email.com", "123456", "123456"};
        AuthController.register(rafael_parameters, 1);
        AuthController.register(paula_parameters, 1);
        AuthController.register(debora_parameters, 1);
        User userLogged = AuthController.login("rafael@email.com", "123456");
        if (userLogged != null){
            System.out.println("Você está logado como " + userLogged.getName());
        }else{
            System.out.println("Login errado ai!");
        }
        if (SchoolYearController.create("2020", "2", "Closed")) System.out.println("Período letivo de 2020.2 criado com sucesso");
        if (SchoolYearController.create("2021", "1", "Closed")) System.out.println("Período letivo de 2021.1 criado com sucesso");
        if (SchoolYearController.create("2021", "2", "Planning")) System.out.println("Período letivo de 2021.2 criado com sucesso");
        ArrayList<SchoolYear> school_years = SchoolYearController.index();
        System.out.println("Anos letivos cadastrados:");
        school_years.forEach(schoolyear -> {
            System.out.println(" -> " + schoolyear.getYear() + "." + schoolyear.getSemester());
        });
        if(SchoolYearController.destroy(school_years.get(1))){
            System.out.println("Ano letivo de " + school_years.get(1).getYear() + "." + school_years.get(1).getSemester() + " foi deletado");
            school_years.remove(1);
        }
    }
}