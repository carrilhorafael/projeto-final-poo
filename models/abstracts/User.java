package models.abstracts;

import models.interfaces.QueryInterface;

public abstract class User {
    private String name, cpf, email, password, registration, state, birthdate, nationality;
    private int id;
    private static int next_user_id = Integer.parseInt(QueryInterface.find("ids", 1).split(" \\| ")[1]);

    public User(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.state = state;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.id = next_user_id;
        next_user_id++;
    }
    
    public User(String id, String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.state = state;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.id = Integer.parseInt(id);
    }

    public String stringify(){
        return this.id + " | " + this.name + " | " + this.cpf + " | " + this.email + " | " + this.password + " | " + this.registration + " | " + this.birthdate + " | " + this.state + " | " + this.nationality + " | " + this.getClass().toString().split("\\.")[2];
    }
    
    public boolean authenticate (String password){
        return this.password.equals(password); 
    }

    // Getters
    public int getId(){
        return id;
    }
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
