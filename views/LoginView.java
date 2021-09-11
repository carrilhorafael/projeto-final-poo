package views;

import controllers.AuthController;
import models.Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoginView {

	private JFrame frame = new JFrame("IDUFF - Faça Login");


	/** Create the application */
	public LoginView() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		initialize(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	/** Initialize the contents of the frame */
	private void initialize(final Container container) {
		final JPanel titleSection = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel entrarLabel = new JLabel("Entrar");
		entrarLabel.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		titleSection.add(entrarLabel);

		final JPanel containerForm = new JPanel();
		containerForm.setLayout(new FlowLayout());
		final JPanel form = new JPanel();
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));


		JLabel lblNomeDeUsurio = new JLabel("Nome de Usuário:");
		lblNomeDeUsurio.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(lblNomeDeUsurio);

		JTextField email = new JTextField();
		lblNomeDeUsurio.setLabelFor(email);
		email.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(email);
		System.out.println(email.getSize());

		JLabel passwordLabel = new JLabel("Senha:");
		passwordLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(passwordLabel);

		JPasswordField passwordField = new JPasswordField();
		passwordLabel.setLabelFor(passwordField);
		passwordField.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(passwordField);

		JButton loginBtn = new JButton("Fazer Login");
		loginBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		loginBtn.setBackground(new Color(32, 178, 170));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AuthController.login(email.getText(), String.valueOf(passwordField.getPassword()))){
					frame.setVisible(false);
					if (AuthController.getUserLogged() instanceof Manager) new SchoolYearsView();
					// else if(AuthController.getUserLogged() instanceof DepartmentCoordinator) new DepartmentCoordinatorView();
					// else if(AuthController.getUserLogged() instanceof CourseCoordinator) new CourseCoordinatorView();
					// else if(AuthController.getUserLogged() instanceof Teacher) new TeacherView();
					// else if(AuthController.getUserLogged() instanceof Student) new StudentView();
				}
			}
		});
		form.add(loginBtn);
		containerForm.add(form);
		container.add(titleSection, BorderLayout.NORTH);
		container.add(containerForm);


	}
}
