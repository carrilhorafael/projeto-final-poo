package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class createCoordinator {

	private JFrame frame;
	private JLabel title;
	private JLabel lblName;
	private JTextField name;
	private JLabel lblCPF;
	private JTextField cpf;
	private JLabel lblEmail;
	private JTextField email;
	private JLabel lblPassword;
	private JTextField password;
	private JButton createBtn;
	private JButton backToHome;
	private JButton btnCreateCoord;
	private JLabel lblRegistration;
	private JTextField registration;
	private JLabel lblBirthdate;
	private JTextField birthdate;
	private JLabel lblState;
	private JTextField state;
	private JLabel lblNationality;
	private JTextField nationality;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createCoordinator window = new createCoordinator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public createCoordinator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		title = new JLabel("criar coordenador");
		title.setBounds(307, 33, 372, 44);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		frame.getContentPane().add(title);
		
		lblName = new JLabel("Nome do Coordenador:");
		lblName.setBounds(46, 100, 402, 20);
		lblName.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(lblName);
		lblName.setLabelFor(name);
		
		name = new JTextField();
		name.setBounds(46, 131, 402, 60);
		name.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		name.setColumns(10);
		frame.getContentPane().add(name);
		
		lblCPF = new JLabel("CPF do Coordenador:");
		lblCPF.setBounds(518, 100, 402, 20);
		lblCPF.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(lblCPF);
		lblCPF.setLabelFor(cpf);
		
		cpf = new JTextField();
		cpf.setBounds(518, 131, 402, 60);
		cpf.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		cpf.setColumns(10);
		frame.getContentPane().add(cpf);
		
		lblEmail = new JLabel("E-mail do Coordenador:");
		lblEmail.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblEmail.setBounds(46, 225, 402, 20);
		frame.getContentPane().add(lblEmail);
		lblEmail.setLabelFor(email);
		
		email = new JTextField();
		email.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		email.setColumns(10);
		email.setBounds(46, 256, 402, 60);
		frame.getContentPane().add(email);
		
		lblPassword = new JLabel("Senha da Conta:");
		lblPassword.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblPassword.setBounds(518, 225, 402, 20);
		frame.getContentPane().add(lblPassword);
		lblPassword.setLabelFor(password);
		
		password = new JTextField();
		password.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(518, 256, 402, 60);
		frame.getContentPane().add(password);
		
		lblRegistration = new JLabel("Matr\u00EDcula do Coordenador:");
		lblRegistration.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblRegistration.setBounds(46, 351, 402, 20);
		frame.getContentPane().add(lblRegistration);
		lblRegistration.setLabelFor(registration);
		
		registration = new JTextField();
		registration.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		registration.setColumns(10);
		registration.setBounds(46, 382, 402, 60);
		frame.getContentPane().add(registration);
		
		lblBirthdate = new JLabel("Data de Nascimento:");
		lblBirthdate.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblBirthdate.setBounds(518, 351, 402, 20);
		frame.getContentPane().add(lblBirthdate);
		lblBirthdate.setLabelFor(birthdate);
		
		birthdate = new JTextField();
		birthdate.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		birthdate.setColumns(10);
		birthdate.setBounds(518, 382, 402, 60);
		frame.getContentPane().add(birthdate);
		
		lblState = new JLabel("Estado do Coordenador:");
		lblState.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblState.setBounds(46, 476, 402, 20);
		frame.getContentPane().add(lblState);
		lblState.setLabelFor(state);
		
		state = new JTextField();
		state.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		state.setColumns(10);
		state.setBounds(46, 507, 402, 60);
		frame.getContentPane().add(state);
		
		lblNationality = new JLabel("Data de Nascimento:");
		lblNationality.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblNationality.setBounds(518, 476, 402, 20);
		frame.getContentPane().add(lblNationality);
		lblNationality.setLabelFor(nationality);
		
		nationality = new JTextField();
		nationality.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		nationality.setColumns(10);
		nationality.setBounds(518, 507, 402, 60);
		frame.getContentPane().add(nationality);
		
		JButton createBtn = new JButton("Criar Coordenador");
		createBtn.setBounds(46, 608, 268, 44);
		createBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createBtn.setBackground(new Color(32, 178, 170));
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			}
		});
		frame.getContentPane().add(createBtn);
		
		JButton backToHome = new JButton("Voltar para o início");
		backToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backToHome.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		backToHome.setBackground(new Color(32, 178, 170));
		backToHome.setBounds(652, 608, 268, 44);
		frame.getContentPane().add(backToHome);
		
	}

}
