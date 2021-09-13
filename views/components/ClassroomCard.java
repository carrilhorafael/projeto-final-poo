package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.ClassroomsController;
import models.Classroom;
import views.MyCourseFrame;

public class ClassroomCard extends JPanel{
    public ClassroomCard(Classroom classroom, MyCourseFrame container){
        super(new FlowLayout(FlowLayout.CENTER));

        JLabel classroomLabel = new JLabel(classroom.getCode() + " | Matéria: " + classroom.getSubject().getName() + " | Professor: " + classroom.getTeacher().getName() + " | Inscrições: " + classroom.getSubscriptions().size());
        classroomLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deleteClassroomBtn = new JButton("Deletar curso");
        deleteClassroomBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deleteClassroomBtn.setBackground(new Color(32, 178, 170));
        deleteClassroomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Confirmar", "Cancelar" };
                int response = JOptionPane.showOptionDialog(null, "Confirma que quer deletar essa turma e todas as suas inscrições?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(response == 0){
                    ClassroomsController.destroy(classroom.getId());
                    container.reload();
                }
            }
        });
        this.add(classroomLabel);
        this.add(deleteClassroomBtn);
    }
}
