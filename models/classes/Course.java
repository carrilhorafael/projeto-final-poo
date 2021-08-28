package models.classes;

import models.interfaces.QueryInterface;

public class Course {
    private String name, knowledge_area, campus, code;
    private int id, course_coordinator_id;
    private static int next_course_id = Integer.parseInt(QueryInterface.last("ids").split(" \\| ")[3]);

    public Course (String name, String knowledge_area, String campus, String code, int course_coordinator_id){
        this.name = name;
        this.knowledge_area = knowledge_area;
        this.campus = campus;
        this.code = code;
        this.course_coordinator_id = course_coordinator_id;
        this.id = next_course_id;
        next_course_id++;
    }

    public Course (String course_stringified){
        String[] parameters = course_stringified.split(" \\| "); 
        this.id = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.knowledge_area = parameters[2];
        this.campus = parameters[3];
        this.code = parameters[4];
        this.course_coordinator_id = Integer.parseInt(parameters[5]);
    }

    public String stringify(){
        return this.id + " | " + this.name + " | " + this.knowledge_area + " | " + this.campus + " | " + this.code + " | " + this.course_coordinator_id;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getCampus() {
        return campus;
    }
    public String getCode() {
        return code;
    }
    public String getKnowledgeArea() {
        return knowledge_area;
    }
    public String getName() {
        return name;
    }
    public CourseCoordinator getCourseCoordinator() {
        String[] parameters = QueryInterface.find("users", course_coordinator_id).split(" \\| "); 
        CourseCoordinator course_coordinator = new CourseCoordinator(parameters);
        return course_coordinator;
    }
}
