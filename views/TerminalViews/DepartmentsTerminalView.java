package views.TerminalViews;

import java.util.ArrayList;
import java.util.Scanner;

import controllers.DepartmentsController;
import controllers.UsersController;
import models.abstracts.User;
import models.Department;
import models.DepartmentCoordinator;

public class DepartmentsTerminalView {
    private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
    private static Scanner teclado = new Scanner(System.in);

    public static void index(){
        System.out.println(div_string);
        System.out.println("Você está na página de Departamentos.");
        System.out.println("Atualmente os seguintes Departamentos estão cadastrados: ");
        ArrayList<Department> departments = DepartmentsController.index();
        departments.forEach(each_department -> {
            System.out.println("-> " + each_department.getName() + ". Area de conhecimento: " + each_department.getKnowledgeArea() + ". Coordenador(a): " + each_department.getDepartmentCoordinator().getName());
        });
    }

    public static void show(User loggedUser){
        System.out.println(div_string);
        System.out.println("Meus Departamentos.");
        Department my_department = ((DepartmentCoordinator)loggedUser).getDepartment();
        System.out.println("Esses são os departamentos que você coordena:");

        System.out.println(my_department.getId() + " -> " + my_department.getName());

    }

    public static void create(){
        System.out.println(div_string);
        System.out.println("Criar um departamento. ");
        System.out.print("Digite o nome: ");
        String name = teclado.nextLine();
        System.out.print("Digite a area de conhecimento: ");
        String knowledge_area = teclado.nextLine();
        System.out.print("Digite o campus sede: ");
        String campus = teclado.nextLine();
        System.out.print("Digite o código: ");
        String code = teclado.nextLine();
        System.out.print("Escolha um coordenador para esse departamento: ");
        int coordinator_id = teclado.nextInt();
        String[] parameters = {name, knowledge_area, campus, code, Integer.toString(coordinator_id)};
        DepartmentsController.create(parameters);
    }

    public static void createCoordinator(){
        System.out.println(div_string);
        System.out.println("Essa é a página de criar coordenador de departamento.");
        System.out.print("Digite o nome: ");
        String name = teclado.nextLine();
        System.out.print("Digite o cpf: ");
        String cpf = teclado.nextLine();
        System.out.print("Digite o email: ");
        String email = teclado.nextLine();
        System.out.print("Digite a senha: ");
        String password = teclado.nextLine();
        System.out.print("Digite a matricula na UFF: ");
        String registration = teclado.nextLine();
        System.out.print("Digite a data de nascimento: ");
        String birthdate = teclado.nextLine();
        System.out.print("Digite o estado: ");
        String state = teclado.nextLine();
        System.out.print("Digite o país: ");
        String nationality = teclado.nextLine();
        String[] coord_parameters = {name, cpf, email, password, registration, birthdate, state, nationality};
        UsersController.register(coord_parameters, 2);
    }

    public static void delete(){
        System.out.println(div_string);
        System.out.println("Delete um departamento.");
        System.out.print("Digite o id do departamento: ");
        int department_id = teclado.nextInt();
        DepartmentsController.destroy(department_id);
    }
}
