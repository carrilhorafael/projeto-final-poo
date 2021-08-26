package controllers;

import java.util.ArrayList;

import models.classes.Subject;
import models.interfaces.QueryInterface;

public class SubjectsController {
    public static boolean create (String[] parameters){
        Subject subject = new Subject(parameters[0], parameters[1], parameters[2], Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
        return QueryInterface.save("subjects", subject.stringify());
    }

    public static ArrayList <Subject> index(){
        ArrayList<String> subject_stringifieds = QueryInterface.all("subjects");
        ArrayList<Subject> response = new ArrayList<>();
        if (subject_stringifieds != null){
            subject_stringifieds.forEach(cs -> {
                Subject subject = new Subject(cs);
                response.add(subject);
            });
            return response;
        }
        return null;
    }

    public static boolean destroy(int subject_id){
        return QueryInterface.delete("subjects", subject_id);
    }
}
