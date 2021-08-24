package models.classes;

import java.util.ArrayList;

import models.abstracts.User;
import models.interfaces.QueryInterface;

public class CourseCoordinator extends User {
    public CourseCoordinator(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality) {
        super(name, cpf, email, password, registration, birthdate, state, nationality);        
    }

    public CourseCoordinator(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
    }

    public ArrayList<Course> getCourses(){
        ArrayList<String> courses_stringified = QueryInterface.where("courses", "course_coordinator_id", Integer.toString(this.getId()));
        ArrayList<Course> response = new ArrayList<>();
        if (courses_stringified != null) {
            courses_stringified.forEach(cs -> {
                Course course = new Course(cs);
                response.add(course);
            });
        }
        return response;
    }
}
