package views.TerminalViews;

import java.util.ArrayList;
import java.util.Scanner;

import controllers.SchoolYearsController;
import models.SchoolYear;

public class SchoolYearsTerminalView {
    private final static String div_string = "|| ------------------------------------------------------------------------------------------------------------------------------------------ ||";
    private static Scanner teclado = new Scanner(System.in);

    public static void show(){
        System.out.println(div_string);
        System.out.println("Você está na página de Anos Letivos.");
        System.out.println("Atualmente os seguintes Anos Letivos estão cadastrados: ");
        ArrayList<SchoolYear> school_years = SchoolYearsController.index();
        school_years.forEach(schoolyear -> {
            System.out.println(schoolyear.getId() + " -> " + schoolyear.getYear() + "." + schoolyear.getSemester() + " - " + schoolyear.getStatus());
        });
    }
    public static void create(){
        System.out.println(div_string);
        System.out.println("Criar um ano letivo. ");
        System.out.print("Digite o ano: ");
        String year = teclado.next();
        System.out.print("Digite o semestre: ");
        String semester = teclado.next();
        System.out.print("Digite o status: ");
        String status = teclado.next();
        String[] parameters = {year, semester, status};
        SchoolYearsController.create(parameters);
    }

    public static void delete(){
        System.out.println(div_string);
        System.out.println("Delete um ano letivo.");
        System.out.print("Digite o id do ano letivo: ");
        int school_year_id = teclado.nextInt();
        SchoolYearsController.destroy(school_year_id);
    }
}
