package models.classes;

import models.interfaces.QueryInterface;

public class Classroom {
    private String code, room;
    private int id, teacher_id, subject_id;
    private static String last_classroom = QueryInterface.last("classrooms");
    private static int next_classroom_id = last_classroom == null ? 1 : Integer.parseInt(last_classroom_id.split(" \\| ")[0]) + 1;
    
    public Classroom (String code, String room, int teacher_id, int subject_id){
        this.code = code;
        this.room = room;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.id = next_classroom_id;
        next_classroom_id++;
    }

    public Classroom (String classroom_stringified){
        String[] parameters = classroom_stringified.split(" \\| "); 
        this.id = Integer.parseInt(parameters[0]);
        this.code = parameters[1];
        this.room = parameters[2];
        this.teacher_id = Integer.parseInt(parameters[3]);
        this.subject_id = Integer.parseInt(parameters[4]);
    }
    
    public String stringify(){
        return this.id + " | " + this.code + " | " + this.room + " | " + this.teacher_id + " | " + this.subject_id;
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
        Subject subject = new Subject(QueryInterface.find("subjects", subject_id));
        return subject;
    }
    public String getTeacher() {
        return QueryInterface.find("users", teacher_id);
    }
}
