package controllers;

import java.util.ArrayList;

import models.classes.Course;
import models.interfaces.QueryInterface;

public class CoursesController {
    public static boolean create (String[] parameters){
        Course course = new Course(parameters[0], parameters[1], parameters[2], parameters[3], Integer.parseInt(parameters[4]));
        return QueryInterface.save("courses", course.stringify());
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

    public static boolean destroy(int course_id){
        return QueryInterface.delete("courses", course_id);
    }
}
