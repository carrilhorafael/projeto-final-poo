package models;

import java.util.ArrayList;

import activerecord.ActiveRecord;

public class Classroom {
    private String code, room;
    private int id;
    private Teacher teacher;
    private Course course;
    private Subject subject;
    private ArrayList<String> errors;
    private static int next_classroom_id = Integer.parseInt(ActiveRecord.last("ids").split(" \\| ")[6]);

    private Classroom (){}

    public Classroom (String[] parameters){
        this.id = Integer.parseInt(parameters[0]);
        this.code = parameters[1];
        this.room = parameters[2];
        this.teacher = Teacher.serialize(ActiveRecord.find("users", Integer.parseInt(parameters[3])));
        this.subject = Subject.serialize(ActiveRecord.find("subjects", Integer.parseInt(parameters[4])));
        this.course = Course.serialize(ActiveRecord.find("courses", Integer.parseInt(parameters[5])));
    }

    public boolean save(){
        if(this.errors.isEmpty()){
            this.id = next_classroom_id;
            next_classroom_id++;
            return ActiveRecord.save("classrooms", this.stringify());
        }else return false;
    }

    public void delete(){
        ActiveRecord.delete("classroom", this.id);
    }

    public static Classroom create(String code, String room, int teacher_id, int subject_id, int course_id){
        Classroom classroom = new Classroom();
        classroom.setCode(code);
        classroom.setRoom(room);
        classroom.setTeacher(teacher_id);
        classroom.setSubject(subject_id);
        classroom.setCourse(course_id);
        return classroom;
    }

    public String stringify(){
        return this.id + " | " + this.code + " | " + this.room + " | " + this.teacher.getId() + " | " + this.subject.getId()+ " | " + this.course.getId();
    }

    public static Classroom serialize(String classroom_stringified){
        if (classroom_stringified == null) return null;
        Classroom classroom = new Classroom(classroom_stringified.split(" \\| "));
        return classroom;
    }

    public static ArrayList<Classroom> arraySerialize(ArrayList<String> classroom_stringifieds){
        ArrayList<Classroom> classrooms = new ArrayList<>();
        classroom_stringifieds.forEach(cs -> {
            Classroom classroom = Classroom.serialize(cs);
            classrooms.add(classroom);
        });
        return classrooms;
    }

    public String getCode() {
        return code;
    }
    public int getId() {
        return id;
    }
    public String getRoom() {
        return room;
    }
    public Subject getSubject() {
        return subject;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public Course getCourse() {
        return course;
    }
    public ArrayList<Student> getStudents(){
        ArrayList<String> subscription_stringifieds = ActiveRecord.where("subscriptions", "classroom_id", Integer.toString(this.id));
        ArrayList<Student> students = new ArrayList<>();
        subscription_stringifieds.forEach(ss -> {
            String student_stringified = ActiveRecord.find("users", Integer.parseInt(ss.split(" \\| ")[1]));
            Student student = Student.serialize(student_stringified);
            students.add(student);
        });
        return students;
    }
    public ArrayList<String> getErrors() {
        return errors;
    }

    // Validators e Setters
    public boolean validateCode(String code){
        boolean response = true;
        if(code.isBlank()){
            this.errors.add("O código da turma não pode ficar em branco.");
            response = false;
        }
        return response;
    }
    public boolean validateRoom(String room){
        boolean response = true;
        if(room.isBlank()){
            this.errors.add("A sala de aula não pode ficar em branco");
            response = false;
        }else {
            if (!room.matches("[0-9]*")){
                this.errors.add("A sala de aula deve ser um número.");
                response = false;
            }
        }
        return response;
    }
    public boolean validateTeacher(Teacher teacher){
        boolean response = true;
        if(teacher == null){
            this.errors.add("Professor deve existir");
            response = false;
        }
        return response;
    }
    public boolean validateSubject(Subject subject){
        boolean response = true;
        if(subject == null){
            this.errors.add("Matéria deve existir");
            response = false;
        }
        return response;
    }
    public boolean validateCourse(Course course){
        boolean response = true;
        if(course == null){
            this.errors.add("Curso deve existir");
            response = false;
        }
        return response;
    }
    public void setCode(String code){
        if(validateCode(code)) this.code = code;
    }
    public void setRoom(String room){
        if(validateRoom(room)) this.room = room;
    }
    public void setTeacher(int teacher_id){
        String user_stringified = ActiveRecord.find("users", teacher_id);
        Teacher teacher = Teacher.serialize(user_stringified);
        if(validateTeacher(teacher)) this.teacher = teacher;
    }
    public void setSubject(int subject_id){
        String subject_stringified = ActiveRecord.find("subjects", subject_id);
        Subject subject = Subject.serialize(subject_stringified);
        if(validateSubject(subject)) this.subject = subject;

    }
    public void setCourse(int course_id) {
        String course_stringified = ActiveRecord.find("courses", course_id);
        Course course = Course.serialize(course_stringified);
        if(validateCourse(course)) this.course = course;
    }


}
