package models.classes;

import models.interfaces.QueryInterface;

public class Subject {
    private String name, ch, knowledge_area;
    private int id, department_id;
    private static String last_subject_id = QueryInterface.last("subjects").split(" \\| ")[0];
    private static int quant_subjects = last_subject_id == null ? 0 : Integer.parseInt(last_subject_id);

    public Subject (String name, String knowledge_area, String ch, int department_id){
        this.name = name;
        this.knowledge_area = knowledge_area;
        this.ch = ch;
        this.department_id = department_id;
        quant_subjects++;
        this.id = quant_subjects;
    }

    public Subject (String subject_stringified){
        String[] parameters = subject_stringified.split(" \\| "); 
        this.id = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.knowledge_area = parameters[2];
        this.ch = parameters[3];
        this.department_id = Integer.parseInt(parameters[4]);
    }
    
    public String stringify(){
        return this.id + " | " + this.name + " | " + this.knowledge_area + " | " + this.ch + " | " + this.department_id;
    }

    public int getId() {
        return id;
    }
    public Department getDepartment() {
        Department department = new Department(QueryInterface.find("departments", department_id));
        return department;
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
