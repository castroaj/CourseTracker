package main;

import java.util.ArrayList;

import java.util.HashSet;
import graph.*;
import course_map.*;

/**
 * Planner application that handles the logic of assembling a degree with
 * multiple programs
 * 
 * @author Zeru Tadesse
 *
 */
public class Planner {

	private String name;
	private Semester currentSemester;

	private int totalCreditsNeeded;
	private CourseGraph courseGraph;

	private Course[][] year = new Course[8][5]; // 8 semesters, 5 classes each
	// TODO: private HashSet<Course> coursesTaken;
	private HashSet<Program> programs;
	private HashSet<Cluster> allClusters;
	private HashSet<Course> courses;

	private HashSet<Course> calander;

	/**
	 * Planner Constructor Initialize variables and creates collections
	 * 
	 * @param studentName       Name of student ie: John Doe
	 * @param currentSemester   Current Semester ie: FR_SP
	 * @param programs          Program collection
	 * @param creditsToGraduate Total credits needed for graduation
	 */
	public Planner(String studentName, Semester currentSemester, HashSet<Program> programs, int creditsToGraduate) {
		this.name = studentName;
		this.currentSemester = currentSemester;
		this.programs = programs;
		this.totalCreditsNeeded = creditsToGraduate;
		this.courseGraph = new CourseGraph();
		this.calander = new HashSet<Course>();
		allClusters = new HashSet<Cluster>();
		for (Program p : programs)
			allClusters.addAll(p.getClusters());
	}

	public Planner() {
		this.name = "";
		this.currentSemester = null;
		this.programs = new HashSet<Program>();
		this.totalCreditsNeeded = -1;
		this.courseGraph = new CourseGraph();
		this.calander = new HashSet<Course>();
		this.allClusters = new HashSet<Cluster>();
	}

	/**
	 * Displays the Planner as text
	 * 
	 * @param verbose Whether to include course detail or not
	 * @return return the text as string
	 */
	public String toString(boolean verbose) {
		String s = String.format(
				"%s\nCurrent Semester: %s\nPrograms: %d\nClusters: %d\nMin Classes: %d\n============\n", this.name,
				this.currentSemester.toString(), this.programs.size(), this.allClusters.size(), getClassCount());
		for (Cluster c : allClusters) {
			s += c.toString(verbose) + "";
		}
		return s;
	}

	/**
	 * Get the total number of classes to fulfill all cluster requirements
	 * 
	 * @return Minimum number of classes to complete clusters
	 */
	public int getClassCount() {
		int count = 0;
		for (Cluster c : allClusters) {
			count += c.getClassCount();
		}
		return count;
	}

	/**
	 * ToString redirect {@link #toString(boolean)}
	 */
	public String toString() {
		return toString(true);
	}

	/**
	 * Get all of the clusters in every program
	 * 
	 * @return HshSet of Clusters
	 */
	public HashSet<Cluster> getAllClusters() {
		return this.allClusters;
	}
	
	public ArrayList<Cluster> getListAllClusters()
	{
		ArrayList<Cluster> list = new ArrayList<Cluster>();
		list.addAll(this.allClusters);
		return list;
	}

	/**
	 * Test method for inserting classes into planner
	 * 
	 * @deprecated do not use, only for testing
	 */
	public void doit() {
		int count = 0;
		ArrayList<Program> majors = new ArrayList<Program>();
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		ArrayList<Course> courses = new ArrayList<Course>();
		// clusters.addAll(programs);
		majors.addAll(programs);
		System.out.println("Num of majors " + majors.size());
		for (int m = 0; m < majors.size(); m++) {
			clusters = new ArrayList<Cluster>();
			clusters.addAll(majors.get(m).getClusters());
			System.out.println("Clusteres " + clusters.size());
			for (int cl = 0; cl < clusters.size(); cl++) {
				courses = new ArrayList<Course>();
				courses.addAll(clusters.get(cl).getCourses());
				System.out.println(clusters.get(cl).getName() + " (" + courses.size() + ")");
				int classesToAdd = 0;
				if (clusters.get(cl).getRule() == Rule.TAKE_ALL)
					classesToAdd = courses.size();
				if (clusters.get(cl).getRule() == Rule.TAKE_ONE)
					classesToAdd = 1;
				if (clusters.get(cl).getRule() == Rule.TAKE_TWO)
					classesToAdd = 2;
				if (clusters.get(cl).getRule() == Rule.TAKE_THREE)
					classesToAdd = 3;
				for (int co = 0; co < classesToAdd; co++) {
					System.out.print(courses.get(co).toString(false));
				}
			}

		}
		System.out.println(year.toString());
	}

	/**
	 * TODO: Implement method Create a Planner with the following specifications: 1.
	 * Add the minimum required classes in each cluster to a collection b.
	 * Prioritize highest preferred class 2. Check prerequisite graph b. Add any
	 * missing prereqs 3. Fill in schedule b. Prioritize classes with most pre-reqs
	 */
	public void createPlanner() {

		for (Program p : programs) {
			for (Cluster cl : p.getClusters()) {
				for (int i = 0; i < cl.getClassCount(); i++) {
					Course c = cl.getPreferedClass();
					if (!c.getClassID().equals("nocourse"))
						calander.add(c);
				}
			}
		}

	}

	/**
	 * Display the content of the calendar generated by the planner
	 * 
	 * @param verbose Include detail
	 * @return String representation of the calendar/schedule
	 */
	public String toStringCalander(boolean verbose) {
		String s = "";
		s += "Calander\n\n";
		s += "Class count : " + calander.size() + "\n";
		int count = 0;
		for (Course c : calander) {
			int sem = count - (count % 5);
			if (count % 5 == 0)
				s += String.format("Year: %d Semester: %d\n", count / 10 + 1, sem % 2 + 1);
			s += c.toString(verbose);
			count++;
		}

		return s;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSemester(Semester s) {
		this.currentSemester = s;
	}

	public void addProgram(Program p) {
		this.programs.add(p);
	}

	public void addClusters(HashSet<Cluster> clusters) {
		this.allClusters.addAll(clusters);
	}

}