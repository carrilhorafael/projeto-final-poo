package models.classes;

import models.abstracts.User;
import models.interfaces.QueryInterface;

public class Teacher extends User {
    private Department department;
    public Teacher(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality, String department_id) {
        super(name, cpf, email, password, registration, birthdate, state, nationality);        
        this.department = new Department(QueryInterface.find("departments", Integer.parseInt(department_id))); 
    }

    public Teacher(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
        int department_id = Integer.parseInt(QueryInterface.find_by("teachersdepartments", "teacher_id", parameters[0]).split(" \\| ")[2]);
        this.department = new Department(QueryInterface.find("departments", department_id));
    }

    public Department getDepartment() {
        return department;
    }
}
