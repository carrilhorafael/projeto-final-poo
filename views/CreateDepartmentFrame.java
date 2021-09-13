package views;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.DepartmentsController;
import controllers.UsersController;
import models.Department;
import models.DepartmentCoordinator;
import views.components.HeaderComponent;

public class CreateDepartmentFrame extends JFrame{
	FlowLayout mainLayout = new FlowLayout();
	public CreateDepartmentFrame() {
		super("Criar departamento");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        initialize(this);
        this.pack();
        this.setVisible(true);
	}

	private void initialize(JFrame container) {
		container.add(new HeaderComponent(this), BorderLayout.PAGE_END);

		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

		JLabel departmentTitle = new JLabel("Criar departamento");
		departmentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		departmentTitle.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		container.add(departmentTitle);

		JPanel departmentsSection = new JPanel();
		BoxLayout departmentslayout = new BoxLayout(departmentsSection, BoxLayout.Y_AXIS);
		departmentsSection.setLayout(departmentslayout);
		departmentsSection.setAlignmentY(CENTER_ALIGNMENT);

		JLabel lblName = new JLabel("Nome do Departamento:");
		lblName.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		departmentsSection.add(lblName);

		JTextField name = new JTextField();
		lblName.setLabelFor(name);
		name.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		// name.setColumns(10);
		departmentsSection.add(name);

		JLabel lblArea = new JLabel("Área de conhecimento:");
		lblArea.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		departmentsSection.add(lblArea);

		JTextField area = new JTextField();
		lblArea.setLabelFor(area);
		area.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		area.setColumns(10);
		departmentsSection.add(area);

		JLabel lblCampus = new JLabel("Campus Sede:");
		lblCampus.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		departmentsSection.add(lblCampus);

		JTextField campus = new JTextField();
		lblCampus.setLabelFor(campus);
		campus.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		campus.setColumns(10);
		departmentsSection.add(campus);

		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		departmentsSection.add(lblCode);

		JTextField code = new JTextField();
		lblCode.setLabelFor(code);
		code.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		departmentsSection.add(code);

		JLabel lblCoordenador = new JLabel("Nome do Coordenador:");
		lblCoordenador.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		departmentsSection.add(lblCoordenador);

		JComboBox<String> coordenador = new JComboBox<>();
		ArrayList<DepartmentCoordinator> department_coordinators = UsersController.indexDepartmentCoordinatorsAvailable();
		if(!department_coordinators.isEmpty()){
			department_coordinators.forEach(department -> {
				coordenador.addItem(department.getId() + " - " + department.getName());
			});
		}else{
			coordenador.setEnabled(false);
		}
		lblCoordenador.setLabelFor(coordenador);
		coordenador.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		departmentsSection.add(coordenador);
		JButton createCoordinatorBtn = new JButton("Criar coordenador para departamento");
		createCoordinatorBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createCoordinatorBtn.setBackground(new Color(32, 178, 170));
		createCoordinatorBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new CreateCoordinatorPanel(2);
				container.dispose();;
			}
		});
		departmentsSection.add(createCoordinatorBtn);

		createCoordinatorBtn.setBackground(new Color(32, 178, 170));
		JButton createBtn = new JButton("Criar departamento");
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
				Department created = DepartmentsController.create(parameters);
				if(created == null){
					JOptionPane.showMessageDialog(null, "Erro: Permissão negada");
				}else if(created.getErrors().isEmpty()){
					JOptionPane.showMessageDialog(null, "Criado com sucesso");
					new ManageUniversityFrame();
					container.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Erro: " + created.getErrors().get(0));
				}
			}
		});
		departmentsSection.add(createBtn);
		main.add(departmentsSection);
		container.getContentPane().add(main);
		// container.getContentPane().add(departmentsSection);
	}
}
