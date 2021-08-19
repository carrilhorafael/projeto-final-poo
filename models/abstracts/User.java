package models.abstracts;
import java.util.*;

import models.classes.Manager;


public abstract class User {
    private String name, cpf, email, password, registration, state, birthdate, nationality;

     
    public User(String name, String cpf, String email, String password, String registration, String birthdate, String nationality, String state){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.state = state;
        this.birthdate = birthdate;
        this.nationality = nationality;
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
        return this.name + " | " + this.cpf + " | " + this.email + " | " + this.password + " | " + this.registration + " | " + this.birthdate + " | " + this.state + " | " + this.nationality + " | " + this.getClass().toString().split("\\.")[2];
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
