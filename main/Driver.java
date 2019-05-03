package main;
import java.io.IOException;
import java.util.HashSet;
import graph.*;
import gui.*;
import main.*;
import temp.*;
import course_map.*;
public class Driver {
	/**
	 * 1 planner = [ x Programs [ y Clusters [ z Courses ] ] ] =================IE: zeru = [
	 * 2 (CS,GENED) [ 20 (Writing,Intro to CS) [CS101,WRTC103]]]
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		StartScreen startScreenGUI = new StartScreen("CourseTracker");

		Program CS = ZGenerator.makeCSMajor();
		Program GenED = ZGenerator.makeGenED();

		HashSet<Program> degree = new HashSet<Program>();
		degree.add(CS);
		degree.add(GenED);

		ZPlanner planner = new ZPlanner("Zeru Tadesse", Semester.SO_SP, degree, 120);

		System.out.println(planner.toString(false));
		

	}
}
