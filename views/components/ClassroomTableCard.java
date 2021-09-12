package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.ClassroomsController;
import models.Classroom;
import views.MyCourseView;

public class ClassroomTableCard extends JPanel{
    public ClassroomTableCard(Classroom classroom, MyCourseView container){
        super(new FlowLayout(FlowLayout.CENTER));

        JLabel classroomLabel = new JLabel(classroom.getCode() + " | Matéria: " + classroom.getSubject().getName() + " | Professor: " + classroom.getTeacher().getName() + " | Inscrições: " + classroom.getSubscriptions().size());
        classroomLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deleteClassroomBtn = new JButton("Deletar curso");
        deleteClassroomBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deleteClassroomBtn.setBackground(new Color(32, 178, 170));
        deleteClassroomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClassroomsController.destroy(classroom.getId());
                JOptionPane.showConfirmDialog(null, "Turma deletada!");
                container.reload();
            }
        });
        this.add(classroomLabel);
        this.add(deleteClassroomBtn);
    }
}
