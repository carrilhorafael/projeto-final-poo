package models.classes;

import models.abstracts.User;

public class Manager extends User {
    public Manager(String name, String cpf, String email, String password, String registration, String birthdate, String nationality, String state) {
        super(name, cpf, email, password, registration, birthdate, state, nationality);
    }

    public Manager(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7]);
    }
}
