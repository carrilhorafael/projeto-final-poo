package controllers;

import java.util.ArrayList;

import models.classes.Department;
import models.interfaces.QueryInterface;

public class DepartmentsController {
    public static boolean create (String[] parameters){
        Department department = new Department(parameters[0], parameters[1], parameters[2], parameters[3], Integer.parseInt(parameters[4]));
        return QueryInterface.save("departments", department.stringify());
    }

    public static ArrayList <Department> index(){
        ArrayList<String> department_stringifieds = QueryInterface.all("departments");
        ArrayList<Department> response = new ArrayList<>();
        if (department_stringifieds != null){
            department_stringifieds.forEach(ds -> {
                Department department = new Department(ds);
                response.add(department);
            });
            return response;
        }
        return null;
    }

    public static boolean destroy(Department department){
        return QueryInterface.delete("departments", department.stringify());
    }
}
