package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Classroom;
import models.CourseCoordinator;

public class ClassroomsController extends ApplicationController{
    public static Classroom create (String[] parameters){
        if(!raise_permitions("classrooms::create")) return null;
        Classroom classroom = Classroom.create(
            parameters[0], // String code
            parameters[1], // String room
            Integer.parseInt(parameters[2]), // int teacher_id
            Integer.parseInt(parameters[3])  // int subject_id
        );
        if (classroom.save()){
            return classroom;
        }else{
            classroom.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return classroom;
        }
    }

    public static Classroom show(int classroom_id){
        if(!raise_permitions("classrooms::show")) return null;
        Classroom classroom = setClassroom(classroom_id);
        return classroom;
    }

    public static Classroom update(int classroom_id, String parameter, String value){
        if(!raise_permitions("classrooms::update")) return null;
        if (ActiveRecord.update("classrooms", classroom_id, parameter, value)){
            Classroom classroom = setClassroom(classroom_id);
            return classroom;
        }
        return null;
    }

    public static ArrayList <Classroom> index(){
        if(!raise_permitions("classrooms::index")) return null;
        String course_id_stringified = Integer.toString(((CourseCoordinator)AuthController.user_logged).getCourse().getId());
        ArrayList<String> classroom_stringifieds = ActiveRecord.where("classrooms", "course_id", course_id_stringified);
        ArrayList<Classroom> response = Classroom.arraySerialize(classroom_stringifieds);
        return response;
    }

    public static void destroy(int classroom_id){
        if(!raise_permitions("classrooms::destroy")) return;
        Classroom classroom = setClassroom(classroom_id);
        classroom.delete();
    }

    private static Classroom setClassroom(int classroom_id){
        String classroom_stringified = ActiveRecord.find("classrooms", classroom_id);
        return Classroom.serialize(classroom_stringified);
    }
}
