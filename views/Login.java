package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controllers.AuthController;
import models.abstracts.User;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.SpringLayout;

public class Login {

	private JFrame frame;
	private JLabel entrar;
	private JLabel lblNomeDeUsurio;
	private JTextField email;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	
	/** Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the application */
	public Login() {
		initialize();
	}

	/** Initialize the contents of the frame */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		entrar = new JLabel("Entrar");
		entrar.setBounds(451, 115, 86, 44);
		entrar.setHorizontalAlignment(SwingConstants.CENTER);
		entrar.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		frame.getContentPane().add(entrar);
		
		lblNomeDeUsurio = new JLabel("Nome de Usu\u00E1rio:");
		lblNomeDeUsurio.setBounds(371, 197, 160, 20);
		lblNomeDeUsurio.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(lblNomeDeUsurio);
		lblNomeDeUsurio.setLabelFor(email);
		
		email = new JTextField();
		email.setBounds(371, 229, 247, 60);
		email.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		email.setColumns(10);
		frame.getContentPane().add(email);
		
		passwordLabel = new JLabel("Senha:");
		passwordLabel.setBounds(371, 315, 60, 20);
		passwordLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(passwordLabel);
		passwordLabel.setLabelFor(passwordField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(371, 347, 247, 60);
		passwordField.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(passwordField);
		
		JButton loginBtn = new JButton("Fazer Login");
		loginBtn.setBounds(409, 432, 170, 44);
		loginBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		loginBtn.setBackground(new Color(32, 178, 170));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User userLogged = AuthController.login(email, passwordField);
		        // Teste de logado (pode se tornar um try catch)
		        if (userLogged != null){
		            System.out.println("Você está logado como " + userLogged.getName());
		            return userLogged;
		        }else{
		            System.out.println("Erro no Login - Verifique os parametros informados");
		            return null;
		        }
			}
		});
		frame.getContentPane().add(loginBtn);
	}
}
