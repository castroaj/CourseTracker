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
		// runParser();
		// parse("resources/gen_ed.zagp");
		// parse("resources/cs_major.zagp");
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
		// System.out.println(Generator.findCourse("CS", "149").toString(true));
	}
}
