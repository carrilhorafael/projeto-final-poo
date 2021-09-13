package views;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.ClassroomsController;
import controllers.SubjectsController;
import controllers.UsersController;
import models.Classroom;
import models.SchoolYear;
import models.Subject;
import models.Teacher;
import views.components.HeaderComponent;

public class CreateClassroomFrame extends JFrame{
	FlowLayout mainLayout = new FlowLayout();
    public CreateClassroomFrame(int courseId){
        super("Criar matéria");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        initialize(this, courseId);
        this.pack();
        this.setVisible(true);
    }
    public void initialize(JFrame container, int courseId){
        container.add(new HeaderComponent(this), BorderLayout.PAGE_END);

		final JPanel main = new JPanel();
		main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

        JPanel formSection = new JPanel();
        BoxLayout formlayout = new BoxLayout(formSection, BoxLayout.Y_AXIS);
        formSection.setLayout(formlayout);
        formSection.setAlignmentY(CENTER_ALIGNMENT);

        JLabel classroomTitle = new JLabel("Criar nova turma.");
        classroomTitle.setHorizontalAlignment(SwingConstants.CENTER);
        classroomTitle.setFont(new Font("Bebas Neue", Font.PLAIN, 36));
        formSection.add(classroomTitle);

        JLabel lblCode = new JLabel("Código de turma:");
        lblCode.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        formSection.add(lblCode);

        JTextField code = new JTextField();
        lblCode.setLabelFor(code);
        code.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        code.setColumns(10);
        formSection.add(code);

        JLabel lblRoom = new JLabel("Sala de aula:");
        lblRoom.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        formSection.add(lblRoom);

        JTextField room = new JTextField();
        lblRoom.setLabelFor(room);
        room.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        room.setColumns(10);
        formSection.add(room);

        ArrayList<Teacher> teachers = UsersController.indexTeachers();
        JComboBox<String> teacherSelect = new JComboBox<>();
        if(!teachers.isEmpty()){
            teachers.forEach(teacher -> {
                teacherSelect.addItem(teacher.getId() + " - " + teacher.getName());
            });
        }

        ArrayList<Subject> subjects = SubjectsController.index();
        JComboBox<String> subjectSelect = new JComboBox<>();
        if(!subjects.isEmpty()){
            subjects.forEach(subject -> {
                subjectSelect.addItem(subject.getId() + " - " + subject.getName());
            });
        }

        JButton createSubjectBtn = new JButton("Criar nova matéria");
        createSubjectBtn.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
        createSubjectBtn.setBackground(new Color(32, 178, 170));
        createSubjectBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String[] parameters = {
                    code.getText(),
                    room.getText(),
                    (teacherSelect.getSelectedItem()+"").split(" - ")[0],
                    (subjectSelect.getSelectedItem()+"").split(" - ")[0],
                    courseId + ""
                };
                Classroom created = ClassroomsController.create(parameters);
                if(created == null){
                    JOptionPane.showMessageDialog(null, "Erro: Permissão negada");
                }else if(created.getErrors().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Criado com sucesso");
                    new MyCourseFrame();
                    container.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Erro: " + created.getErrors().get(0));
                }
            }
        });
        formSection.add(createSubjectBtn);

        main.add(formSection);
        this.getContentPane().add(main);

    }
}
