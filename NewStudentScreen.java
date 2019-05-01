import javax.swing.JFrame;

public class NewStudentScreen {

	JFrame newStudentScreen;
	
	public NewStudentScreen(String title)
	{
		newStudentScreen = new JFrame();
		
		newStudentScreen.setSize(800, 600);
		newStudentScreen.setResizable(false);
		newStudentScreen.setLocation(200, 200);

		newStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newStudentScreen.setTitle(title);
		newStudentScreen.setVisible(true);
	}
}
