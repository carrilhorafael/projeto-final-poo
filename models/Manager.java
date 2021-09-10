package models;

import activerecord.ActiveRecord;
import models.abstracts.User;

public class Manager extends User {
    private Manager() {
        super();
    }

    private Manager(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
    }

    public static Manager create(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        Manager manager = new Manager();
        manager.setName(name);
        manager.setCpf(cpf);
        manager.setEmail(email);
        manager.setPassword(password);
        manager.setRegistration(registration);
        manager.setBirthdate(birthdate);
        manager.setState(state);
        manager.setNationality(nationality);
        return manager;
    }
    public void delete(){
        ActiveRecord.delete("users", this.getId());
    }

    public static Manager serialize(String manager_stringified){
        Manager manager = new Manager(manager_stringified.split(" \\| "));
        return manager;
    }
}
