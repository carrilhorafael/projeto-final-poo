package controllers;

import java.util.ArrayList;
import db.QueryInterface;
import models.classes.Course;

public class CourseController {
    public static boolean create (String[] parameters){
        Course course = new Course(parameters[0], parameters[1], parameters[2], parameters[3]);
        return QueryInterface.create("courses", course.stringify());
    }

    public static ArrayList <Course> index(){
        ArrayList<String> course_stringifieds = QueryInterface.all("courses");
        ArrayList<Course> response = new ArrayList<>();
        if (course_stringifieds != null){
            course_stringifieds.forEach(cs -> {
                Course course = new Course(cs);
                response.add(course);
            });
            return response;
        }
        return null;
    }

    public static boolean destroy(Course course){
        boolean response = QueryInterface.delete("courses", course.stringify());
        return response;
    }
}
