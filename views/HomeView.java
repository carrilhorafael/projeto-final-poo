package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.SystemColor;

public class HomeView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView window = new HomeView();
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
	public HomeView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Fazer Logout");
		btnNewButton.setBounds(804, 12, 170, 44);
		btnNewButton.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(32, 178, 170));
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Criar Usu\u00E1rio");
		btnNewButton_1.setBackground(SystemColor.control);
		btnNewButton_1.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		btnNewButton.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		btnNewButton_1.setBounds(622, 12, 170, 44);
		frame.getContentPane().add(btnNewButton_1);
	}
}
