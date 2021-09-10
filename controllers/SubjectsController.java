package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Subject;

public class SubjectsController extends ApplicationController {
    public static Subject create (String[] parameters){
        if(!raise_permitions("subjects::create")) return null;
        Subject subject = Subject.create(
            parameters[0], // String name
            Integer.parseInt(parameters[1]), // int ch
            Integer.parseInt(parameters[2]), // int department_id
            Integer.parseInt(parameters[3])  // int school_year_id
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
        if(!raise_permitions("subjects::index")) return null;
        ArrayList<String> subject_stringifieds = ActiveRecord.all("subjects");
        ArrayList<Subject> response = Subject.arraySerialize(subject_stringifieds);
        return response;
    }

    public static Subject show(int subject_id){
        if(!raise_permitions("subjects::show")) return null;
        Subject response = setSubject(subject_id);
        return response;
    }

    public static void destroy(int subject_id){
        if(!raise_permitions("subjects::destroy")) return;
        Subject subject = setSubject(subject_id);
        subject.delete();
    }

    public static Subject update(int subject_id, String parameter, String value){
        if(!raise_permitions("subjects::update")) return null;
        if (ActiveRecord.update("subjects", subject_id, parameter, value)){
            Subject subject = setSubject(subject_id);
            return subject;
        }
        return null;
    }

    private static Subject setSubject(int subject_id){
        String subject_stringified = ActiveRecord.find("subjects", subject_id);
        return Subject.serialize(subject_stringified);
    }
}
