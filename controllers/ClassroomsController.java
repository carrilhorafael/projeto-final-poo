package controllers;

import java.util.ArrayList;

import models.classes.Classroom;
import models.interfaces.QueryInterface;

public class ClassroomsController {
    public static boolean create (String[] parameters){
        Classroom classroom = new Classroom(parameters[0], parameters[1], Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
        return QueryInterface.save("classroom", classroom.stringify());
    }

    public static ArrayList <Classroom> index(){
        ArrayList<String> classroom_stringifieds = QueryInterface.all("classrooms");
        ArrayList<Classroom> response = new ArrayList<>();
        if (classroom_stringifieds != null){
            classroom_stringifieds.forEach(cs -> {
                Classroom classroom = new Classroom(cs);
                response.add(classroom);
            });
            return response;
        }
        return null;
    }

    public static boolean destroy(Classroom classroom){
        return QueryInterface.delete("classrooms", classroom.stringify());
    }
}
