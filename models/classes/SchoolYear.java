package models.classes;

public class SchoolYear {    
    private String year, semester, status;

    public SchoolYear(String year, String semester, String status){
        this.year = year;
        this.semester = semester;
        this.status = status;
    }

    public SchoolYear(String sy_stringified){
        String[] parameters = sy_stringified.split(" \\| ");
        this.year = parameters[0];
        this.semester = parameters[1];
        this.status = parameters[2];
    }

    public String stringify(){
        return this.year + " | " + this.semester + " | " + this.status;
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

}
