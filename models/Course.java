package models;

import java.util.ArrayList;

import activerecord.ActiveRecord;

public class Course {
    private String name, knowledge_area, campus, code;
    private int id;
    private CourseCoordinator course_coordinator;
    private ArrayList<String> errors = new ArrayList<>();
    private static int next_course_id = Integer.parseInt(ActiveRecord.last("ids").split(" \\| ")[3]);

    private Course (){}

    private Course (String[] parameters){
        this.id = Integer.parseInt(parameters[0]);
        this.name = parameters[1];
        this.knowledge_area = parameters[2];
        this.campus = parameters[3];
        this.code = parameters[4];
        this.course_coordinator = CourseCoordinator.serialize(ActiveRecord.find("users", Integer.parseInt(parameters[5])));
    }

    public boolean save(){
        if(this.errors.isEmpty()){
            this.id = next_course_id;
            next_course_id++;
            return ActiveRecord.save("courses", this.stringify());
        }else return false;
    }

    public void delete(){
        // this.getStudents()
        ActiveRecord.delete("courses", this.id);
    }

    public static Course serialize(String course_stringified){
        if (course_stringified == null) return null;
        Course course = new Course(course_stringified.split(" \\| "));
        return course;
    }

    public static ArrayList<Course> arraySerialize(ArrayList<String> course_stringifieds){
        ArrayList<Course> courses = new ArrayList<>();
        course_stringifieds.forEach(cs -> {
            Course course = Course.serialize(cs);
            courses.add(course);
        });
        return courses;
    }

    public static Course create(String name, String knowledge_area, String campus, String code, int course_coordinator_id){
        Course course = new Course();
        course.setName(name);
        course.setKnowledgeArea(knowledge_area);
        course.setCampus(campus);
        course.setCode(code);
        course.setCoordinator(course_coordinator_id);
        return course;
    }

    public String stringify(){
        return this.id + " | " + this.name + " | " + this.knowledge_area + " | " + this.campus + " | " + this.code + " | " + this.course_coordinator.getId();
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
        return course_coordinator;
    }
    public ArrayList<Classroom> getClassrooms(){
        ArrayList<String> classroom_stringifieds = ActiveRecord.where("classrooms", "course_id", Integer.toString(this.id));
        ArrayList<Classroom> response = Classroom.arraySerialize(classroom_stringifieds);
        return response;
    }
    public ArrayList<String> getErrors() {
        return errors;
    }


    // Validators e setters
    public boolean validateName(String name){
        boolean response = true;
        if(name.isBlank()){
            this.errors.add("Não é possivel deixar o nome em branco.");
            response = false;
        }
        return response;
    }
    public boolean validateKnowledgeArea(String knowledge_area){
        boolean response = true;
        if(knowledge_area.isBlank()){
            this.errors.add("A area de conhecimento não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateCode(String code){
        boolean response = true;
        if(code.isBlank()){
            this.errors.add("O código não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateCampus(String campus){
        boolean response = true;
        if(campus.isBlank()){
            this.errors.add("O campus não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateCoordinator(String coordinator_stringified){
        boolean response = true;
        if(coordinator_stringified == null){
            this.errors.add("O coordenador não existe");
            response = false;
        }else if(coordinator_stringified.split(" \\| ")[9].equals("CourseCoordinator")){
            this.errors.add("O id informado não pertence à um coordenador de curso");
            response = false;
        }
        return response;
    }
    public void setName(String name){
        if(validateName(name)) this.name = name;
    }
    public void setCode(String code){
        if(validateCode(code)) this.code = code;
    }
    public void setCampus(String campus){
        if(validateCampus(campus)) this.campus = campus;
    }
    public void setKnowledgeArea(String knowledge_area){
        if(validateKnowledgeArea(knowledge_area)) this.knowledge_area = knowledge_area;
    }
    public void setCoordinator(int coordinator_id){
        String coordinator_stringified = ActiveRecord.find("users", coordinator_id);
        if(validateCoordinator(coordinator_stringified)){
            this.course_coordinator = CourseCoordinator.serialize(coordinator_stringified);
        }
    }
}
