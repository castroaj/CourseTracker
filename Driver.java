import java.io.IOException;
import java.util.HashSet;

public class Driver {
	/**
	 * 1 planner = [ x Programs [ y Clusters [ z Courses ] ] ] ===================
	 * IE: zeru = [ 2 (CS,GENED) [ 20 (Writing,Intro to CS) [CS101,WRTC103]]]
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		StartScreen startScreenGUI = new StartScreen("CourseTracker");
		// name semester bigbag, credits
		Program CS = ZGenerator.makeCSMajor();
		Program GenED = ZGenerator.makeGenED();
		
		System.out.println(GenED);
		//HashSet<Program> majors = new HashSet<Program>();
		//majors.add(CS);
		//ZPlanner planner = new ZPlanner("Zeru", Semester.FR_FA, majors, 100);
		//planner.doit();
		//System.out.println(CS.toString(true));
	}
}
