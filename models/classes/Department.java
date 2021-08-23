package models.classes;

import db.QueryInterface;

public class Department {
    private String name, knowledge_area, campus, code;
    private int id, department_coordinator_id;
    private static String last_department_id = QueryInterface.last("departments").split(" \\| ")[0];
    private static int quant_departments = last_department_id == null ? 0 : Integer.parseInt(last_department_id);

    public Department (String name, String knowledge_area, String campus, String code, int department_coordinator_id){
        this.name = name;
        this.knowledge_area = knowledge_area;
        this.campus = campus;
        this.code = code;
        this.department_coordinator_id = department_coordinator_id;
        quant_departments++;
        this.id = quant_departments;
    }

    public Department (String department_stringified){
        String[] parameters = department_stringified.split(" \\| "); 
        this.id = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.knowledge_area = parameters[2];
        this.campus = parameters[3];
        this.code = parameters[4];
        this.department_coordinator_id = Integer.parseInt(parameters[5]);
    }
    
    public String stringify(){
        return this.id + " | " + this.name + " | " + this.knowledge_area + " | " + this.campus + " | " + this.code + " | " + this.department_coordinator_id;
    }

    public String getCampus() {
        return campus;
    }
    public String getCode() {
        return code;
    }
    public String getDepartmentCoordinator() {
        return QueryInterface.find("users", Integer.toString(department_coordinator_id));
    }
    public int getId() {
        return id;
    }
    public String getKnowledgeArea() {
        return knowledge_area;
    }
    public String getName() {
        return name;
    }
}
