package views.components;
import java.awt.*;
import javax.swing.*;

import models.Student;
import views.MyCourseView;

public class StudentTableCard extends JPanel{
    public StudentTableCard(Student student, MyCourseView container){
        super(new FlowLayout(FlowLayout.CENTER));
        JLabel studentLabel = new JLabel(student.getName()+ " | Email: " + student.getEmail() + " | Nacionalidade: " + student.getNationality());
        studentLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        // JButton deleteSubjectBtn = new JButton("Deletar matéria");
        // deleteSubjectBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        // deleteSubjectBtn.setBackground(new Color(32, 178, 170));
        // deleteSubjectBtn.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         Object[] options = { "Confirmar", "Cancelar" };
        //         int response = JOptionPane.showOptionDialog(null, "Clique confirmar para continuar", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //         if(response == 0){
        //             SubjectsController.destroy(teacher.getId());
        //         }
        //         // container.reload();
        //     }
        // });
        // JButton createClassroomBtn = new JButton("Criar turma");
        // createClassroomBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        // createClassroomBtn.setBackground(new Color(32, 178, 170));
        // createClassroomBtn.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         // new CreateClassroomView();
        //         container.dispose();
        //     }
        // });
        this.add(studentLabel);
        // this.add(deleteSubjectBtn);
        // this.add(createClassroomBtn);
    }
}
