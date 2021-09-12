package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.DepartmentsController;
import models.Department;
import views.ManageUniversityView;

public class DepartmentTableCard extends JPanel{
    public DepartmentTableCard(Department department, ManageUniversityView container){
        super(new FlowLayout(FlowLayout.CENTER));
        JLabel departmentLabel = new JLabel(department.getName()+" | " + department.getKnowledgeArea() + " | " + department.getCode() + " | " + department.getDepartmentCoordinator().getName());
        departmentLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        JButton deletedepartment = new JButton("Deletar departamento");
        deletedepartment.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        deletedepartment.setBackground(new Color(32, 178, 170));
        deletedepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DepartmentsController.destroy(department.getId());
                JOptionPane.showConfirmDialog(null, "Departamento deletado!");
                container.reload();
            }
        });
        this.add(departmentLabel);
        this.add(deletedepartment);
    }
}
