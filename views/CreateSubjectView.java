package views;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controllers.SubjectsController;
import models.SchoolYear;
import models.Subject;
import views.components.Header;

public class CreateSubjectView extends JFrame{
	FlowLayout mainLayout = new FlowLayout();
    public CreateSubjectView(int departmentId, SchoolYear schoolYearId){
        super("Criar matéria");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        initialize(this, departmentId, schoolYearId);
        this.pack();
        this.setVisible(true);
    }
    public void initialize(JFrame container, int departmentId, SchoolYear schoolYear){
        container.add(new Header(this), BorderLayout.PAGE_END);

		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

        JPanel formSection = new JPanel();
        BoxLayout formlayout = new BoxLayout(formSection, BoxLayout.Y_AXIS);
        formSection.setLayout(formlayout);
        formSection.setAlignmentY(CENTER_ALIGNMENT);

        JLabel subjectTitle = new JLabel("Criar matéria para o ano letivo em planejamento.");
        subjectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        subjectTitle.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
        formSection.add(subjectTitle);

        JLabel schoolYearTitle = new JLabel("Ano letivo em planejamento: " + schoolYear.getYear() + "." + schoolYear.getSemester());
        schoolYearTitle.setHorizontalAlignment(SwingConstants.CENTER);
        schoolYearTitle.setFont(new Font("Fira Code", Font.PLAIN, 16));
        formSection.add(schoolYearTitle);

        JLabel lblName = new JLabel("Nome da matéria:");
        lblName.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        formSection.add(lblName);

        JTextField name = new JTextField();
        lblName.setLabelFor(name);
        name.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        name.setColumns(10);
        formSection.add(name);

        JLabel lblCh = new JLabel("Carga horário da matéria:");
        lblCh.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        formSection.add(lblCh);

        JTextField ch = new JTextField();
        lblCh.setLabelFor(ch);
        ch.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        ch.setColumns(10);
        formSection.add(ch);

        JButton createSubjectBtn = new JButton("Criar nova matéria");
        createSubjectBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        createSubjectBtn.setBackground(new Color(32, 178, 170));
        createSubjectBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String[] parameters = {
                    name.getText(),
                    ch.getText(),
                    departmentId + "",
                    schoolYear.getId() + ""
                };
                Subject created = SubjectsController.create(parameters);
                if(created == null){
                    JOptionPane.showMessageDialog(null, "Erro: Permissão negada");
                }else if(created.getErrors().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Criado com sucesso");
                    new MyDepartmentView();
                    container.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Erro: " + created.getErrors().get(0));
                }
            }
        });
        formSection.add(createSubjectBtn);

        main.add(formSection);
        this.getContentPane().add(main);

    }
}
