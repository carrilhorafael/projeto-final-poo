package models;

import java.util.ArrayList;

import activerecord.ActiveRecord;

public class Department {
    private String name, knowledge_area, campus, code;
    private int id;
    private DepartmentCoordinator department_coordinator;
    private ArrayList<String> errors;
    private static int next_department_id = Integer.parseInt(ActiveRecord.last("ids").split(" \\| ")[2]);

    private Department (){}

    private Department (String[] parameters){
        this.id = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.knowledge_area = parameters[2];
        this.campus = parameters[3];
        this.code = parameters[4];
        this.department_coordinator = DepartmentCoordinator.serialize(ActiveRecord.find("users", Integer.parseInt(parameters[5])));
    }

    public String stringify(){
        return this.id + " | " + this.name + " | " + this.knowledge_area + " | " + this.campus + " | " + this.code + " | " + this.department_coordinator.getId();
    }

    public boolean save(){
        if(this.errors.isEmpty()){
            this.id = next_department_id;
            next_department_id++;
            return ActiveRecord.save("departments", this.stringify());
        }else return false;
    }

    public void delete(){
        this.getTeachers().forEach(teacher -> teacher.delete());
        this.getSubjects().forEach(subject -> subject.delete());
        ActiveRecord.delete("departments", this.id);
    }

    public static Department create(String name, String knowledge_area, String campus, String code, int department_coordinator_id){
        Department department = new Department();
        department.setName(name);
        department.setCampus(campus);
        department.setCode(code);
        department.setKnowledgeArea(knowledge_area);
        department.setCoordinator(department_coordinator_id);
        return department;
    }
    public static Department serialize(String department_stringified){
        Department department = new Department(department_stringified.split(" \\| "));
        return department;
    }
    public static ArrayList<Department> arraySerialize(ArrayList<String> department_stringifieds){
        ArrayList<Department> departments = new ArrayList<>();
        department_stringifieds.forEach(ds -> {
            Department department = Department.serialize(ds);
            departments.add(department);
        });
        return departments;
    }

    public String getCampus() {
        return campus;
    }
    public String getCode() {
        return code;
    }
    public DepartmentCoordinator getDepartmentCoordinator() {
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
        ArrayList<String> teachers_ids = ActiveRecord.where("teachersdepartments", "department_id", Integer.toString(this.getId()));
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers_ids.forEach(ti -> {
            String teacher_stringified = ActiveRecord.find("users", Integer.parseInt(ti.split(" \\| ")[0]));
            Teacher teacher = Teacher.serialize(teacher_stringified);
            teachers.add(teacher);
        });
        return teachers;
    }
    public ArrayList<Subject> getSubjects(){
        ArrayList<String> subject_stringifieds = ActiveRecord.where("subjects", "department_id", Integer.toString(this.id));
        return Subject.arraySerialize(subject_stringifieds);
    }
    public ArrayList<String> getErrors() {
        return errors;
    }

    // Validators e Setters
    public boolean validateName(String name){
        boolean response = true;
        if(name.isBlank()){
            this.errors.add("O nome do departamento não pode ficar em branco.");
            response = false;
        }
        return response;
    }
    public boolean validateCampus(String campus){
        boolean response = true;
        if(campus.isBlank()){
            this.errors.add("O campus não pode ficar em branco.");
            response = false;
        }
        return response;
    }
    public boolean validateCode(String code){
        boolean response = true;
        if(code.isBlank()){
            this.errors.add("O código do departamento não pode ficar em branco.");
            response = false;
        }
        return response;
    }
    public boolean validateKnowledgeArea(String knowledge_area){
        boolean response = true;
        if(knowledge_area.isBlank()){
            this.errors.add("A area de conhecimento do curso não pode ficar em branco.");
            response = false;
        }
        return response;
    }
    public boolean validateCoordinator(String coordinator_stringified){
        boolean response = true;
        if(coordinator_stringified == null){
            this.errors.add("Coordenador deve existir");
            response = false;
        }else if(coordinator_stringified.split(" \\| ")[9].equals("DepartmentCoordinator")){
            this.errors.add("O id informado não pertence a um coordenador de departamento");
            response = false;
        }
        return response;
    }
    public void setName(String name){
        if(validateName(name)) this.name = name;
    }
    public void setCampus(String campus){
        if(validateCampus(campus)) this.campus = campus;
    }
    public void setCode(String code){
        if(validateCode(code)) this.code = code;
    }
    public void setKnowledgeArea(String knowledge_area){
        if(validateKnowledgeArea(knowledge_area)) this.knowledge_area = knowledge_area;
    }
    public void setCoordinator(int coordinator_id){
        String coordinator_stringified = ActiveRecord.find("users", coordinator_id);
        if(validateCoordinator(coordinator_stringified)) this.department_coordinator = DepartmentCoordinator.serialize(coordinator_stringified);
    }
}
