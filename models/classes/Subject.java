package models.classes;

import models.interfaces.QueryInterface;

public class Subject {
    private String name, ch, knowledge_area;
    private int id, department_id, school_year_id;
    private static String last_subject = QueryInterface.last("subjects");
    private static int next_subject_id = last_subject == null ? 1 : Integer.parseInt(last_subject.split(" \\| ")[0]) + 1;

    public Subject (String name, String knowledge_area, String ch, int department_id, int school_year_id){
        this.name = name;
        this.knowledge_area = knowledge_area;
        this.ch = ch;
        this.department_id = department_id;
        this.school_year_id = school_year_id;
        this.id = next_subject_id;
        next_subject_id++;
    }

    public Subject (String subject_stringified){
        String[] parameters = subject_stringified.split(" \\| "); 
        this.id = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.knowledge_area = parameters[2];
        this.ch = parameters[3];
        this.department_id = Integer.parseInt(parameters[4]);
        this.school_year_id = Integer.parseInt(parameters[5]);
    }
    
    public String stringify(){
        return this.id + " | " + this.name + " | " + this.knowledge_area + " | " + this.ch + " | " + this.department_id + " | " + this.school_year_id;
    }

    public int getId() {
        return id;
    }
    public Department getDepartment() {
        Department department = new Department(QueryInterface.find("departments", department_id));
        return department;
    }
    public SchoolYear getSchoolYear() {
        SchoolYear school_year = new SchoolYear(QueryInterface.find("schoolyears", school_year_id));
        return school_year;
    }
    public String getCh() {
        return ch;
    }
    public String getKnowledgeArea() {
        return knowledge_area;
    }
    public String getName() {
        return name;
    }
}
