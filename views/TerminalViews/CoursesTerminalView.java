// package views;

// import java.util.ArrayList;
// import java.util.Scanner;

// import controllers.CoursesController;
// import controllers.UsersController;
// import models.Course;

// public class CoursesTerminalView {
//     private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
//     private static Scanner teclado = new Scanner(System.in);

//     public static void show(){
//         System.out.println(div_string);
//         System.out.println("Você está na página de Cursos.");
//         System.out.println("Atualmente os seguintes Cursos estão cadastrados: ");
//         ArrayList<Course> courses = CoursesController.index();
//         courses.forEach(each_course -> {
//             System.out.println("-> " + each_course.getName() + ". Area de conhecimento: " + each_course.getKnowledgeArea() + ". Coordenador(a): " + each_course.getCourseCoordinator());
//         });
//     }

//     public static void create(){
//         System.out.println(div_string);
//         System.out.println("Criar um curso. ");
//         System.out.print("Digite o nome: ");
//         String name = teclado.nextLine();
//         System.out.print("Digite a area de conhecimento: ");
//         String knowledge_area = teclado.nextLine();
//         System.out.print("Digite o campus sede: ");
//         String campus = teclado.nextLine();
//         System.out.print("Digite o código: ");
//         String code = teclado.nextLine();
//         System.out.print("Escolha um coordenador para esse curso: ");
//         int coordinator_id = teclado.nextInt();
//         String[] parameters = {name, knowledge_area, campus, code, Integer.toString(coordinator_id)};
//         CoursesController.create(parameters);
//     }

//     public static void createCoordinator(){
//         System.out.println(div_string);
//         System.out.println("Essa é a página de criar coordenador de curso.");
//         System.out.print("Digite o nome: ");
//         String name = teclado.nextLine();
//         System.out.print("Digite o cpf: ");
//         String cpf = teclado.nextLine();
//         System.out.print("Digite o email: ");
//         String email = teclado.nextLine();
//         System.out.print("Digite a senha: ");
//         String password = teclado.nextLine();
//         System.out.print("Digite a matricula na UFF: ");
//         String registration = teclado.nextLine();
//         System.out.print("Digite a data de nascimento: ");
//         String birthdate = teclado.nextLine();
//         System.out.print("Digite o estado: ");
//         String state = teclado.nextLine();
//         System.out.print("Digite o país: ");
//         String nationality = teclado.nextLine();
//         String[] coord_parameters = {name, cpf, email, password, registration, birthdate, state, nationality};
//         UsersController.register(coord_parameters, 3);
//     }

//     public static void delete(){
//         System.out.println(div_string);
//         System.out.println("Delete um curso.");
//         System.out.print("Digite o id do curso: ");
//         int department_id = teclado.nextInt();
//         CoursesController.destroy(department_id);
//     }
// }
