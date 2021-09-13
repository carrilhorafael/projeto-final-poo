package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controllers.AuthController;
import controllers.SchoolYearsController;
import models.Department;
import models.DepartmentCoordinator;
import models.SchoolYear;
import views.components.HeaderComponent;
import views.components.SubjectCard;
import views.components.TeacherCard;

public class MyDepartmentFrame extends JFrame{
	FlowLayout mainLayout = new FlowLayout();
	Department department = ((DepartmentCoordinator)AuthController.getUserLogged()).getDepartment();
	public MyDepartmentFrame() {
		super("Meu departamento");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );

        initialize(this);

        this.pack();
        this.setVisible(true);
	}

	public void reload(){
		new MyDepartmentFrame();
		this.dispose();
	}

	private void initialize(JFrame container) {
		container.getContentPane().add(new HeaderComponent(container), BorderLayout.PAGE_END);

		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

		JPanel subjectsPanel = new JPanel();
		BoxLayout subjectsLayout = new BoxLayout(subjectsPanel, BoxLayout.Y_AXIS);
		subjectsPanel.setLayout(subjectsLayout);

		JLabel myDepartmentTitle = new JLabel("Meu departamento");
		myDepartmentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		myDepartmentTitle.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		subjectsPanel.add(myDepartmentTitle);

		JLabel departmentLabel = new JLabel("Nome do meu departamento: " + department.getName());
		departmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		departmentLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		subjectsPanel.add(departmentLabel);
		if(department.getSubjects().isEmpty()){
			JLabel subjectsTitle = new JLabel("Ainda não há matérias cadastradas para o departamento");
			subjectsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			subjectsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			subjectsPanel.add(subjectsTitle);
		}else{
			JLabel subjectsTitle = new JLabel("Matérias cadastradas para o Departamento: ");
			subjectsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			subjectsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			subjectsPanel.add(subjectsTitle);
			department.getSubjects().forEach(subject -> {
				subjectsPanel.add(new SubjectCard(subject, this));
			});
		}


		SchoolYear planningSchoolYear = SchoolYearsController.planning();
		JButton createSubjectBtn = new JButton("Criar matéria para o departamento");
		// createSubjectBtn.setForeground(Color.WHITE);
		if(planningSchoolYear == null) createSubjectBtn.setEnabled(false);
		createSubjectBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createSubjectBtn.setBackground(new Color(32, 178, 170));
		createSubjectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new CreateSubjectFrame(department.getId(), planningSchoolYear);
				container.dispose();
			}
		});
		subjectsPanel.add(createSubjectBtn);

		JPanel teachersPanel = new JPanel();
		BoxLayout teachersLayout = new BoxLayout(teachersPanel, BoxLayout.Y_AXIS);
		teachersPanel.setLayout(teachersLayout);

		if(department.getTeachers().isEmpty()){
			JLabel teachersTitle = new JLabel("Ainda não há professores cadastrados para o departamento");
			teachersTitle.setHorizontalAlignment(SwingConstants.CENTER);
			teachersTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			teachersPanel.add(teachersTitle);
		}else{
			JLabel teachersTitle = new JLabel("Professores cadastrados para o Departamento: ");
			teachersTitle.setHorizontalAlignment(SwingConstants.CENTER);
			teachersTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			teachersPanel.add(teachersTitle);
			department.getTeachers().forEach(teacher -> {
				teachersPanel.add(new TeacherCard(teacher, this));
			});
		}

		JButton createTeacherBtn = new JButton("Criar professor para o departamento");
		// createTeacherBtn.setForeground(Color.WHITE);
		createTeacherBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createTeacherBtn.setBackground(new Color(32, 178, 170));
		createTeacherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateTeacherFrame(department.getId());
				container.dispose();
			}
		});
		teachersPanel.add(createTeacherBtn);

		main.add(subjectsPanel, BorderLayout.WEST);
		main.add(teachersPanel, BorderLayout.EAST);

		container.getContentPane().add(main);
	}

}
