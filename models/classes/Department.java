package models.classes;

import java.util.ArrayList;

import models.interfaces.QueryInterface;

public class Department {
    private String name, knowledge_area, campus, code;
    private int id, department_coordinator_id;
    private static int next_department_id = Integer.parseInt(QueryInterface.last("ids").split(" \\| ")[2]);

    public Department (String name, String knowledge_area, String campus, String code, int department_coordinator_id){
        this.name = name;
        this.knowledge_area = knowledge_area;
        this.campus = campus;
        this.code = code;
        this.department_coordinator_id = department_coordinator_id;
        this.id = next_department_id;
        next_department_id++;
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
    public DepartmentCoordinator getDepartmentCoordinator() {
        String[] parameters = QueryInterface.find("users", department_coordinator_id).split(" \\| ");
        DepartmentCoordinator department_coordinator = new DepartmentCoordinator(parameters);
        return department_coordinator;
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
    public ArrayList<Teacher> getTeachers(){
        ArrayList<String> teachers_ids = QueryInterface.where("teachersdepartments", "department_id", Integer.toString(this.getId()));
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers_ids.forEach(ti -> {
            String teacher_stringified = QueryInterface.find("users", Integer.parseInt(ti.split(" \\| ")[0]));
            Teacher teacher = new Teacher(teacher_stringified.split(" \\| "));
            teachers.add(teacher);
        });
        return teachers;
    }
}
