package main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

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
		runGui();
		//runGenerator();
		runParser();
		

	}

	public static void runGui() {
		StartScreen startScreenGUI = new StartScreen("CourseTracker");
	}

	public static void runGenerator() {
		Program GE = Generator.createProgram("resources/gen_ed.zagp");
		Program CS_M = Generator.makeCSMajor();
		Program CIS_m = Generator.createProgram("resources/cis_minor.zagp");
		HashSet<Program> degree = new HashSet<Program>();
		degree.add(GE);
		degree.add(CIS_m);
		degree.add(CS_M);
		ZPlanner planner = new ZPlanner("ZAG", Semester.SO_SP, degree, 120);

		planner.createPlanner();
		System.out.println(planner.toString(true)); // Change boolean for less detail
		System.out.println(planner.toStringCalander(true)); // Change boolean for less detail
	}
	public static void runParser() {
		Generator.loadCourseDatabase("resources/courses.zagbase");
		System.out.println(Generator.findCourse("CS", "149").toString(true));
	}
}
