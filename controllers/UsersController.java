package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.CourseCoordinator;
import models.DepartmentCoordinator;
import models.Manager;
import models.Student;
import models.Teacher;
import models.abstracts.User;

public class UsersController extends ApplicationController{
    public static User register(String[] parameters, int kind){
        if (kind == 1){
            return createManager(parameters);
        }else if (kind == 2){
            if (raise_permitions("departmentcoordinators::register")) return null;
            return createDepartmentCoordinator(parameters);
        }else if (kind == 3){
            if (raise_permitions("coursecoordinators::register")) return null;
            return createCourseCoordinator(parameters);
        }else if (kind == 4){
            if (raise_permitions("teachers::register")) return null;
            return createTeacher(parameters);
        }else if (kind == 5){
            if (raise_permitions("student::register")) return null;
            return createStudent(parameters);
        }
        else{
            return null;
        }
    }


    // public static ArrayList <User> index(){
    //     ArrayList<String> users_stringifieds = ActiveRecord.all("users");
    //     ArrayList<User> response = new ArrayList<>();
    //     if (users_stringifieds != null){
    //         users_stringifieds.forEach(cs -> {
    //             User user = new User(cs);
    //             response.add(user);
    //         });
    //         return response;
    //     }
    //     return null;
    // }

    public static boolean destroy(int user_id){
        return ActiveRecord.delete("classrooms", user_id);
    }

    private static Student createStudent(String[] parameters){
        Student student = Student.create(
            parameters[0], // String name
            parameters[1], // String cpf
            parameters[2], // String email
            parameters[3], // String password
            parameters[4], // String registration
            parameters[5], // String birthdate
            parameters[6], // String state
            parameters[7],  // String nationality
            Integer.parseInt(parameters[8]) // int course_id
        );
        if (student.save()){
            String students_course = student.getId() + " | " + student.getCourse().getId();
            ActiveRecord.save("studentscourses", students_course);
            return student;
        }else{
            student.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return student;
        }
    }

    private static Teacher createTeacher(String[] parameters){
        Teacher teacher = Teacher.create(
            parameters[0], // String name
            parameters[1], // String cpf
            parameters[2], // String email
            parameters[3], // String password
            parameters[4], // String registration
            parameters[5], // String birthdate
            parameters[6], // String state
            parameters[7],  // String nationality
            Integer.parseInt(parameters[8]) // int department_id
        );
        if (teacher.save()){
            String teachers_department = teacher.getId() + " | " + teacher.getDepartment().getId();
            ActiveRecord.save("teachersdepartments", teachers_department);
            return teacher;
        }else{
            teacher.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return teacher;
        }
    }

    private static CourseCoordinator createCourseCoordinator(String[] parameters) {
        CourseCoordinator course_coordinator = CourseCoordinator.create(
            parameters[0], // String name
            parameters[1], // String cpf
            parameters[2], // String email
            parameters[3], // String password
            parameters[4], // String registration
            parameters[5], // String birthdate
            parameters[6], // String state
            parameters[7]  // String nationality
        );
        if (course_coordinator.save()){
            return course_coordinator;
        }else{
            course_coordinator.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return course_coordinator;
        }

    }

    private static DepartmentCoordinator createDepartmentCoordinator(String[] parameters) {
        DepartmentCoordinator department_coordinator = DepartmentCoordinator.create(
            parameters[0], // String name
            parameters[1], // String cpf
            parameters[2], // String email
            parameters[3], // String password
            parameters[4], // String registration
            parameters[5], // String birthdate
            parameters[6], // String state
            parameters[7]  // String nationality
        );
        if (department_coordinator.save()){
            return department_coordinator;
        }else{
            department_coordinator.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return department_coordinator;
        }
    }

    private static Manager createManager(String[] parameters){
        Manager manager = Manager.create(
            parameters[0], // String name
            parameters[1], // String cpf
            parameters[2], // String email
            parameters[3], // String password
            parameters[4], // String registration
            parameters[5], // String birthdate
            parameters[6], // String state
            parameters[7]  // String nationality
        );
        if (manager.save()){
            return manager;
        }else{
            manager.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return manager;
        }
    }

}
