package main;

import java.io.IOException;
import java.util.HashSet;
import course_map.*;

public class Driver {
	/**
	 * 1 planner = [ x Programs [ y Clusters [ z Courses ] ] ] =================IE:
	 * zeru = [ 2 (CS,GENED) [ 20 (Writing,Intro to CS) [CS101,WRTC103]]]
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// StartScreen startScreenGUI = new StartScreen("CourseTracker");

		Program GE = ZGenerator.createProgram("resources/gen_ed.zagp");
		Program CS_M = ZGenerator.makeCSMajor();
		Program CIS_m = ZGenerator.createProgram("resources/cis_minor.zagp");
		HashSet<Program> degree = new HashSet<Program>();
		degree.add(GE);
		degree.add(CIS_m);
		degree.add(CS_M);
		ZPlanner planner = new ZPlanner("Zeru Tadesse", Semester.SO_SP, degree, 120);

		System.out.println(planner.toString(true));

	}
}