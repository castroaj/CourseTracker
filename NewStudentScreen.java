import javax.swing.JFrame;

public class NewStudentScreen {

	private Planner planner;

	JFrame newStudentScreen;
		
	public NewStudentScreen(String title, Planner planner)
	{
		this.planner = planner;
	
		newStudentScreen = new JFrame();
		
		newStudentScreen.setSize(800, 600);
		newStudentScreen.setResizable(false);
		newStudentScreen.setLocation(200, 200);

		newStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newStudentScreen.setTitle(title);
		newStudentScreen.setVisible(true);
		
		System.out.println(planner.getMajorSubject());
	}
}
