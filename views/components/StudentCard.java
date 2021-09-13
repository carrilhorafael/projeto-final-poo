package views.components;
import java.awt.*;
import javax.swing.*;

import models.Student;
import views.MyCourseFrame;

public class StudentCard extends JPanel{
    public StudentCard(Student student, MyCourseFrame container){
        super(new FlowLayout(FlowLayout.CENTER));
        JLabel studentLabel = new JLabel(student.getName()+ " | Email: " + student.getEmail() + " | Nacionalidade: " + student.getNationality());
        studentLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        this.add(studentLabel);
    }
}
