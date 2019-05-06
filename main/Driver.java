package main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

import course_map.*;
import gui.PreferencesSetupScreen;
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
		// runGui();
		Generator.loadCourseDatabase("resources/courses.zagbase");

		Planner test = new Planner();
		test.setName("test");
		test.setSemester(Semester.FR_FA);
		test.addProgram(parseProgram("gen_ed"));
		test.addProgram(parseProgram("cs_major"));
		test.addProgram(parseProgram("cis_minor"));
		PreferencesSetupScreen prefScreen = new PreferencesSetupScreen("Test", test);
		// runPlanner();
		// parseProgram("gen_ed");
		// parseProgram("cs_major");
		// parseProgram("cis_minor");
	}

	public static void runGui() {
		Generator.loadCourseDatabase("resources/courses.zagbase");
		StartScreen startScreenGUI = new StartScreen("CourseTracker");
	}

	public static void parseCourseDatabase() {
		Generator.loadCourseDatabase("resources/courses.zagbase");
		System.out.println(Generator.findCourse("CS", "149").toString(true));
	}

	public static void parse(String file) {
		Generator.loadCourseDatabase("resources/courses.zagbase");
		Program p = Generator.loadProgram(file);
		System.out.println(p.toString(true));
	}

	public static Program parseProgram(String file) {
		Generator.loadCourseDatabase("resources/courses.zagbase");
		Program p = Generator.loadProgram("resources/" + file + ".zagp");
		// System.out.println(p.toString(true));
		return p;
	}

	public static void runPlanner() {
		HashSet<Program> programs = new HashSet<Program>();

		programs.add(parseProgram("gen_ed"));
		programs.add(parseProgram("cs_major"));
		programs.add(parseProgram("cis_minor"));

		Planner plan = new Planner("Zeru", Semester.FR_FA, programs, 120);

		System.out.println(plan);

	}

}
