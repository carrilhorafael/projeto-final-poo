import java.util.Scanner;

import models.CourseCoordinator;
import models.DepartmentCoordinator;
import models.Manager;
import models.abstracts.User;
import views.CoursesTerminalView;
import views.DepartmentsTerminalView;
import views.LoginTerminalView;
import views.SchoolYearsTerminalView;
import views.SubjectsTerminalView;

public class ViewOperation {
    private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
    public static Scanner teclado = new Scanner(System.in);

    public static void welcome() {
        System.out.println(div_string);
        System.out.println("           Bem vindo ao sistema de gerenciamento de graduação da UFF construido por Débora Barbosa, Paula Fernandes e Rafael Carrilho!          ");
        System.out.println("     Use as entradas do terminal explicitadas para passar pelas funcionalidades. Se quiser encerrar a execução, digite 0 no menu de controle.     ");
        System.out.println(div_string);
    }


    public static void routerOperations(){
        int operation = 0;
        boolean authenticated = false;
        User loggedUser = null;

        do{
            System.out.println(div_string);
            if(!authenticated){
                System.out.println("Você ainda não está logado. Você tem as seguintes opções: ");
                String[] guest_options = {"Login"};
                operation = showOptions(guest_options);
                if (operation == 1){
                    loggedUser = LoginTerminalView.loginView();
                    if (loggedUser != null)
                        authenticated = true;
                }
                else if(operation != 0)
                    throwOperationError();
            }
            else{
                if (loggedUser instanceof Manager){
                    System.out.println("Você está logado como "+ loggedUser.getName() + ". PERMISSÃO: Diretor");
                    String[] manager_options = {"Anos Letivos", "Departamentos", "Cursos"};
                    operation = showOptions(manager_options);
                    if (operation == 1){
                        do{
                            SchoolYearsTerminalView.show();
                            String[] schoolyears_options = {"Novo ano letivo", "Deletar ano letivo", "Voltar"};
                            operation = showOptions(schoolyears_options);
                            if (operation == 1){
                                SchoolYearsTerminalView.create();
                            }else if(operation == 2){
                                SchoolYearsTerminalView.delete();
                            }else if(operation != 0 && operation != 3){
                                throwOperationError();
                            }
                        }while(operation != 3 && operation != 0);

                    } else if (operation == 2){
                        do{
                            DepartmentsTerminalView.index();
                            String[] departments_options = {"Criar coordenador de departamento", "Criar departamento", "Deletar departamento", "Voltar"};
                            operation = showOptions(departments_options);
                            if (operation == 1){
                                DepartmentsTerminalView.createCoordinator();
                            }else if(operation == 2){
                                DepartmentsTerminalView.create();
                            }else if(operation == 3){
                                DepartmentsTerminalView.delete();
                            }else if(operation != 0 && operation != 4){
                                throwOperationError();
                            }
                        }while(operation != 4 && operation != 0);

                    } else if (operation == 3){
                        do{
                            CoursesTerminalView.show();
                            String[] courses_options = {"Criar coordenador de curso", "Criar curso", "Deletar curso", "Voltar"};
                            operation = showOptions(courses_options);
                            if (operation == 1){
                                CoursesTerminalView.createCoordinator();
                            }else if(operation == 2){
                                CoursesTerminalView.create();
                            }else if(operation == 3){
                                CoursesTerminalView.delete();
                            }else if(operation != 0 && operation != 4){
                                throwOperationError();
                            }
                        }while(operation != 4 && operation != 0);

                    } else if (operation != 0){
                        throwOperationError();
                    }
                }else if (loggedUser instanceof DepartmentCoordinator){
                    System.out.println("Você está logado como "+ loggedUser.getName() + ". PERMISSÃO: Coordenador de Departamento");
                    String[] dep_coordinator_options = {"Meu departamento", "Matérias"};
                    operation = showOptions(dep_coordinator_options);
                    if (operation == 1){
                        do{
                            DepartmentsTerminalView.show();
                            String[] dep_options = {"Criar professor para o departamento", "Deletar departamento", "Voltar"};
                            operation = showOptions(dep_options);
                            if(operation == 1){
                                DepartmentsTerminalView.createTeacher();
                            }else if (operation == 2){
                                DepartmentsTerminalView.delete();
                            }else if(operation != 0 && operation != 2){
                                throwOperationError();
                            }
                        }while(operation != 2 && operation != 0);
                    }else if(operation == 2){
                        do{
                            SubjectsTerminalView.index();
                            String[] subject_options = {"Criar matéria", "Deletar matéria", "Voltar"};
                            operation = showOptions(subject_options);
                            if (operation == 1){
                                SubjectsTerminalView.create();
                            }else if(operation == 2){
                                SubjectsTerminalView.delete();
                            }else if(operation != 0 && operation != 3){
                                throwOperationError();
                            }
                        }while(operation != 3 && operation != 0);
                    }else if(operation != 0){
                        throwOperationError();
                    }
                }
            }

        }while(operation != 0);
    }
    public static int showOptions(String[] options){
        int response;
        System.out.println("Você tem as seguintes opções: ");
        for (int i = 0; i < options.length; i++){
            System.out.print((i+1) + " - " + options[i]);
            if (i != options.length-1)
                System.out.print(" | ");
        }
        System.out.println(" ||| 0 - Sair");
        response = teclado.nextInt();
        return response;
    }
    public static void throwOperationError(){
        System.out.println("(422) Opção invalida - Digite uma opção válida!");
    }
}
