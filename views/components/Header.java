package views.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import views.CoursesView;
import views.DepartmentsView;
import views.SchoolYearsView;

public class Header extends JPanel{
    public Header(JFrame frame){
        super();
        FlowLayout layout = new FlowLayout();

        JPanel buttonsviews = new JPanel();
        layout.setAlignment(layout.RIGHT);
        buttonsviews.setLayout(layout);

        this.setLayout(layout);

		JButton link_schoolyear_create = new JButton("Home Page");
		link_schoolyear_create.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		if(!frame.getTitle().equals("Página principal do Diretor")){
            link_schoolyear_create.setBackground(new Color(32, 178, 170));
            link_schoolyear_create.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new SchoolYearsView();
                    frame.toBack();
                    frame.setVisible(false);
                }
            });
        }else {
            link_schoolyear_create.setBackground(new Color(32, 255, 170));
            link_schoolyear_create.setEnabled(false);
        }
		buttonsviews.add(link_schoolyear_create);

		JButton link_department = new JButton("Gerenciamento de departamentos");
		link_department.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		if(!frame.getTitle().equals("Gerenciamento de departamentos")){
            link_department.setBackground(new Color(32, 178, 170));
            link_department.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DepartmentsView();
                    frame.toBack();
                    frame.setVisible(false);
                }
            });
        }else {
            link_department.setBackground(new Color(32, 255, 170));
            link_department.setEnabled(false);
        }
		buttonsviews.add(link_department);

        JButton link_course = new JButton("Gerenciamento de cursos");
		// link_course.setBounds(900, 640, 370, 50);
		link_course.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
		if(!frame.getTitle().equals("Gerenciamento de curso")){
            link_course.setBackground(new Color(32, 178, 170));
            link_course.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new CoursesView();
                    frame.toBack();
                    frame.setVisible(false);
                }
            });
        }else {
            link_course.setBackground(new Color(32, 255, 170));
            link_course.setEnabled(false);
        }
		buttonsviews.add(link_course);
        this.add(buttonsviews);
	}
}
