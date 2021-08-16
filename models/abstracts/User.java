package models.abstracts;
import java.io.Serializable;
import java.util.*;

import db.QueryInterface;

public class User implements Serializable{
    private String name, cpf, email, password, registration;
    public static Set<User> usersRegistered = new HashSet<>();
    
    public User(String name, String cpf, String email, String password, String registration){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.registration = registration;
        
    }

    public static User login(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite seu email: ");
        String email = teclado.next();
        System.out.print("Digite sua senha: ");
        String password = teclado.next();
        User user = QueryInterface.tryAuthenticate(email, password);
        if (user != null)
            return user;
        else {
            System.out.println("Não foi possivel fazer login, verifique as credenciais digitadas");
            return null;
        }
    }

    public static void signUp() {
        User rafael = new User("Rafael", "111.111.111-11", "rafael@email.com", "12345678", "111.111.111");
        User rafael2 = new User("Rafael", "111.111.111-11", "rafael@email.com", "12345678", "111.111.111");
        User debora = new User("Debora", "111.111.111-12", "debora@email.com", "12345678", "111.111.112");
        User paula = new User("Paula", "111.111.111-13", "paula@email.com", "12345678", "111.111.113");
        usersRegistered.add(rafael);
        usersRegistered.add(rafael2);
        usersRegistered.add(paula);
        usersRegistered.add(debora);
        QueryInterface.trySaveUser(rafael);
        QueryInterface.trySaveUser(debora);
        QueryInterface.trySaveUser(paula);
    }

    // Getters
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getRegistration() {
        return registration;
    }
}
