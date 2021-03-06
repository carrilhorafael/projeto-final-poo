package views.components;
import java.awt.*;
import javax.swing.*;

import models.Teacher;
import views.MyDepartmentFrame;

public class TeacherCard extends JPanel{
    public TeacherCard(Teacher teacher, MyDepartmentFrame container){
        super(new FlowLayout(FlowLayout.CENTER));
        JLabel teacherLabel = new JLabel(teacher.getName()+ " | Email: " + teacher.getEmail() + " | Nacionalidade: " + teacher.getNationality());
        teacherLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        this.add(teacherLabel);
    }
}
