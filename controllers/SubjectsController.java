package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.DepartmentCoordinator;
import models.SchoolYear;
import models.Subject;
import models.Teacher;

public class SubjectsController extends ApplicationController {
    public static Subject create (String[] parameters){
        if(!raise_permissions("subjects::create")) return null;
        int department_id = ((DepartmentCoordinator)AuthController.getUserLogged()).getDepartment().getId();
        int planning_school_year_id = SchoolYear.serialize(ActiveRecord.find_by("schoolyears", "status", "Planejamento")).getId();
        Subject subject = Subject.create(
            parameters[0], // String name
            Integer.parseInt(parameters[1]), // int ch
            department_id, // int department_id
            planning_school_year_id  // int school_year_id
        );
        if (subject.save()){
            return subject;
        }else{
            subject.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return subject;
        }
    }

    public static ArrayList <Subject> index(){
        if (!raise_permissions("subjects::index")) return null;
        if (AuthController.getUserLogged() instanceof DepartmentCoordinator){
            DepartmentCoordinator user_logged = (DepartmentCoordinator)AuthController.getUserLogged();
            ArrayList<Subject> response = user_logged.getDepartment().getSubjects();
            return response;
        }else if(AuthController.getUserLogged() instanceof Teacher){
            Teacher user_logged = (Teacher)AuthController.getUserLogged();
            ArrayList<Subject> response = user_logged.getDepartment().getSubjects();
            return response;
        }else {
            ArrayList<String> subject_stringifieds = ActiveRecord.all("subjects");
            ArrayList<Subject> response = Subject.arraySerialize(subject_stringifieds);
            return response;
        }
    }

    public static Subject show(int subject_id){
        if(!raise_permissions("subjects::show")) return null;
        Subject response = setSubject(subject_id);
        return response;
    }

    public static void destroy(int subject_id){
        if(!raise_permissions("subjects::destroy")) return;
        Subject subject = setSubject(subject_id);
        DepartmentCoordinator user_logged = (DepartmentCoordinator)AuthController.getUserLogged();
        if(user_logged.getDepartment().getId() == subject.getDepartment().getId())
        subject.delete();

    }

    public static Subject update(int subject_id, String parameter, String value){
        if(!raise_permissions("subjects::update")) return null;
        Subject subject = setSubject(subject_id);
        DepartmentCoordinator user_logged = (DepartmentCoordinator)AuthController.getUserLogged();
        if(user_logged.getDepartment().equals(subject.getDepartment())){
            if (ActiveRecord.update("subjects", subject_id, parameter, value)){
                subject = setSubject(subject_id);
                return subject;
            }
        }
        return null;
    }

    private static Subject setSubject(int subject_id){
        String subject_stringified = ActiveRecord.find("subjects", subject_id);
        return Subject.serialize(subject_stringified);
    }
}
