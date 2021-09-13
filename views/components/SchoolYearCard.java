package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.SchoolYearsController;
import models.SchoolYear;
import views.SchoolYearsFrame;

public class SchoolYearCard extends JPanel{
    public SchoolYearCard(SchoolYear schoolYear, SchoolYearsFrame container){
        super(new FlowLayout(FlowLayout.TRAILING));
        JLabel syLabel = new JLabel(schoolYear.getYear()+"."+schoolYear.getSemester() + " (" + schoolYear.getStatus() + ")");
        syLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deleteSy = new JButton("Deletar " + schoolYear.getYear() + "." + schoolYear.getSemester());
        deleteSy.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deleteSy.setBackground(new Color(32, 178, 170));
        deleteSy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Confirmar", "Cancelar" };
                int response = JOptionPane.showOptionDialog(null, "Apagar o periodo letivo irá apagar todas as matérias e turmas vinculadas a ele. Confirma que quer apagar?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(response == 0){
                    SchoolYearsController.destroy(schoolYear.getId());
                    container.reload();
                }
            }
        });
        this.add(syLabel);
        this.add(deleteSy);
    }
}
