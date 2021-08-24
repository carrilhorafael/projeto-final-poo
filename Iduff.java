import java.util.ArrayList;

import controllers.AuthController;
import controllers.CoursesController;
import controllers.SchoolYearsController;
import models.abstracts.User;
import models.classes.Course;
import models.classes.Manager;
import models.classes.SchoolYear;

public class Iduff{
    public static void main (String[] args){
        //ViewOperation.welcome();
        
        // USERS
        // Cadastro de usuários
        String[] rafael_parameters = {"Rafael Carrilho", "111.111.111-11", "rafael@email.com", "123456", "123456","234213", "brasil", "rj"};
        String[] paula_parameters = {"Paula Fernandes", "111.111.111-12", "paula@email.com", "123456", "123456","234213", "brasil", "rj"};
        String[] debora_parameters = {"Débora Barbosa", "111.111.111-13", "debora@email.com", "123456", "123456","234213", "brasil", "rj"};
        AuthController.register(rafael_parameters, 1);
        AuthController.register(paula_parameters, 1);
        AuthController.register(debora_parameters, 1);
        
        // Login do usuário Rafael
        User userLogged = AuthController.login("rafael@email.com", "123456");
        // Teste de logado (pode se tornar um try catch)
        if (userLogged != null){
            System.out.println("Você está logado como " + userLogged.getName());
        }else{
            System.out.println("Erro no Login - Verifique os parametros informados");
        }

        
        if (userLogged instanceof Manager){
            // SCHOOL YEAR
            // Criar 3 periodos letivos
            SchoolYearsController.create("2020", "2", "Closed");
            SchoolYearsController.create("2021", "1", "Closed");
            SchoolYearsController.create("2021", "2", "Planning");
            
            // Index dos periodos letivos
            ArrayList<SchoolYear> school_years = SchoolYearsController.index();
            System.out.println("Anos letivos cadastrados:");
            school_years.forEach(schoolyear -> {
                System.out.println(" -> " + schoolyear.getYear() + "." + schoolyear.getSemester());
            });
            
            // Destruir primeiro processo seletivo
            if(SchoolYearsController.destroy(school_years.get(1))){
                System.out.println("Ano letivo de " + school_years.get(1).getYear() + "." + school_years.get(1).getSemester() + " foi deletado");
                school_years.remove(1);
            }
            
            // COURSES
            // Criar 5 cursos
            String[] cc_parameters = {"Ciencia da Computação", "Tecnologia", "Praia Vermelha", "037"};
            String[] si_parameters = {"Sistemas de Informação", "Tecnologia", "Praia Vermelha", "083"};
            String[] ep_parameters = {"Engenharia de Produção", "Engenharia", "Praia Vermelha", "009"};
            String[] fis_parameters = {"Fisica", "Ciencia da Natureza", "Praia Vermelha", "011"};
            String[] mat_parameters = {"Matematica", "Ciencias exatas", "Praia Vermelha", "187"};
            CoursesController.create(cc_parameters);
            CoursesController.create(si_parameters);
            CoursesController.create(ep_parameters);
            CoursesController.create(fis_parameters);
            CoursesController.create(mat_parameters);
            
            // Index dos cursos
            ArrayList<Course> courses = CoursesController.index();
            System.out.println("Cursos cadastrados:");
            courses.forEach(each_course -> {
                System.out.println("Curso: " + each_course.getName() + ", da area de conhecimento " + each_course.getKnowledgeArea() + " e de código " + each_course.getCode());
            });
            
            // Destruir o primeiro curso
            if(CoursesController.destroy(courses.get(1))){
                System.out.println("Curso " + courses.get(1).getName() + " foi deletado com sucesso");
                courses.remove(1);
            }

        }else{
            System.out.println("Ainda não foi gerado as telas para esse tipo de usuário");
        }
    }
}