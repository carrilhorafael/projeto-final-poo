package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.CoursesController;
import models.Course;

public class CourseTableCard extends JPanel{
    public CourseTableCard(Course course){
        super(new FlowLayout(FlowLayout.CENTER));

        JLabel courseLabel = new JLabel(course.getName()+" | " + course.getKnowledgeArea() + " | " + course.getCode() + " | " + course.getCourseCoordinator().getName());
        courseLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deleteCourse = new JButton("Deletar curso");
        deleteCourse.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deleteCourse.setBackground(new Color(32, 178, 170));
        deleteCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CoursesController.destroy(course.getId());
                JOptionPane.showConfirmDialog(null, "Curso deletado!");
            }
        });
        this.add(courseLabel);
        this.add(deleteCourse);
    }
}
