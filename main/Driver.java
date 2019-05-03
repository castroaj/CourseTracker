package main;

import java.io.IOException;
import java.util.HashSet;
import course_map.*;
import gui.StartScreen;

public class Driver {
	/**
	 * 1 planner = [ x Programs [ y Clusters [ z Courses ] ] ] =================IE:
	 * zeru = [ 2 (CS,GENED) [ 20 (Writing,Intro to CS) [CS101,WRTC103]]]
	 * 
	 * M = Major m = minor
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//StartScreen startScreenGUI = new StartScreen("CourseTracker");

		Program GE = ZGenerator.createProgram("resources/gen_ed.zagp");
		Program CS_M = ZGenerator.makeCSMajor();
		Program CIS_m = ZGenerator.createProgram("resources/cis_minor.zagp");
		HashSet<Program> degree = new HashSet<Program>();
		degree.add(GE);
		degree.add(CIS_m);
		degree.add(CS_M);
		ZPlanner planner = new ZPlanner("ZAG", Semester.SO_SP, degree, 120);
		planner.createPlanner();
		System.out.println(planner.toString(true));  //Change boolean for less detail
		System.out.println(planner.toStringCalander(true)); //Change boolean for less detail

	}
}
