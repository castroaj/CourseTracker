
public class Driver {
	public static void main(String[] args) {
		StartScreen startScreenGUI = new StartScreen("CourseTracker");

		ZMajor CS = ZGenerator.makeCSMajor();
		
		System.out.println(CS.toString());
	}
}
