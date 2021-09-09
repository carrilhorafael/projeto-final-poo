package views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Cadastro {

	private JFrame frame;
	private JLabel labelnome;
	private JTextField textnome;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro window = new Cadastro();
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
	public Cadastro() {
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

		JLabel lblCadastroDeUsurio = new JLabel("Cadastro de usu\u00E1rio");
		lblCadastroDeUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeUsurio.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		lblCadastroDeUsurio.setBounds(351, 34, 283, 44);
		frame.getContentPane().add(lblCadastroDeUsurio);
	
		labelnome = new JLabel("Nome:");
		labelnome.setBounds(59, 114, 160, 20);
		labelnome.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(labelnome);
		lblNomeDeUsurio.setLabelFor(textnome);
		
		textnome = new JTextField();
		textnome.setBounds(59, 146, 381, 60);
		textnome.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		textnome.setColumns(10);
		frame.getContentPane().add(textnome);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblEmail.setBounds(526, 114, 392, 20);
		frame.getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(526, 146, 392, 60);
		frame.getContentPane().add(textField);
	}
}
