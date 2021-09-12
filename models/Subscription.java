package models;

import java.text.DecimalFormat;
import java.util.ArrayList;

import activerecord.ActiveRecord;

public class Subscription {
    private Classroom classroom;
    private Student student;
    private Double n1, n2, n3;
    private int id;
    private ArrayList<String> errors = new ArrayList<>();
    private static int next_subscription_id = Integer.parseInt(ActiveRecord.last("ids").split(" \\| ")[7]);

    private Subscription(){}

    private Subscription(String[] parameters){}

    public static Subscription create(int student_id, int classroom_id){
        Subscription subscription = new Subscription();
        subscription.setStudent(student_id);
        subscription.setClassroom(classroom_id);
        subscription.setGrade("n1", 0.0);
        subscription.setGrade("n2", 0.0);
        subscription.setGrade("n3", 0.0);
        subscription.verifySchoolYearStatus();
        return subscription;
    }
    public boolean save(){
        if(this.errors.isEmpty()){
            this.id = next_subscription_id;
            next_subscription_id++;
            return ActiveRecord.save("subscriptions", this.stringify());
        } else return false;
    }

    public void delete(){
        ActiveRecord.delete("subscriptions", this.id);
    }

    public String stringify(){
        return this.id + " | " + this.student.getId() + " | " + this.classroom.getId() + " | " + this.n1 + " | " + this.n2 + " | " + this.n3;
    }

    public static Subscription serialize(String subscription_stringified){
        if(subscription_stringified == null) return null;
        Subscription subscription = new Subscription(subscription_stringified.split(" \\| "));
        return subscription;
    }

    public static ArrayList<Subscription> arraySerialize(ArrayList<String> subscription_stringifieds){
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        subscription_stringifieds.forEach(ss -> {
            Subscription subscription = Subscription.serialize(ss);
            subscriptions.add(subscription);
        });
        return subscriptions;
    }


    // Getters
    public int getId() {
        return id;
    }
    public Double getN1() {
        return n1;
    }
    public Double getN2() {
        return n2;
    }
    public Double getN3() {
        return n3;
    }
    public Classroom getClassroom() {
        return classroom;
    }
    public Student getStudent() {
        return student;
    }
    public ArrayList<String> getErrors() {
        return errors;
    }
    public Double getMedia(){
        return Double.valueOf(new DecimalFormat("#,##0.00").format((this.n1 + this.n2 + this.n3) / 3));
    }

    // Setters e Validators
    public void verifySchoolYearStatus(){
        String planning_school_year = ActiveRecord.find_by("schoolyears", "status", "Planejamento");
        SchoolYear school_year = SchoolYear.serialize(planning_school_year);
        if(school_year == null){
            this.errors.add("Não existe um ano letivo em planejamento");
        }
    }

    public boolean validateStudent(Student student){
        boolean response = true;
        if (student == null){
            this.errors.add("Aluno não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateGrade(Double grade){
        boolean response = true;
        if (grade <= 0.0 && grade > 10.0){
            this.errors.add("Esse não é um valor possivel para notas");
            response = false;
        }
        return response;
    }
    public void setStudent(int student_id){
        String student_stringified = ActiveRecord.find("users", student_id);
        Student student = Student.serialize(student_stringified);
        if(validateStudent(student)) this.student = student;
    }
    public boolean validateClassroom(Classroom classroom){
        boolean response = true;
        if (classroom == null){
            this.errors.add("Aluno não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public void setClassroom(int classroom_id){
        String classroom_stringified = ActiveRecord.find("users", classroom_id);
        Classroom classroom = Classroom.serialize(classroom_stringified);
        if(validateClassroom(classroom)) this.classroom = classroom;
    }
    public void setGrade(String node, Double grade){
        if(validateGrade(grade)){
            if(node == "n1"){
                this.n1 = Double.valueOf(new DecimalFormat("#,##0.00").format(this.n1));
            }else if(node == "n2"){
                this.n2 = Double.valueOf(new DecimalFormat("#,##0.00").format(this.n2));
            }else{
                this.n3 = Double.valueOf(new DecimalFormat("#,##0.00").format(this.n3));
            }
        }
    }
}
