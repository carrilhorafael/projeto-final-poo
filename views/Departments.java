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

public class Departments {

	private JFrame frame;
	private JLabel title;
	private JLabel dptsCadastrados;
	private JTable table;

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
		table.setBounds(45, 136, 895, 327);
		frame.getContentPane().add(table);
	}
}
