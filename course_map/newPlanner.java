package course_map;

import java.util.ArrayList;
import course_map.*;

public class newPlanner {
	private String name;
	private Semester semester;
	private ArrayList<Program> programs;
	private ArrayList<Cluster> clusters;
	private ArrayList<Course> courses;

	public newPlanner(String studentName, Semester currentSemester, ArrayList<Program> programs,
			int creditsToGraduate) {
		this.name = studentName;
		this.semester = currentSemester;
		for (Program p : programs) {
			add(p);
		}
	}

//========================================================//

	public void add(Program newProgram) {
		programs.add(newProgram);
		for (Cluster c : newProgram.getClusters()) {
			add(c);
		}
	}

	private void add(Cluster newCluster) {
		clusters.add(newCluster);
		for (Course c : newCluster.getCourses()) {
			if (!courses.contains(c))
				courses.add(c);
			else
				System.out.println("Course already added");
		}
	}

	public Course findCourse(Course c) {
		for (Course course : courses) {
			if (c.equals(course)) {
				return course;
			}
		}
		return null;
	}

	public Cluster findCluster(Cluster c) {
		for (Cluster cluster : clusters) {
			if (c.equals(cluster))
				return cluster;
		}
		return null;
	}

	public Program findProgram(Program p) {
		for (Program program : programs) {
			if (p.equals(program))
				return program;
		}
		return null;
	}

//========================================================//
	public int getCourseCount() {
		return courses.size();
	}

	public int getClassesTaken(Cluster c) {
		int count = 0;
		c = findCluster(c);
		for (Course course : c.getCourses()) {
			if (findCourse(course).isTaken())
				count++;
		}
		return count;
	}

//========================================================//

	public void setPreference(Course c, int pref) {
		findCourse(c).setPrefrence(pref);
	}

	public void setTaken(Course c, boolean taken) {
		findCourse(c).setTaken(taken);
	}

	// ========================================================//
	public String toString() {
		return toString(false);
	}

	public String toString(boolean verbose) {
		if (verbose) {
			return String.format("Planner: %s\nPrograms: %d\nClusters: %d\nCourses: %d", this.name,
					this.programs.size(), this.clusters.size(), this.courses.size());
		} else {
			return this.name;
		}
	}
}
