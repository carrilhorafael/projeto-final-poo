package views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Departments {

	private JFrame frame;
	private JLabel title;
	private JLabel dptsCadastrados;
	private JTable table;
	private JButton createBtn;
	private JButton deleteBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Departments window = new Departments();
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
	public Departments() {
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
		
		title = new JLabel("P\u00E1gina de departamentos");
		title.setBounds(307, 48, 372, 44);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
		frame.getContentPane().add(title);
		
		dptsCadastrados = new JLabel("Atualmente, os departamentos cadastrados  s\u00E3o:");
		dptsCadastrados.setBounds(49, 104, 473, 20);
		dptsCadastrados.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		frame.getContentPane().add(dptsCadastrados);
		
		table = new JTable();
		table.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.shadow"));
		table.setBackground(UIManager.getColor("Panel.background"));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nome", "\u00C1rea de Conhecimento", "Coordenador"},
				{"Teste", "Teste", null},
				{"Testinho", "Testão", "Carrilho"}
			},
			new String[] {
				"Nome", "\u00C1rea de Conhecimento", "Coordenador"
			}
		));
		table.setFont(new Font("Fira Code", Font.BOLD, 16));
		table.setEnabled(false);
		table.setBounds(36, 135, 895, 256);
		frame.getContentPane().add(table);
		
		JLabel lblOsDepartamentosQue = new JLabel("Os departamentos que voc\u00EA coordena s\u00E3o:");
		lblOsDepartamentosQue.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblOsDepartamentosQue.setBounds(36, 405, 397, 20);
		frame.getContentPane().add(lblOsDepartamentosQue);
		
		JLabel lblTestinho = new JLabel("Testinho");
		lblTestinho.setForeground(UIManager.getColor("OptionPane.questionDialog.border.background"));
		lblTestinho.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		lblTestinho.setBounds(36, 437, 397, 20);
		frame.getContentPane().add(lblTestinho);
		
		createBtn = new JButton("Criar departamento");
		createBtn.setForeground(Color.WHITE);
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		createBtn.setBackground(new Color(32, 178, 170));
		createBtn.setBounds(602, 403, 268, 44);
		frame.getContentPane().add(createBtn);
		
		deleteBtn = new JButton("Deletar departamento");
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		deleteBtn.setBackground(new Color(32, 178, 170));
		deleteBtn.setBounds(602, 480, 268, 44);
		frame.getContentPane().add(deleteBtn);
	}
}
