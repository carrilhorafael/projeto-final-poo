package views;

import java.awt.*;
import javax.swing.*;

import controllers.UsersController;
import models.abstracts.User;

import java.awt.event.*;


public class CreateTeacherView extends JFrame{

	FlowLayout mainLayout = new FlowLayout();
	/**
	 * Create the application.
	 */
	public CreateTeacherView(int departmentId) {
		super("Criar coordenador");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        initialize(this, departmentId);
        this.pack();
        this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame frame, int departmentId) {
		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);
		JPanel teacherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel teacherTitle = new JLabel("Criar professor");
		teacherTitle.setHorizontalAlignment(SwingConstants.CENTER);
		teacherTitle.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		teacherPanel.add(teacherTitle);
		frame.getContentPane().add(teacherPanel);

		JPanel formSection = new JPanel();
		BoxLayout formlayout = new BoxLayout(formSection, BoxLayout.Y_AXIS);
		formSection.setLayout(formlayout);
		formSection.setAlignmentY(CENTER_ALIGNMENT);

		JLabel lblName = new JLabel("Nome do professor:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(lblName);

		JTextField name = new JTextField();
		lblName.setLabelFor(name);
		name.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		name.setColumns(10);
		formSection.add(name);

		JLabel lblCPF = new JLabel("CPF do professor:");
		lblCPF.setHorizontalAlignment(SwingConstants.CENTER);
		lblCPF.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(lblCPF);

		JTextField cpf = new JTextField();
		lblCPF.setLabelFor(cpf);
		cpf.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		cpf.setColumns(10);
		formSection.add(cpf);

		JLabel lblEmail = new JLabel("E-mail do professor:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(lblEmail);

		JTextField email = new JTextField();
		lblEmail.setLabelFor(email);
		email.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		email.setColumns(10);
		formSection.add(email);

		JLabel lblRegistration = new JLabel("Matrícula do professor:");
		lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistration.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(lblRegistration);

		JTextField registration = new JTextField();
		lblRegistration.setLabelFor(registration);
		registration.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		registration.setColumns(10);
		formSection.add(registration);

		JLabel lblBirthdate = new JLabel("Data de Nascimento:");
		lblBirthdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirthdate.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(lblBirthdate);

		JTextField birthdate = new JTextField();
		lblBirthdate.setLabelFor(birthdate);
		birthdate.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		birthdate.setColumns(10);
		formSection.add(birthdate);

		JLabel lblState = new JLabel("Estado do professor:");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(lblState);

		String[] brazilianStates = {
			"Acre",
			"Alagoas",
			"Amazonas",
			"Amapá",
			"Bahia",
			"Ceará",
			"Distrito Federal",
			"Espirito Santo",
			"Goiás",
			"Maranhão",
			"Mato Grosso",
			"Mato Grosso do Sul",
			"Minas Gerais",
			"Pará",
			"Paraíba",
			"Paraná",
			"Pernambuco",
			"Piauí",
			"Rio de Janeiro",
			"Rio Grande do Norte",
			"Rio Grande do Sul",
			"Rondonia",
			"Roraima",
			"Santa Catarina",
			"São Paulo",
			"Sergipe",
			"Tocantins"
		};
		JComboBox<String> state = new JComboBox<>(brazilianStates);
		lblState.setLabelFor(state);
		state.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(state);

		JLabel lblNationality = new JLabel("Nacionalidade do professor:");
		lblNationality.setHorizontalAlignment(SwingConstants.CENTER);
		lblNationality.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		formSection.add(lblNationality);

		JTextField nationality = new JTextField();
		lblNationality.setLabelFor(nationality);
		nationality.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		nationality.setColumns(10);
		formSection.add(nationality);

		JButton createBtn = new JButton("Criar professor");
		createBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createBtn.setBackground(new Color(32, 178, 170));
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parameters = {
					name.getText(),
					cpf.getText(),
					email.getText(),
					registration.getText(),
					birthdate.getText(),
					state.getSelectedItem() + "",
					nationality.getText(),
					departmentId + ""

				};
				User created = UsersController.register(parameters, 4);
				if(created == null){
					JOptionPane.showMessageDialog(null, "Erro: Permissão negada");
				}else if(created.getErrors().isEmpty()){
					JOptionPane.showMessageDialog(null, "Coordenador criado com sucesso");

					frame.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Erro: " + created.getErrors().get(0));
				}
			}
		});
		formSection.add(createBtn);
		main.add(teacherPanel);
		main.add(formSection);
		frame.getContentPane().add(main);
	}
}
