package controllers;

import java.util.ArrayList;

import activerecord.ActiveRecord;
import models.SchoolYear;

public class SchoolYearsController extends ApplicationController{
    public static SchoolYear create (String[] parameters){
        if(!raise_permitions("schoolyears::create")) return null;
        SchoolYear schoolyear = SchoolYear.create(parameters[0], parameters[1], parameters[2]);
        if (schoolyear.save()){
            return schoolyear;
        }else{
            schoolyear.getErrors().forEach(error -> {
                System.out.println("(422)" + error);
            });
            return schoolyear;
        }
    }

    public static ArrayList <SchoolYear> index(){
        if(!raise_permitions("schoolyears::index")) return null;
        ArrayList<String> schoolyear_stringifieds = ActiveRecord.all("schoolyears");
        ArrayList<SchoolYear> response = SchoolYear.arraySerialize(schoolyear_stringifieds);
        return response;
    }

    public static SchoolYear show(int schoolyear_id){
        if(!raise_permitions("schoolyears::show")) return null;
        SchoolYear response = setSchoolYear(schoolyear_id);
        return response;
    }

    public static void destroy(int schoolyear_id){
        if(!raise_permitions("schoolyears::destroy")) return;
        SchoolYear schoolyear = setSchoolYear(schoolyear_id);
        schoolyear.delete();
    }

    public static SchoolYear update(int schoolyear_id, String parameter, String value){
        if(!raise_permitions("schoolyears::update")) return null;
        if (ActiveRecord.update("schoolyears", schoolyear_id, parameter, value)){
            SchoolYear schoolyear = setSchoolYear(schoolyear_id);
            return schoolyear;
        }
        return null;
    }

    private static SchoolYear setSchoolYear(int schoolyear_id){
        String schoolyear_stringified = ActiveRecord.find("schoolyears", schoolyear_id);
        return SchoolYear.serialize(schoolyear_stringified);
    }
}
