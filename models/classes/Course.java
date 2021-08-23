package models.classes;

public class Course {
    private String name, knowledge_area, campus, code;

    public Course (String name, String knowledge_area, String campus, String code){
        this.name = name;
        this.knowledge_area = knowledge_area;
        this.campus = campus;
        this.code = code;
    }

    public Course (String course_stringified){
        String[] parameters = course_stringified.split(" \\| "); 
        this.name = parameters[0];
        this.knowledge_area = parameters[1];
        this.campus = parameters[2];
    this.code = parameters[3];
    }

    public String stringify(){
        return this.name + " | " + this.knowledge_area + " | " + this.campus + " | " + this.code;
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
}
