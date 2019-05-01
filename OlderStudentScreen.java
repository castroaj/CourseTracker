import javax.swing.JFrame;

public class OlderStudentScreen {

	JFrame olderStudentScreen;
	
	public OlderStudentScreen(String title)
	{
		olderStudentScreen = new JFrame();
		
		olderStudentScreen.setSize(400, 600);
		olderStudentScreen.setResizable(false);
		olderStudentScreen.setLocation(200, 100);

		olderStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		olderStudentScreen.setTitle(title);
		olderStudentScreen.setVisible(true);
	}
}
