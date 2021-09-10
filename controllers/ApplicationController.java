package controllers;

public class ApplicationController {
    private final static String[] manager_routes = {
        "users::register",
        "courses::create",
        "courses::index",
        "courses::show",
        "courses::destroy",
        "courses::update",
        "department::create",
        "department::index",
        "department::show",
        "department::destroy",
        "department::update",
    };
    private final static String[] dep_coordinator_routes = {
        "users::register",
        "department::show",
        "department::destroy",
        "department::update",
        "department::update",
        "subject::create",
        "subject::index",
        "subject::show",
        "subject::destroy",
        "subject::update",
    };
    private final static String[] course_coordinator_routes = {
        "users::register",
        "course::show",
        "course::destroy",
        "course::update",
        "course::update",
        "classroom::create",
        "classroom::index",
        "classroom::show",
        "classroom::destroy",
        "classroom::update",
    };
    public static boolean raise_permitions(String route){

        return true;
    }
}
