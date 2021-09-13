package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.CoursesController;
import models.Course;
import views.ManageUniversityFrame;

public class CourseCard extends JPanel{
    public CourseCard(Course course, ManageUniversityFrame container){
        super(new FlowLayout(FlowLayout.CENTER));

        JLabel courseLabel = new JLabel(course.getName()+" | " + course.getKnowledgeArea() + " | " + course.getCode() + " | " + course.getCourseCoordinator().getName());
        courseLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deleteCourse = new JButton("Deletar curso");
        deleteCourse.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deleteCourse.setBackground(new Color(32, 178, 170));
        deleteCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Confirmar", "Cancelar" };
                int response = JOptionPane.showOptionDialog(null, "Deletar o curso irá apagar todas as turmas e alunos vinculados a ele. Confirma que quer deletar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(response == 0){
                    CoursesController.destroy(course.getId());
                    container.reload();
                }
            }
        });
        this.add(courseLabel);
        this.add(deleteCourse);
    }
}
