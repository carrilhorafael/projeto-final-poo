package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Classroom;
import models.CourseCoordinator;

public class ClassroomsController extends ApplicationController{
    public static Classroom create (String[] parameters){
        if(!raise_permissions("classrooms::create")) return null;
        Classroom classroom = Classroom.create(
            parameters[0], // String code
            parameters[1], // String room
            Integer.parseInt(parameters[2]), // int teacher_id
            Integer.parseInt(parameters[3]),  // int subject_id
            Integer.parseInt(parameters[4])  // int course_id
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
        if(!raise_permissions("classrooms::show")) return null;
        Classroom classroom = setClassroom(classroom_id);
        return classroom;
    }

    public static Classroom update(int classroom_id, String parameter, String value){
        if(!raise_permissions("classrooms::update")) return null;
        if (ActiveRecord.update("classrooms", classroom_id, parameter, value)){
            Classroom classroom = setClassroom(classroom_id);
            return classroom;
        }
        return null;
    }

    public static ArrayList <Classroom> index(){
        if(!raise_permissions("classrooms::index")) return null;
        CourseCoordinator user_logged = (CourseCoordinator)AuthController.getUserLogged();
        ArrayList<Classroom> response = user_logged.getCourse().getClassrooms();
        return response;
    }

    public static void destroy(int classroom_id){
        if(!raise_permissions("classrooms::destroy")) return;
        Classroom classroom = setClassroom(classroom_id);
        classroom.delete();
    }

    private static Classroom setClassroom(int classroom_id){
        String classroom_stringified = ActiveRecord.find("classrooms", classroom_id);
        return Classroom.serialize(classroom_stringified);
    }
}
