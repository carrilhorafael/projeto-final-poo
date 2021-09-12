package models;

import java.util.ArrayList;

import activerecord.ActiveRecord;

public class Subject {
    private String name;
    private int id, ch;
    private Department department;
    private SchoolYear school_year;
    private ArrayList<String> errors = new ArrayList<>();
    private static int next_subject_id = Integer.parseInt(ActiveRecord.last("ids").split(" \\| ")[4]);

    private Subject (){
    }

    private Subject (String[] parameters){
        this.id = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.ch = Integer.parseInt(parameters[2]);
        String department_stringified = ActiveRecord.find("departments", Integer.parseInt(parameters[3]));
        String school_year_stringified = ActiveRecord.find("schoolyears", Integer.parseInt(parameters[4]));
        this.department =  Department.serialize(department_stringified);
        this.school_year = SchoolYear.serialize(school_year_stringified);
    }

    public String stringify(){
        return this.id + " | " + this.name + " | " + this.ch + " | " + this.department.getId() + " | " + this.school_year.getId();
    }

    public boolean save(){
        if (this.errors.isEmpty()){
            this.id = next_subject_id;
            next_subject_id++;
            return ActiveRecord.save("subjects", this.stringify());
        } return false;
    }

    public void delete(){
        this.getClassrooms().forEach(classroom -> classroom.delete());
        ActiveRecord.delete("subjects", this.id);
    }

    public static Subject create(String name, int ch, int department_id, int school_year_id){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCh(ch);
        subject.setDepartment(department_id);
        subject.setSchoolYear(school_year_id);
        return subject;
    }

    public static Subject serialize(String subject_stringified){
        if(subject_stringified == null) return null;
        Subject subject = new Subject(subject_stringified.split(" \\| "));
        return subject;
    }

    public static ArrayList<Subject> arraySerialize(ArrayList<String> subject_stringifieds){
        ArrayList<Subject> subjects = new ArrayList<>();
        subject_stringifieds.forEach(ss -> {
            Subject subject = Subject.serialize(ss);
            subjects.add(subject);
        });
        return subjects;
    }

    public int getId() {
        return id;
    }
    public Department getDepartment() {
        return department;
    }
    public SchoolYear getSchoolYear() {
        return school_year;
    }
    public int getCh() {
        return ch;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getErrors() {
        return errors;
    }
    public ArrayList<Classroom> getClassrooms(){
        ArrayList<String> classroom_stringifieds = ActiveRecord.where("classrooms", "subject_id", this.id+"");
        ArrayList<Classroom> classrooms = Classroom.arraySerialize(classroom_stringifieds);
        return classrooms;
    }

    // Setters e validators
    public boolean validateName(String name){
        boolean response = true;
        if(name.isBlank()){
            this.errors.add("O nome da matéria não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateCh(int ch){
        boolean response = true;
        if(ch != 32 && ch != 64){
            this.errors.add("A carga horaria deve ser 32 ou 64 horas");
            response = false;
        }
        return response;
    }
    public boolean validateSchoolYear(SchoolYear school_year){
        boolean response = true;
        if(school_year == null){
            this.errors.add("Ano letivo deve existir");
            response = false;
        }else if(!school_year.getStatus().equals("Planejamento")){
            this.errors.add("Você só pode criar matérias para anos letivos em planejamento");
            response = false;
        }
        return response;
    }
    public boolean validateDepartment(Department department){
        boolean response = true;
        if(department == null){
            this.errors.add("Departamento deve existir");
            response = false;
        }
        return response;
    }
    public void setName(String name){
        if(validateName(name)) this.name = name;
    }
    public void setCh(int ch){
        if(validateCh(ch)) this.ch = ch;
    }
    public void setSchoolYear(int school_year_id){
        String school_year_stringified = ActiveRecord.find("schoolyears", school_year_id);
        SchoolYear school_year = SchoolYear.serialize(school_year_stringified);
        if(validateSchoolYear(school_year)) this.school_year = school_year;
    }
    public void setDepartment(int department_id){
        String department_stringified = ActiveRecord.find("departments", department_id);
        Department department = Department.serialize(department_stringified);
        if(validateDepartment(department)) this.department = department;
    }

}
