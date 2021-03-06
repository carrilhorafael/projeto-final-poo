package controllers;

import models.CourseCoordinator;
import models.DepartmentCoordinator;
import models.Manager;
import models.Student;
import models.Teacher;

public class ApplicationController {
    private final static String[] manager_routes = {
        "schoolyears::create",
        "schoolyears::index",
        "schoolyears::show",
        "schoolyears::destroy",
        "schoolyears::update",
        "courses::create",
        "courses::index",
        "courses::show",
        "courses::destroy",
        "courses::update",
        "coursecoordinators::register",
        "departments::create",
        "departments::index",
        "departments::show",
        "departments::destroy",
        "departments::update",
        "departmentcoordinators::register"
    };
    private final static String[] dep_coordinator_routes = {
        "departments::show",
        "departments::destroy",
        "departments::update",
        "subjects::create",
        "subjects::index",
        "subjects::show",
        "subjects::destroy",
        "subjects::update",
        "teachers::register",
        "schoolyears::planning"
    };
    private final static String[] course_coordinator_routes = {
        "courses::show",
        "courses::destroy",
        "courses::update",
        "subjects::index",
        "classrooms::create",
        "classrooms::index",
        "classrooms::show",
        "classrooms::destroy",
        "classrooms::update",
        "students::register",
    };
    private final static String[] teacher_routes = {
        "departments::show",
        "classrooms::index",
        "classrooms::show",
        "classrooms::update",
        "classrooms::evaluate",
        "subjects::index",
        "subjects::show",

    };
    private final static String[] student_routes = {
        "courses::show",
        "classrooms::index",
        "classrooms::show",
        "classrooms::subscribe",
        "subjects::index",
        "subjects::show",
    };
    public static boolean raise_permissions(String route){
        if (AuthController.getUserLogged() == null) return false;
        else if(AuthController.getUserLogged() instanceof Manager){
            for (String i : manager_routes) if (i.equals(route)) return true;
            return false;
        }
        else if(AuthController.getUserLogged() instanceof DepartmentCoordinator){
            for (String allowed_route : dep_coordinator_routes) if (route.equals(allowed_route)) return true;
            return false;
        }
        else if(AuthController.getUserLogged() instanceof CourseCoordinator){
            for (String allowed_route : course_coordinator_routes) if (route.equals(allowed_route)) return true;
            return false;
        }
        else if(AuthController.getUserLogged() instanceof Teacher){
            for (String allowed_route : teacher_routes) if (route.equals(allowed_route)) return true;
            return false;
        }
        else if(AuthController.getUserLogged() instanceof Student){
            for (String allowed_route : student_routes) if (route.equals(allowed_route)) return true;
            return false;
        }
        else return false;
    }
}
