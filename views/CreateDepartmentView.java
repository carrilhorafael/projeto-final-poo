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


public class CreateDepartmentView {

	private JFrame frame;
	private JLabel title;
	private JLabel lblName;
	private JTextField name;
	private JLabel lblArea;
	private JTextField area;
	private JLabel lblCampus;
	private JTextField campus;
	private JLabel lblCoordenador;
	private JTextField coordenador;
	private JButton btnCreateCoord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDepartmentView window = new CreateDepartmentView();
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
	public CreateDepartmentView() {
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

		title = new JLabel("criar departamento");
		title.setBounds(307, 48, 372, 44);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		frame.getContentPane().add(title);

		lblName = new JLabel("Nome do Departamento:");
		lblName.setBounds(47, 117, 402, 20);
		lblName.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(lblName);
		lblName.setLabelFor(name);

		name = new JTextField();
		name.setBounds(47, 148, 402, 60);
		name.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		name.setColumns(10);
		frame.getContentPane().add(name);

		lblArea = new JLabel("\u00C1rea de conhecimento:");
		lblArea.setBounds(519, 117, 402, 20);
		lblArea.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(lblArea);
		lblArea.setLabelFor(area);

		area = new JTextField();
		area.setBounds(519, 148, 402, 60);
		area.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		area.setColumns(10);
		frame.getContentPane().add(area);

		lblCampus = new JLabel("Campus Sede:");
		lblCampus.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblCampus.setBounds(47, 242, 402, 20);
		frame.getContentPane().add(lblCampus);
		lblCampus.setLabelFor(campus);

		campus = new JTextField();
		campus.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		campus.setColumns(10);
		campus.setBounds(47, 273, 402, 60);
		frame.getContentPane().add(campus);

		lblCoordenador = new JLabel("Nome do Coordenador:");
		lblCoordenador.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblCoordenador.setBounds(519, 242, 402, 20);
		frame.getContentPane().add(lblCoordenador);
		lblCoordenador.setLabelFor(coordenador);

		coordenador = new JTextField();
		coordenador.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		coordenador.setColumns(10);
		coordenador.setBounds(519, 273, 402, 60);
		frame.getContentPane().add(coordenador);

		JButton createBtn = new JButton("Criar departamento");
		createBtn.setBounds(359, 374, 268, 44);
		createBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createBtn.setBackground(new Color(32, 178, 170));
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(createBtn);

		JButton backToHome = new JButton("Voltar para o in�cio");
		backToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backToHome.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		backToHome.setBackground(new Color(32, 178, 170));
		backToHome.setBounds(359, 558, 268, 44);
		frame.getContentPane().add(backToHome);

		btnCreateCoord = new JButton("Criar Coordenador");
		btnCreateCoord.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		btnCreateCoord.setBackground(new Color(32, 178, 170));
		btnCreateCoord.setBounds(359, 468, 268, 44);
		frame.getContentPane().add(btnCreateCoord);
	}
}
