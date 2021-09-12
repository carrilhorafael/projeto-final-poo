package views;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.CoursesController;
import controllers.UsersController;
import models.Course;
import models.CourseCoordinator;
import views.components.Header;

public class CreateCourseView extends JFrame{
	FlowLayout mainLayout = new FlowLayout();
	public CreateCourseView() {
		super("Criar curso");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        initialize(this);
        this.pack();
        this.setVisible(true);
	}

	private void initialize(JFrame container) {
		container.getContentPane().add(new Header(this), BorderLayout.PAGE_END);

		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

		JLabel courseTitle = new JLabel("Criar curso");
		courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		courseTitle.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		main.add(courseTitle);

		JPanel coursesSection = new JPanel();
		BoxLayout courseslayout = new BoxLayout(coursesSection, BoxLayout.Y_AXIS);
		coursesSection.setLayout(courseslayout);
		coursesSection.setAlignmentY(CENTER_ALIGNMENT);

		JLabel lblName = new JLabel("Nome do curso:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(lblName);

		JTextField name = new JTextField();
		lblName.setLabelFor(name);
		name.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(name);

		JLabel lblArea = new JLabel("Área de conhecimento:");
		lblArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblArea.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(lblArea);

		JTextField area = new JTextField();
		lblArea.setLabelFor(area);
		area.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		area.setColumns(10);
		coursesSection.add(area);

		JLabel lblCampus = new JLabel("Campus Sede:");
		lblCampus.setHorizontalAlignment(SwingConstants.CENTER);
		lblCampus.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(lblCampus);

		JTextField campus = new JTextField();
		lblCampus.setLabelFor(campus);
		campus.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		campus.setColumns(10);
		coursesSection.add(campus);

		JLabel lblCode = new JLabel("Code:");
		lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblCode.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(lblCode);

		JTextField code = new JTextField();
		lblCode.setLabelFor(code);
		code.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(code);

		JLabel lblCoordenador = new JLabel("Nome do Coordenador:");
		lblCoordenador.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoordenador.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(lblCoordenador);

		JComboBox<String> coordenador = new JComboBox<>();
		ArrayList<CourseCoordinator> course_coordinators = UsersController.indexCourseCoordinatorsAvailable();
		if(!course_coordinators.isEmpty()){
			course_coordinators.forEach(course -> {
				coordenador.addItem(course.getId() + " - " + course.getName());
			});
		}else{
			coordenador.setEnabled(false);
		}
		lblCoordenador.setLabelFor(coordenador);
		coordenador.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coursesSection.add(coordenador);

		JButton createCoordinatorBtn = new JButton("Criar coordenador para curso");
		createCoordinatorBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createCoordinatorBtn.setBackground(new Color(32, 178, 170));
		createCoordinatorBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new CreateCoordinatorView(3);
				container.dispose();
			}
		});
		coursesSection.add(createCoordinatorBtn);

		JButton createBtn = new JButton("Criar curso");
		createBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createBtn.setBackground(new Color(32, 178, 170));
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parameters = {
					name.getText(),
					area.getText(),
					campus.getText(),
					code.getText(),
					(coordenador.getSelectedItem()+"").split(" - ")[0]
				};
				Course created = CoursesController.create(parameters);
				if(created == null){
					JOptionPane.showMessageDialog(null, "Erro: Permissão negada");
				}else if(created.getErrors().isEmpty()){
					JOptionPane.showMessageDialog(null, "Criado com sucesso");
					new ManageUniversityView();
					container.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Erro: " + created.getErrors().get(0));
				}
			}
		});
		coursesSection.add(createBtn);
		main.add(coursesSection);
		container.getContentPane().add(main);
	}
}
