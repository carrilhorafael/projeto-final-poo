package models.classes;

import models.interfaces.QueryInterface;

public class SchoolYear {    
    private String year, semester, status;
    private int id;
    private static String last_school_year = QueryInterface.last("schoolyears");
    private static int next_school_year_id = last_school_year == null ? 1 : Integer.parseInt(last_school_year.split(" \\| ")[0]) + 1;

    public SchoolYear(String year, String semester, String status){
        this.year = year;
        this.semester = semester;
        this.status = status;
        this.id = next_school_year_id;
        next_school_year_id++;
    }

    public SchoolYear(String sy_stringified){
        String[] parameters = sy_stringified.split(" \\| ");
        this.id = Integer.parseInt(parameters[0]);
        this.year = parameters[1];
        this.semester = parameters[2];
        this.status = parameters[3];
    }

    public String stringify(){
        return this.id + " | " + this.year + " | " + this.semester + " | " + this.status;
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

    // Setters
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setYear(String year) {
        this.year = year;
    }

}
