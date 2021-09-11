package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Department;

public class DepartmentsController extends ApplicationController{
    public static Department create (String[] parameters){
        if(!raise_permitions("departments::create")) return null;
        Department department = Department.create(parameters[0], parameters[1], parameters[2], parameters[3], Integer.parseInt(parameters[4]));
        if (department.save()){
            return department;
        }else{
            department.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return department;
        }
    }

    public static ArrayList <Department> index(){
        if(!raise_permissions("departments::index")) return null;
        ArrayList<String> department_stringifieds = ActiveRecord.all("departments");
        ArrayList<Department> response = Department.arraySerialize(department_stringifieds);
        return response;
    }

    public static Department show(int department_id){
        if(!raise_permitions("departments::show")) return null;
        Department response = setDepartment(department_id);
        return response;
    }

    public static void destroy(int department_id){
        if(!raise_permitions("departments::destroy")) return;
        Department department = setDepartment(department_id);
        department.delete();
    }

    public static Department update(int department_id, String parameter, String value){
        if(!raise_permitions("departments::update")) return null;
        if (ActiveRecord.update("departments", department_id, parameter, value)){
            Department department = setDepartment(department_id);
            return department;
        }
        return null;
    }

    private static Department setDepartment(int department_id){
        String department_stringified = ActiveRecord.find("departments", department_id);
        return Department.serialize(department_stringified);
    }

}
