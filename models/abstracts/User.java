package models.abstracts;
import java.io.Serializable;
import java.util.*;

import db.QueryInterface;

public class User extends QueryInterface implements Serializable{
    private String name, cpf, email, password, registration;
    public static Set<User> usersRegistered = new HashSet<>();
    
    public User(String name, String cpf, String email, String password, String registration){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.registration = registration;
    }
    public User(String line){
        String[] parameters = line.split(" \\| ");
        
        this.name = parameters[0];
        this.cpf = parameters[1];
        this.email = parameters[2];
        this.password = parameters[3];
        this.registration = parameters[4];
    }

    public String stringify(){
        return this.name + " | " + this.cpf + " | " + this.email + " | " + this.password + " | " + this.registration;
    }
    
    public boolean authenticate (String password){
        return this.password.equals(password); 
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
