package views;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.AuthController;
import models.Course;
import models.CourseCoordinator;
import views.components.Header;
import views.components.SubjectTableCard;
import views.components.TeacherTableCard;

public class MyCourseView extends JFrame{
	FlowLayout mainLayout = new FlowLayout();
	Course course = ((CourseCoordinator)AuthController.getUserLogged()).getCourse();
	public MyCourseView() {
		super("Meu cursoo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );

        initialize(this);

        this.pack();
        this.setVisible(true);
	}

	public void reload(){
		this.initialize(this);
	}

	private void initialize(JFrame container) {
		container.getContentPane().add(new Header(container), BorderLayout.PAGE_END);

		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

		JPanel classroomsPanel = new JPanel();
		BoxLayout classroomsLayout = new BoxLayout(classroomsPanel, BoxLayout.Y_AXIS);
		classroomsPanel.setLayout(classroomsLayout);

		JLabel myCourseTitle = new JLabel("Meu curso");
		myCourseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		myCourseTitle.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		classroomsPanel.add(myCourseTitle);

		JLabel courseLabel = new JLabel("Nome do meu curso: " + course.getName());
		courseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		courseLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		classroomsPanel.add(courseLabel);

		if(course.getClassrooms().isEmpty()){
			JLabel classroomsTitle = new JLabel("Ainda não há matérias cadastradas para o curso");
			classroomsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			classroomsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			classroomsPanel.add(classroomsTitle);
		}else{
			JLabel classroomsTitle = new JLabel("Matérias cadastradas para o curso: ");
			classroomsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			classroomsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			classroomsPanel.add(classroomsTitle);
			course.getClassrooms().forEach(classroom -> {
				classroomsPanel.add(new ClassroomTableCard(classroom, this));
			});
		}

		JButton createClassroomBtn = new JButton("Criar matéria para o curso");
		createClassroomBtn.setForeground(Color.WHITE);
		createClassroomBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createClassroomBtn.setBackground(new Color(32, 178, 170));
		createClassroomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new CreateClassroomView();
				container.dispose();
			}
		});
		classroomsPanel.add(createClassroomBtn);

		JPanel studentsPanel = new JPanel();
		BoxLayout studentsLayout = new BoxLayout(studentsPanel, BoxLayout.Y_AXIS);
		studentsPanel.setLayout(studentsLayout);

		if(course.getStudents().isEmpty()){
			JLabel studentsTitle = new JLabel("Ainda não há professores cadastrados para o curso");
			studentsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			studentsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			studentsPanel.add(studentsTitle);
		}else{
			JLabel studentsTitle = new JLabel("Professores cadastrados para o curso: ");
			studentsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			studentsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			studentsPanel.add(studentsTitle);
			course.getStudents().forEach(teacher -> {
				studentsPanel.add(new TeacherTableCard(teacher, this));
			});
		}

		JButton createTeacherBtn = new JButton("Criar professor para o curso");
		createTeacherBtn.setForeground(Color.WHITE);
		createTeacherBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createTeacherBtn.setBackground(new Color(32, 178, 170));
		createTeacherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateTeacherView(course.getId());
				container.dispose();
			}
		});
		studentsPanel.add(createTeacherBtn);

		main.add(subjectsPanel, BorderLayout.WEST);
		main.add(studentsPanel, BorderLayout.EAST);

		container.getContentPane().add(main);
	}

}
