package gui;
import javax.swing.JFrame;
import temp.*;
public class OlderStudentScreen {

	private Planner planner;
	
	JFrame olderStudentScreen;
	
	public OlderStudentScreen(String title, Planner planner)
	{
		this.planner = planner;
		olderStudentScreen = new JFrame();
		
		olderStudentScreen.setSize(400, 600);
		olderStudentScreen.setResizable(false);
		olderStudentScreen.setLocation(200, 100);

		olderStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		olderStudentScreen.setTitle(title);
		olderStudentScreen.setVisible(true);
		
		System.out.println(planner.getMajorObject().getListOfRequiredCourses().toString());
		System.out.println(planner.getMajorObject().getCourseCount());
	}
}
