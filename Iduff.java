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
        
        // Login do usuário administrador Rafael
        User userLogged = AuthController.login("rafael@email.com", "123456");
        // Teste de logado (pode se tornar um try catch)
        if (userLogged != null){
            System.out.println("Você está logado como " + userLogged.getName());
        }else{
            System.out.println("Erro no Login - Verifique os parametros informados");
        }

        if (userLogged instanceof Manager){
            // DEPARTMENTS
            // Cadastro do Coordenador de Departamento
            String[] debora_parameters = {"Débora Barbosa", "111.111.111-13", "debora@email.com", "123456", "123456", "234213", "brasil", "rj"};
            UsersController.register(debora_parameters, 2);
            
            // Cadastra 3 departamentos
            String[] gma_params = {"GMA", "Matemática", "Gragoatá", "111", "2"};
            String[] ggm_params = {"GGM", "Geometria", "Gragoatá", "112", "2"};
            String[] gat_params = {"GAT", "Matemática", "Gragoatá", "113", "2"};
            DepartmentsController.create(gma_params);
            DepartmentsController.create(ggm_params);
            DepartmentsController.create(gat_params);
            
            // Index dos cursos
            ArrayList<Department> departments = DepartmentsController.index();
            System.out.println("Departamentos cadastrados:");
            departments.forEach(each_department -> {
                System.out.println("-> " + each_department.getName() + ". Area de conhecimento: " + each_department.getKnowledgeArea() + ". Coordenadora: " + each_department.getDepartmentCoordinator());
            });
            
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
            
            // SCHOOL YEAR
            // Criar 3 periodos letivos
            String[] sy2020_2 = {"2020", "2", "Closed"};
            String[] sy2021_1 = {"2021", "1", "Closed"};
            String[] sy2021_2 = {"2021", "2", "Planning"};        
            SchoolYearsController.create(sy2020_2);
            SchoolYearsController.create(sy2021_1);
            SchoolYearsController.create(sy2021_2);
            
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
            
            
        }else{
            System.out.println("Ainda não foi gerado as telas para esse tipo de usuário");
        }       
    }
}