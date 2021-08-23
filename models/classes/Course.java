package models.classes;

import db.QueryInterface;

public class Course {
    private String id, name, knowledge_area, campus, code, course_coordinator_id;
    private static String last_course_id = QueryInterface.last("courses").split(" \\| ")[0];
    private static int quant_courses = last_course_id == null? 0 : Integer.parseInt(last_course_id);

    public Course (String name, String knowledge_area, String campus, String code, String course_coordinator_id){
        this.name = name;
        this.knowledge_area = knowledge_area;
        this.campus = campus;
        this.code = code;
        this.course_coordinator_id = course_coordinator_id;
        quant_courses++;
        this.id = Integer.toString(quant_courses);
    }

    public Course (String course_stringified){
        String[] parameters = course_stringified.split(" \\| "); 
        this.id = parameters[0];
        this.name = parameters[1];
        this.knowledge_area = parameters[2];
        this.campus = parameters[3];
        this.code = parameters[4];
        this.course_coordinator_id = parameters[5];
    }

    public String stringify(){
        return this.id + " | " + this.name + " | " + this.knowledge_area + " | " + this.campus + " | " + this.code + " | " + this.course_coordinator_id;
    }

    // Getters
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
    public String getCourseCoordinator() {
        return QueryInterface.find("users", course_coordinator_id);
    }
}
