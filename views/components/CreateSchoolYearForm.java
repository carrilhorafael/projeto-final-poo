package views.components;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.SchoolYearsController;
import models.SchoolYear;
import views.SchoolYearsView;
public class CreateSchoolYearForm extends JPanel{

    public CreateSchoolYearForm(SchoolYearsView container){
        super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		final JPanel form = new JPanel();
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

		JLabel lblYear = new JLabel("Ano:");
		lblYear.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(lblYear);

		JTextField year = new JTextField();
		lblYear.setLabelFor(year);
		year.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(year);

		JLabel lblSemester = new JLabel("Semestre:");
		lblSemester.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(lblSemester);

		JTextField semester = new JTextField();
		lblYear.setLabelFor(semester);
		semester.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(semester);

		JLabel lblStatus = new JLabel("Escolha o status:");
		lblStatus.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(lblStatus);

		JComboBox status = new JComboBox();
		status.addItem("Planejamento");
		status.addItem("Inscrições");
		status.addItem("Ativo");
		status.addItem("Concluido");
		lblStatus.setLabelFor(status);
		status.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		form.add(status);

		JButton send = new JButton("Criar periodo letivo");
			send.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			send.setBackground(new Color(32, 178, 170));
			send.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] parameters = {year.getText(), semester.getText(), status.getSelectedItem()+""};
					SchoolYear created = SchoolYearsController.create(parameters);
					if (created.getErrors().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!!");
                        container.reload();
					}else{
                        JOptionPane.showMessageDialog(null, "Erro no cadastro: " + created.getErrors().get(0));
                    }
				}
		});
		form.add(send);

		this.add(form);
	}
}
