package models.classes;

import models.enumerators.StatusSchoolYear;

public class SchoolYear {
    
    private int year, semester;
    private StatusSchoolYear status;

    public SchoolYear(int year, int semester, StatusSchoolYear status){
        this.year = year;
        this.semester = semester;
        this.status = status;
    }

    // Getters
    public int getSemester() {
        return semester;
    }
    public int getYear() {
        return year;
    }
    public StatusSchoolYear getStatus() {
        return status;
    }

}
