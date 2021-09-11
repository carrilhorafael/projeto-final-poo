package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.Classroom;
import models.CourseCoordinator;
import models.Student;
import models.Subscription;
import models.Teacher;

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
        CourseCoordinator user_logged = (CourseCoordinator)AuthController.getUserLogged();
        Classroom classroom = setClassroom(classroom_id);
        if(classroom.getCourse().equals(user_logged.getCourse())){
            if (ActiveRecord.update("classrooms", classroom_id, parameter, value)){
                classroom = setClassroom(classroom_id);
                return classroom;
            }
        }
        return null;
    }

    public static ArrayList <Classroom> index(){
        if(!raise_permissions("classrooms::index")) return null;
        if(AuthController.getUserLogged() instanceof CourseCoordinator){
            CourseCoordinator user_logged = (CourseCoordinator)AuthController.getUserLogged();
            ArrayList<Classroom> response = user_logged.getCourse().getClassrooms();
            return response;
        }else{
            Teacher user_logged = (Teacher)AuthController.getUserLogged();
            ArrayList<Classroom> response = user_logged.getClassrooms();
            return response;
        }
    }

    public static void destroy(int classroom_id){
        if(!raise_permissions("classrooms::destroy")) return;
        CourseCoordinator user_logged = (CourseCoordinator)AuthController.getUserLogged();
        Classroom classroom = setClassroom(classroom_id);
        if(classroom.getCourse().equals(user_logged.getCourse()))
            classroom.delete();
    }

    public static Subscription subscribe(int classroom_id){
        if(!raise_permissions("classrooms::subscribe")) return null;
        Student user_logged = (Student)AuthController.getUserLogged();
        Subscription subscription = Subscription.create(
            user_logged.getId(), // int student_id
            classroom_id  // int classroom_id
        );
        if (subscription.save()){
            return subscription;
        }else{
            subscription.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return subscription;
        }
    }

    public static boolean evaluate(int subscription_id, String avaliation, int grade){
        if(!raise_permissions("classrooms::evaluate")) return false;
        Teacher user_logged = (Teacher) AuthController.getUserLogged();
        Subscription subscription = setSubscription(subscription_id);
        Classroom classroom = subscription.getClassroom();
        if(classroom.getTeacher().equals(user_logged))
            return ActiveRecord.update("subscriptions", subscription.getId(), avaliation, Integer.toString(grade));
        return false;
    }

    private static Classroom setClassroom(int classroom_id){
        String classroom_stringified = ActiveRecord.find("classrooms", classroom_id);
        return Classroom.serialize(classroom_stringified);
    }
    private static Subscription setSubscription(int subscription_id){
        String subscription_stringified = ActiveRecord.find("subscription", subscription_id);
        return Subscription.serialize(subscription_stringified);
    }
}
