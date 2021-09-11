package models;

import java.util.ArrayList;

import activerecord.ActiveRecord;

public class SchoolYear {
    private String year, semester, status;
    private int id;
    private ArrayList<String> errors = new ArrayList<>();
    private static int next_school_year_id = Integer.parseInt(ActiveRecord.last("ids").split(" \\| ")[5]);

    private SchoolYear(){}

    private SchoolYear(String[] parameters){
        this.id = Integer.parseInt(parameters[0]);
        this.year = parameters[1];
        this.semester = parameters[2];
        this.status = parameters[3];
    }

    public String stringify(){
        return this.id + " | " + this.year + " | " + this.semester + " | " + this.status;
    }

    public boolean save(){
        if(this.errors.isEmpty()){
            this.id = next_school_year_id;
            next_school_year_id++;
            return ActiveRecord.save("schoolyears", this.stringify());
        }else return false;
    }

    public void delete(){
        ActiveRecord.delete("schoolyears", this.id);
    }

    public static SchoolYear create(String year, String semester, String status){
        SchoolYear school_year = new SchoolYear();
        school_year.setYear(year);
        school_year.setSemester(semester);
        school_year.setStatus(status);
        school_year.verifyUniqueness();
        return school_year;
    }

    public static SchoolYear serialize(String school_year_stringified){
        if(school_year_stringified == null) return null;
        SchoolYear school_year = new SchoolYear(school_year_stringified.split(" \\| "));
        return school_year;
    }

    public static ArrayList<SchoolYear> arraySerialize(ArrayList<String> school_year_stringifieds){
        ArrayList<SchoolYear> school_years = new ArrayList<>();
        school_year_stringifieds.forEach(sy -> {
            SchoolYear school_year = SchoolYear.serialize(sy);
            school_years.add(school_year);
        });
        return school_years;
    }

    // Getters
    public String getSemester() {
        return semester;
    }
    public String getYear() {
        return year;
    }
    public String getStatus() {
        return status;
    }
    public int getId() {
        return id;
    }
    public ArrayList<String> getErrors() {
        return errors;
    }

    // Setters
    public boolean validateSemester(String semester){
        boolean response = true;
        if(semester.isBlank()){
            this.errors.add("O semestre do periodo letivo não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateYear(String year){
        boolean response = true;
        if(year.isBlank()){
            this.errors.add("O ano do periodo letivo não pode ficar em branco");
            response = false;
        }
        return response;
    }
    public boolean validateStatus(String status){
        boolean response = true;
        if(status.isBlank()){
            this.errors.add("O status do período letivo não pode ficar em branco.");
            response = false;
        }else {
            if(!(status.equals("Planejamento") || status.equals("Ativo") || status.equals("Fechado") || status.equals("Inscrições"))){
                this.errors.add("Essa opção não existe para status do periodo letivo");
                response = false;
            }else if(status.equals("Planejamento") && (ActiveRecord.find_by("schoolyears", "status", "Planejamento") != null)){
                this.errors.add("Já existe um ano letivo em planejamento");
                response = false;
            }
        }
        return response;
    }
    public void verifyUniqueness(){
        ArrayList<SchoolYear> school_years = SchoolYear.arraySerialize(ActiveRecord.where("schoolyears", "year", this.year));
        school_years.forEach(sy -> {
            if(sy.getSemester().equals(this.semester)) this.errors.add("O ano letivo já existe.");
        });
    }
    public void setSemester(String semester) {
        if(validateSemester(semester)) this.semester = semester;
    }
    public void setStatus(String status) {
        if(validateStatus(status)) this.status = status;
    }
    public void setYear(String year) {
        if(validateYear(year)) this.year = year;
    }

}
