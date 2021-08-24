package controllers;

import java.util.ArrayList;

import models.classes.SchoolYear;
import models.interfaces.QueryInterface;

public class SchoolYearsController {
    public static boolean create (String year, String semester, String status){
        SchoolYear new_year = new SchoolYear(year, semester, status);
        return QueryInterface.save("schoolyears", new_year.stringify());
    }

    public static ArrayList <SchoolYear> index(){
        ArrayList<String> sy_stringifieds = QueryInterface.all("schoolyears");
        ArrayList<SchoolYear> response = new ArrayList<>();
        if (sy_stringifieds != null){
            sy_stringifieds.forEach(sy -> {
                SchoolYear school_year = new SchoolYear(sy);
                response.add(school_year);
            });
            return response;
        }
        return null;
    }

    public static boolean destroy(SchoolYear schoolYear){
        boolean response = QueryInterface.delete("schoolyears", schoolYear.stringify());
        return response;
    }
}
