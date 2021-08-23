package controllers;

import java.util.ArrayList;

import models.classes.Course;
import models.interfaces.QueryInterface;

public class CourseController {
    public static boolean create (String name, String knowledge_area, String campus, String code, int course_coordinator_id){
        Course course = new Course(name, knowledge_area, campus, code, course_coordinator_id);
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

    public static boolean destroy(Course course){
        boolean response = QueryInterface.delete("courses", course.stringify());
        return response;
    }
}
