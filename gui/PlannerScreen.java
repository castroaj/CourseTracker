package gui;
import javax.swing.JFrame;

import main.Planner;

public class PlannerScreen {

	JFrame plannerScreen;
	Planner planner;
	
	public PlannerScreen(String title, Planner planner)
	{
		this.planner = planner;
		plannerScreen = new JFrame();
		plannerScreen.setSize(400, 200);
		plannerScreen.setResizable(false);
		plannerScreen.setLocation(200, 200);
		plannerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plannerScreen.setTitle(title);
		plannerScreen.setVisible(true);
		 
	}
	
}
