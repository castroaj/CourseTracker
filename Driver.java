import java.util.HashSet;

public class Driver {
	public static void main(String[] args) {
		// StartScreen startScreenGUI = new StartScreen("CourseTracker");
		// name semester bigbag, credits
		ZMajor CS = ZGenerator.makeCSMajor();
		HashSet<ZMajor> majors = new HashSet<ZMajor>();
		majors.add(CS);
		ZPlanner planner = new ZPlanner("Zeru", Semester.FR_FA, majors, 100);
		planner.doit();
		System.out.println(CS.toString(true));
	}
}
