package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Department;
import models.DepartmentCoordinator;
import models.Teacher;

public class DepartmentsController extends ApplicationController{
    public static Department create (String[] parameters){
        if(!raise_permissions("departments::create")) return null;
        Department department = Department.create(
            parameters[0], // String name
            parameters[1], // String knowledge_area
            parameters[2], // String campus
            parameters[3], // String code
            Integer.parseInt(parameters[4]) // int course_coordinator_id
        );
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
        if(!raise_permissions("departments::show")) return null;
        if(AuthController.getUserLogged() instanceof DepartmentCoordinator){
            DepartmentCoordinator user_logged = (DepartmentCoordinator)AuthController.getUserLogged();
            Department department = user_logged.getDepartment();
            return department;
        }else if(AuthController.getUserLogged() instanceof Teacher){
            Teacher user_logged = (Teacher)AuthController.getUserLogged();
            Department department = user_logged.getDepartment();
            return department;
        }else{
            Department response = setDepartment(department_id);
            return response;
        }
    }

    public static void destroy(int department_id){
        if(!raise_permissions("departments::destroy")) return;
        if (AuthController.getUserLogged() instanceof DepartmentCoordinator){
            DepartmentCoordinator user_logged = (DepartmentCoordinator)AuthController.getUserLogged();
            Department department = user_logged.getDepartment();
            department.delete();
        }else {
            Department department = setDepartment(department_id);
            department.delete();
        }
    }

    public static Department update(int department_id, String parameter, String value){
        if(!raise_permissions("departments::update")) return null;
        if (AuthController.getUserLogged() instanceof DepartmentCoordinator){
            DepartmentCoordinator user_logged = (DepartmentCoordinator)AuthController.getUserLogged();
            if(ActiveRecord.update("departments", user_logged.getDepartment().getId(), parameter, value))
                return user_logged.getDepartment();
            return null;
        }else{
            if (ActiveRecord.update("departments", department_id, parameter, value)){
                Department department = setDepartment(department_id);
                return department;
            }
            return null;
        }
    }

    private static Department setDepartment(int department_id){
        String department_stringified = ActiveRecord.find("departments", department_id);
        return Department.serialize(department_stringified);
    }

}
