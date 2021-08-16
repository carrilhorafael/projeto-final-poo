import java.util.Scanner;

import generalclasses.User;

public class ViewOperation {
    private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
    public static void welcome() {
        System.out.println(div_string);
        System.out.println("           Bem vindo ao sistema de gerenciamento de graduação da UFF construido por Débora Barbosa, Paula Fernandes e Rafael Carrilho!          ");
        System.out.println("     Use as entradas do terminal explicitadas para passar pelas funcionalidades. Se quiser encerrar a rotina, digite 0 no menu de controle.     ");
        System.out.println(div_string); 
    }

    public static void routerOperations(){
        Scanner teclado = new Scanner(System.in);
        int operations = 0;
        boolean authenticated = false;
        User loggedUser = null;
        do{
            System.out.println(div_string);
            if(!authenticated){
                System.out.println("Você ainda não está logado. Você tem as seguintes opções: ");
                System.out.println("1 - Login ||| 0 - Sair");
                operations = teclado.nextInt();
                if (operations == 1)
                loggedUser = User.login();
                if (loggedUser != null){
                    authenticated = true;
                }
                else if(operations != 1 && operations != 0)
                System.out.println("Por favor, selecione uma opção valida!");
            }
            else{
                System.out.println("Você está logado como " + loggedUser.getName() + " de CPF " + loggedUser.getCpf());
                System.out.println("Ainda não temos nenhuma funcionalidade desenvolvida para o seu tipo de usuário, espere um pouco");
                operations = 0;
            }
            
        }while(operations != 0);
    }
}
