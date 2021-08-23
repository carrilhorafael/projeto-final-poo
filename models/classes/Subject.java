package models.classes;

import db.QueryInterface;

public class Subject {
    private String id, name, ch, knowledge_area, department_id;

    public String getId() {
        return id;
    }
    public Department getDepartment() {
        Department department = new Department(QueryInterface.find("courses", department_id));
        return department;
    }
    public String getCh() {
        return ch;
    }
    public String getKnowledgeArea() {
        return knowledge_area;
    }
    public String getName() {
        return name;
    }
}
