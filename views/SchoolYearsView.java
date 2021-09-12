package views;

import controllers.SchoolYearsController;
import models.SchoolYear;
import views.components.CreateSchoolYearForm;
import views.components.Header;
import views.components.SchoolYearTableCard;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class SchoolYearsView extends JFrame{


	FlowLayout mainLayout = new FlowLayout();

    public SchoolYearsView() {
		super("Página principal do Diretor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        //Set up the content pane.
        initialize(this.getContentPane());

        //Display the window.
        this.pack();
        this.setVisible(true);
	}

	public void reload(){
		// this.getContentPane().removeAll();
		new SchoolYearsView();
		this.dispose();
	}

	private void initialize(final Container container) {
		container.add(new Header(this), BorderLayout.PAGE_START);

		final JPanel main = new JPanel();
        main.setLayout(mainLayout);
        main.setAlignmentY(FlowLayout.CENTER);

		final JPanel schoolyearsview = new JPanel();
		BoxLayout schoolyearslayout = new BoxLayout(schoolyearsview, BoxLayout.Y_AXIS);
		schoolyearsview.setLayout(schoolyearslayout);

		ArrayList<SchoolYear> school_years = SchoolYearsController.index();

		if(school_years == null){
			JLabel schoolYearNullLabel = new JLabel("Ainda não há periodos letivos cadastrados");
			schoolYearNullLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			schoolyearsview.add(schoolYearNullLabel);
			schoolyearsview.add(new CreateSchoolYearForm(this));
		}
		else{
			JLabel schoolYearLabel = new JLabel("Os periodos letivos cadastrados no sistema são: ");
			schoolYearLabel.setFont(new Font("Fira Code SemiBold", Font.PLAIN, 16));
			schoolyearsview.add(schoolYearLabel);
			school_years.forEach(sy -> {
				SchoolYearTableCard syCard = new SchoolYearTableCard(sy, this);
				schoolyearsview.add(syCard);
			});
			schoolyearsview.add(new CreateSchoolYearForm(this));

		}
		main.add(schoolyearsview);
		container.add(main);
	}
}
