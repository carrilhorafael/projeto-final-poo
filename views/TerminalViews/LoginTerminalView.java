package views.TerminalViews;

import java.util.Scanner;

import controllers.AuthController;

public class LoginTerminalView {
    private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
    public static Scanner teclado = new Scanner(System.in);

    public static void loginView(){
        System.out.println(div_string);
        System.out.println("Faça login no IDUFF: ");
        System.out.print("Digite seu email: ");
        String email = teclado.next();
        System.out.print("Digite sua senha: ");
        String password = teclado.next();
        AuthController.login(email, password);

    }
}
