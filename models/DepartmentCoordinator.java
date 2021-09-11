package models;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.abstracts.User;

public class DepartmentCoordinator extends User{
    private DepartmentCoordinator() {
        super();
    }

    private DepartmentCoordinator(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
    }

    public static DepartmentCoordinator create(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        DepartmentCoordinator coordinator = new DepartmentCoordinator();
        coordinator.setName(name);
        coordinator.setCpf(cpf);
        coordinator.setEmail(email);
        coordinator.setPassword(password);
        coordinator.setRegistration(registration);
        coordinator.setBirthdate(birthdate);
        coordinator.setState(state);
        coordinator.setNationality(nationality);
        return coordinator;
    }

    public static DepartmentCoordinator serialize(String coordinator_stringified){
        if(coordinator_stringified == null || !coordinator_stringified.split(" \\| ")[9].equals("DepartmentCoordinator")) return null;
        DepartmentCoordinator coordinator = new DepartmentCoordinator(coordinator_stringified.split(" \\| "));
        return coordinator;
    }
    public void delete(){
        ActiveRecord.delete("users", this.getId());
    }

    public Department getDepartment(){
        String department_stringified = ActiveRecord.find_by("departments", "department_coordinator_id", Integer.toString(this.getId()));
        Department department = Department.serialize(department_stringified);
        return department;
    }
}
