package views.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.AuthController;
import models.CourseCoordinator;
import models.DepartmentCoordinator;
import models.Manager;
import views.ManageUniversityView;
import views.MyCourseView;
import views.MyDepartmentView;
import views.LoginView;
import views.SchoolYearsView;

public class Header extends JPanel{
    public Header(JFrame frame){
        super();
        FlowLayout layout = new FlowLayout();

        JPanel buttonsviews = new JPanel();
        layout.setAlignment(2);
        buttonsviews.setLayout(layout);

        this.setLayout(layout);
        JButton link_schoolyear_create = new JButton("Home Page");
        link_schoolyear_create.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        if(!(frame.getTitle().equals("Página principal do Diretor") || frame.getTitle().equals("Meu curso") || frame.getTitle().equals("Meu departamento"))){
            link_schoolyear_create.setBackground(new Color(32, 178, 170));
        }else {
            link_schoolyear_create.setBackground(new Color(32, 255, 170));
            link_schoolyear_create.setEnabled(false);
        }
        link_schoolyear_create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(AuthController.getUserLogged() instanceof Manager)
                    new SchoolYearsView();
                else if(AuthController.getUserLogged() instanceof DepartmentCoordinator)
                    new MyDepartmentView();
                else if(AuthController.getUserLogged() instanceof CourseCoordinator)
                    new MyCourseView();

                frame.dispose();
            }
        });
        buttonsviews.add(link_schoolyear_create);
        if(AuthController.getUserLogged() instanceof Manager){
            JButton link_department = new JButton("Gerenciamento da universidade");
            link_department.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
            if(!frame.getTitle().equals("Gerenciamento da universidade")){
            link_department.setBackground(new Color(32, 178, 170));
            link_department.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new ManageUniversityView();
                    frame.dispose();
                }
            });
        }else {
            link_department.setBackground(new Color(32, 255, 170));
            link_department.setEnabled(false);
        }
        buttonsviews.add(link_department);
    }

        JButton logout = new JButton("Fazer Logout");
        logout.setForeground(Color.WHITE);
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuthController.logout();
                new LoginView();
                frame.dispose();
            }
        });
        logout.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        logout.setBackground(new Color(32, 178, 170));
        buttonsviews.add(logout);




        this.add(buttonsviews);
	}
}
