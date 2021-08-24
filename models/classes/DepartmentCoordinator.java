package models.classes;

import java.util.ArrayList;

import models.abstracts.User;
import models.interfaces.QueryInterface;

public class DepartmentCoordinator extends User{
    public DepartmentCoordinator(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality) {
        super(name, cpf, email, password, registration, birthdate, state, nationality);        
    }

    public DepartmentCoordinator(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
    }

    public ArrayList<Department> getDepartments(){
        ArrayList<String> departments_stringified = QueryInterface.where("departments", "department_coordinator_id", Integer.toString(this.getId()));
        ArrayList<Department> response = new ArrayList<>();
        if (departments_stringified != null) {
            departments_stringified.forEach(ds -> {
                Department department = new Department(ds);
                response.add(department);
            });
        }
        return response;
    }
}
