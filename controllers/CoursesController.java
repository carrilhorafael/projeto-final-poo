package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Course;

public class CoursesController extends ApplicationController{
    public static Course create (String[] parameters){
        if(!raise_permitions("courses::create")) return null;
        Course course = Course.create(parameters[0], parameters[1], parameters[2], parameters[3], Integer.parseInt(parameters[4]));
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
        if(!raise_permitions("courses::index")) return null;
        ArrayList<String> course_stringifieds = ActiveRecord.all("courses");
        ArrayList<Course> response = Course.arraySerialize(course_stringifieds);
        return response;
    }

    public static Course show(int course_id){
        if(!raise_permitions("courses::show")) return null;
        Course response = setCourse(course_id);
        return response;
    }

    public static void destroy(int course_id){
        if(!raise_permitions("courses::destroy")) return;
        Course course = setCourse(course_id);
        course.delete();
    }

    public static Course update(int course_id, String parameter, String value){
        if(!raise_permitions("courses::update")) return null;
        if (ActiveRecord.update("courses", course_id, parameter, value)){
            Course course = setCourse(course_id);
            return course;
        }
        return null;
    }

    private static Course setCourse(int course_id){
        String course_stringified = ActiveRecord.find("courses", course_id);
        return Course.serialize(course_stringified);
    }
}
