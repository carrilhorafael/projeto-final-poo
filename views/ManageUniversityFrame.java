package views;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.CoursesController;
import controllers.DepartmentsController;
import models.Course;
import models.Department;
import views.components.CourseCard;
import views.components.DepartmentCard;
import views.components.HeaderComponent;

public class ManageUniversityFrame extends JFrame{
	JPanel departmentsSection, coursesSection;
	FlowLayout mainLayout = new FlowLayout();

	public ManageUniversityFrame() {
		super("Gerenciamento da universidade");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );

        initialize(this);

        this.pack();
        this.setVisible(true);
	}

	public void reload(){
		new ManageUniversityFrame();
		this.dispose();
	}

	private void initialize(final JFrame container) {

		container.add(new HeaderComponent(this), BorderLayout.PAGE_END);

		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

		JLabel title = new JLabel("Página de gestão de universidade");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		container.add(title, BorderLayout.PAGE_START);

		departmentsSection = new JPanel();
		BoxLayout departmentslayout = new BoxLayout(departmentsSection, BoxLayout.Y_AXIS);
		departmentsSection.setLayout(departmentslayout);
		departmentsSection.setAlignmentY(CENTER_ALIGNMENT);


		ArrayList<Department> departments = DepartmentsController.index();
		if(departments.isEmpty()){
			JLabel listTitle = new JLabel("Ainda não há departamentos cadastrados.");
			listTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			departmentsSection.add(listTitle);
		}
		else{
			JLabel listTitle = new JLabel("Atualmente, os departamentos cadastrados são:");
			listTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			departmentsSection.add(listTitle);
			departments.forEach(department -> {
				departmentsSection.add(new DepartmentCard(department, this));
			});
		}

		JButton createDepartmentBtn = new JButton("Criar departamento");
		createDepartmentBtn.setForeground(Color.WHITE);
		createDepartmentBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createDepartmentBtn.setBackground(new Color(32, 178, 170));
		createDepartmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateDepartmentFrame();
				container.dispose();
			}
		});
		departmentsSection.add(createDepartmentBtn);

		coursesSection = new JPanel();
		BoxLayout courseslayout = new BoxLayout(coursesSection, BoxLayout.Y_AXIS);
		coursesSection.setLayout(courseslayout);
		coursesSection.setAlignmentY(CENTER_ALIGNMENT);


		ArrayList<Course> courses = CoursesController.index();
		if(courses.isEmpty()){
			JLabel listCourseTitle = new JLabel("Ainda não temos cursos cadastrados.");
			listCourseTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			coursesSection.add(listCourseTitle);
		}
		else{
			JLabel listCourseTitle = new JLabel("Atualmente, os cursos cadastrados são:");
			listCourseTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			coursesSection.add(listCourseTitle);
			courses.forEach(course -> {
				coursesSection.add(new CourseCard(course, this));
			});
		}

		JButton createCourseBtn = new JButton("Criar curso");
		createCourseBtn.setForeground(Color.WHITE);
		createCourseBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createCourseBtn.setBackground(new Color(32, 178, 170));
		createCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateCourseFrame();
				container.dispose();
			}
		});
		coursesSection.add(createCourseBtn);

		main.add(departmentsSection, BorderLayout.EAST);
		main.add(coursesSection, BorderLayout.WEST);
		container.getContentPane().add(main);
	}
}
