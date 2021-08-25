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
        // USERS
        // Cadastro do administrador
        String[] rafael_parameters = {"Rafael Carrilho", "111.111.111-11", "rafael@email.com", "123456", "111.111.111", "16022000", "RJ" , "Brasil"};
        UsersController.register(rafael_parameters, 1);
        
        

        if (userLogged instanceof Manager){
            // DEPARTMENTS
            // Cadastro do Coordenador de Departamento
            
            
            
            // Index dos cursos
            ArrayList<Department> departments = DepartmentsController.index();
            System.out.println("Departamentos cadastrados:");
            
            
            // Destruir o primeiro curso
            if(DepartmentsController.destroy(departments.get(1))){
                System.out.println("Departamento " + departments.get(1).getName() + " foi deletado com sucesso");
                departments.remove(1);
            }
            
            // COURSES
            // Cadastra um coordenador de curso            
            String[] paula_parameters = {"Paula Fernandes", "111.111.111-12", "paula@email.com", "123456", "111.111.112", "23/02/2000", "RJ" , "Brasil"};
            UsersController.register(paula_parameters, 3);
            
            // Criar 5 cursos
            String[] cc_parameters = {"Ciencia da Computação", "Tecnologia", "Praia Vermelha", "037", "3"};
            String[] si_parameters = {"Sistemas de Informação", "Tecnologia", "Praia Vermelha", "083", "3"};
            String[] ep_parameters = {"Engenharia de Produção", "Engenharia", "Praia Vermelha", "009", "3"};
            String[] fis_parameters = {"Fisica", "Ciencia da Natureza", "Praia Vermelha", "011", "3"};
            String[] mat_parameters = {"Matematica", "Ciencias exatas", "Praia Vermelha", "187", "3"};
            CoursesController.create(cc_parameters);
            CoursesController.create(si_parameters);
            CoursesController.create(ep_parameters);
            CoursesController.create(fis_parameters);
            CoursesController.create(mat_parameters);
            
            // Index dos cursos
            ArrayList<Course> courses = CoursesController.index();
            System.out.println("Cursos cadastrados:");
            courses.forEach(each_course -> {
                System.out.println("-> " + each_course.getName() + ". Area de conhecimento: " + each_course.getKnowledgeArea() + ". Coordenadora: " + each_course.getCourseCoordinator());
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