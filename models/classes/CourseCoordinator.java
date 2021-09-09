package models.classes;

import java.util.ArrayList;

import models.abstracts.User;
import models.interfaces.ActiveRecord;

public class CourseCoordinator extends User {
    public CourseCoordinator() {
        super();
    }

    public CourseCoordinator(String[] parameters){
        super(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], parameters[6], parameters[7], parameters[8]);
    }

    public static CourseCoordinator create(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        CourseCoordinator coordinator = new CourseCoordinator();
        coordinator.setName(name);
        coordinator.setCpf(cpf);
        coordinator.setEmail(email);
        coordinator.setPassword(password);
        coordinator.setRegistration(registration);
        coordinator.setBirthdate(birthdate);
        coordinator.setState(state);
        coordinator.setNationality(nationality);
        return coordinator;
    }

    public static CourseCoordinator serialize(String coordinator_stringified){
        CourseCoordinator coordinator = new CourseCoordinator(coordinator_stringified.split(" \\| "));
        return coordinator;
    }

    public void delete(){
        ActiveRecord.delete("users", this.getId());
    }
    
    public Course getCourse(){
        String course_stringified = ActiveRecord.find_by("courses", "course_coordinator_id", Integer.toString(this.getId()));
        Course response = Course.serialize(course_stringified);
        return response;
    }
}
