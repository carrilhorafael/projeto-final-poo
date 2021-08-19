package models.classes;

import models.abstracts.User;

public class Manager extends User {
    public Manager(String name, String cpf, String email, String password, String registration, String birthdate, String nationality, String state) {
        super(name, cpf, email, password, registration, birthdate, nationality, state);
        
    }
}
