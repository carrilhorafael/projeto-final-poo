package views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controllers.AuthController;
import models.Course;
import models.CourseCoordinator;
import views.components.ClassroomCard;
import views.components.HeaderComponent;
import views.components.StudentCard;

public class MyCourseFrame extends JFrame{
	FlowLayout mainLayout = new FlowLayout();
	Course course = ((CourseCoordinator)AuthController.getUserLogged()).getCourse();
	public MyCourseFrame() {
		super("Meu cursoo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );

        initialize(this);

        this.pack();
        this.setVisible(true);
	}

	public void reload(){
		new MyCourseFrame();
		this.dispose();
	}

	private void initialize(JFrame container) {
		container.getContentPane().add(new HeaderComponent(container), BorderLayout.PAGE_END);

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
			JLabel classroomsTitle = new JLabel("Ainda não há turmas cadastradas para o curso");
			classroomsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			classroomsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			classroomsPanel.add(classroomsTitle);
		}else{
			JLabel classroomsTitle = new JLabel("Turmas cadastradas para o curso: ");
			classroomsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			classroomsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			classroomsPanel.add(classroomsTitle);
			course.getClassrooms().forEach(classroom -> {
				classroomsPanel.add(new ClassroomCard(classroom, this));
			});
		}

		JButton createClassroomBtn = new JButton("Criar turma para o curso");
		createClassroomBtn.setForeground(Color.WHITE);
		createClassroomBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createClassroomBtn.setBackground(new Color(32, 178, 170));
		createClassroomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateClassroomFrame(course.getId());
				container.dispose();
			}
		});
		classroomsPanel.add(createClassroomBtn);

		JPanel studentsPanel = new JPanel();
		BoxLayout studentsLayout = new BoxLayout(studentsPanel, BoxLayout.Y_AXIS);
		studentsPanel.setLayout(studentsLayout);

		if(course.getStudents().isEmpty()){
			JLabel studentsTitle = new JLabel("Ainda não há alunos cadastrados para o curso");
			studentsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			studentsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			studentsPanel.add(studentsTitle);
		}else{
			JLabel studentsTitle = new JLabel("Alunos cadastrados do curso: ");
			studentsTitle.setHorizontalAlignment(SwingConstants.CENTER);
			studentsTitle.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			studentsPanel.add(studentsTitle);
			course.getStudents().forEach(student -> {
				studentsPanel.add(new StudentCard(student, this));
			});
		}

		JButton createStudentBtn = new JButton("Criar aluno para o curso");
		createStudentBtn.setForeground(Color.WHITE);
		createStudentBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createStudentBtn.setBackground(new Color(32, 178, 170));
		createStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateStudentFrame(course.getId());
				container.dispose();
			}
		});
		studentsPanel.add(createStudentBtn);

		main.add(classroomsPanel, BorderLayout.WEST);
		main.add(studentsPanel, BorderLayout.EAST);

		container.getContentPane().add(main);
	}

}
