package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class deleteDepartment {

	private JFrame frame;
	private JLabel title;
	private JLabel lblId;
	private JTextField id;
	private JButton deleteBtn;
	private JButton backToHome;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteDepartment window = new deleteDepartment();
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
	public deleteDepartment() {
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
		
		title = new JLabel("Excluir um departamento");
		title.setBounds(307, 48, 372, 44);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		frame.getContentPane().add(title);
		
		lblId = new JLabel("ID do Departamento:");
		lblId.setBounds(292, 164, 402, 20);
		lblId.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(lblId);
		lblId.setLabelFor(id);
		
		id = new JTextField();
		id.setBounds(292, 195, 402, 60);
		id.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		id.setColumns(10);
		frame.getContentPane().add(id);
		
		deleteBtn = new JButton("Deletar departamento");
		deleteBtn.setForeground(Color.DARK_GRAY);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		deleteBtn.setBackground(new Color(32, 178, 170));
		deleteBtn.setBounds(359, 281, 268, 44);
		frame.getContentPane().add(deleteBtn);
		
		JButton backToHome = new JButton("Voltar para o início");
		backToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backToHome.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		backToHome.setBackground(new Color(32, 178, 170));
		backToHome.setBounds(359, 362, 268, 44);
		frame.getContentPane().add(backToHome);
	}

}
