package views;

import java.util.Scanner;

import controllers.AuthController;
import models.abstracts.User;

public class LoginTerminalView {
    private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
    public static Scanner teclado = new Scanner(System.in);
    
    public static User loginView(){
        System.out.println(div_string);
        System.out.println("Faça login no IDUFF: ");
        System.out.print("Digite seu email: ");
        String email = teclado.next();
        System.out.print("Digite sua senha: ");
        String password = teclado.next();
        AuthController.login(email, password);
        User userLogged = AuthController.getLogged();
        // Teste de logado (pode se tornar um try catch)
        if (userLogged != null){
            System.out.println("Você está logado como " + userLogged.getName());
            return userLogged;
        }else{
            System.out.println("Erro no Login - Verifique os parametros informados");
            return null;
        }
    }
}
