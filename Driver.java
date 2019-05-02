import java.util.HashSet;

public class Driver {
	public static void main(String[] args) {
		// StartScreen startScreenGUI = new StartScreen("CourseTracker");
		// name semester bigbag, credits
		Program CS = ZGenerator.makeCSMajor();
		System.out.println(CS.export());
		//HashSet<Program> majors = new HashSet<Program>();
		//majors.add(CS);
		//ZPlanner planner = new ZPlanner("Zeru", Semester.FR_FA, majors, 100);
		//planner.doit();
		//System.out.println(CS.toString(true));
	}
}
