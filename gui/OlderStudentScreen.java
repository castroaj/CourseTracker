package gui;
import javax.swing.JFrame;

import main.ZPlanner;
import temp.*;
public class OlderStudentScreen {

	private ZPlanner planner;
	
	JFrame olderStudentScreen;
	
	public OlderStudentScreen(String title)
	{
		this.planner = new ZPlanner();
		olderStudentScreen = new JFrame();
		
		olderStudentScreen.setSize(400, 600);
		olderStudentScreen.setResizable(false);
		olderStudentScreen.setLocation(200, 100);

		olderStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		olderStudentScreen.setTitle(title);
		olderStudentScreen.setVisible(true);
	}
}
