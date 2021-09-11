package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Course;
import models.CourseCoordinator;
import models.DepartmentCoordinator;
import models.Student;

public class CoursesController extends ApplicationController{
    public static Course create (String[] parameters){
        if(!raise_permissions("courses::create")) return null;
        Course course = Course.create(
            parameters[0], // String name
            parameters[1], // String knowledge_area
            parameters[2], // String campus
            parameters[3], // String code
            Integer.parseInt(parameters[4]) // int course_coordinator_id
        );
        if (course.save()){
            return course;
        }else{
            course.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return course;
        }
    }

    public static ArrayList <Course> index(){
        if(!raise_permissions("courses::index")) return null;
        ArrayList<String> course_stringifieds = ActiveRecord.all("courses");
        ArrayList<Course> response = Course.arraySerialize(course_stringifieds);
        return response;
    }

    public static Course show(int course_id){
        if(!raise_permissions("courses::show")) return null;
        if(AuthController.getUserLogged() instanceof CourseCoordinator){
            CourseCoordinator user_logged = (CourseCoordinator)AuthController.getUserLogged();
            Course course = user_logged.getCourse();
            return course;
        }else if (AuthController.getUserLogged() instanceof Student){
            Student user_logged = (Student)AuthController.getUserLogged();
            Course course = user_logged.getCourse();
            return course;
        }else{
            Course course = setCourse(course_id);
            return course;
        }
    }

    public static void destroy(int course_id){
        if(!raise_permissions("courses::destroy")) return;
        if (AuthController.getUserLogged() instanceof CourseCoordinator){
            CourseCoordinator user_logged = (CourseCoordinator)AuthController.getUserLogged();
            Course course = user_logged.getCourse();
            course.delete();
        }else {
            Course course = setCourse(course_id);
            course.delete();
        }
    }

    public static Course update(int course_id, String parameter, String value){
        if(!raise_permissions("courses::update")) return null;
        if (AuthController.getUserLogged() instanceof CourseCoordinator){
            CourseCoordinator user_logged = (CourseCoordinator)AuthController.getUserLogged();
            if (ActiveRecord.update("courses", user_logged.getCourse().getId(), parameter, value))
                return user_logged.getCourse();
            return null;
        }
        else {
            if (ActiveRecord.update("courses", course_id, parameter, value)){
                Course course = setCourse(course_id);
                return course;
            }
            return null;
        }
    }

    private static Course setCourse(int course_id){
        String course_stringified = ActiveRecord.find("courses", course_id);
        return Course.serialize(course_stringified);
    }
}
