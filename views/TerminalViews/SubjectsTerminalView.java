// package views.TerminalViews;

// import java.util.ArrayList;
// import java.util.Scanner;

// import controllers.SubjectsController;
// import models.SchoolYear;
// import models.Subject;
// import models.QueryInterface;

// public class SubjectsTerminalView {
//     private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
//     private static Scanner teclado = new Scanner(System.in);

//     public static void index(){
//         System.out.println(div_string);
//         System.out.println("Você está na página de Matérias dos Departamentos.");
//         System.out.println("Atualmente as seguintes mátérias são ofertadas pelos Departamentos: ");
//         ArrayList<Subject> subjects = SubjectsController.index();
//         subjects.forEach(each_subject -> {
//             System.out.println("-> " + each_subject.getName() + ". Area de conhecimento: " + each_subject.getKnowledgeArea() + ". Departamento: " + each_subject.getDepartment().getName());
//         });
//     }
//     public static void create(){
//         System.out.println(div_string);
//         System.out.println("Crie uma nova matéria.");
//         System.out.print("Digite o nome: ");
//         String name = teclado.nextLine();
//         System.out.print("Digite a area de conhecimento: ");
//         String knowledge_area = teclado.nextLine();
//         System.out.print("Digite a carga horária: ");
//         String ch = teclado.nextLine();
//         System.out.print("Escolha o departamento responsável: ");
//         int department_id = teclado.nextInt();
//         SchoolYear year_planning = new SchoolYear(QueryInterface.find_by("schoolyears", "status", "Planejamento"));
//         int year_id = year_planning.getId();
//         String[] parameters = {name, knowledge_area, ch, Integer.toString(department_id), Integer.toString(year_id)};
//         SubjectsController.create(parameters);
//     }
//     public static void delete(){
//         System.out.println(div_string);
//         System.out.println("Delete uma matéria.");
//         System.out.print("Digite o id da matéria: ");
//         int subject_id = teclado.nextInt();
//         SubjectsController.destroy(subject_id);
//     }


// }
