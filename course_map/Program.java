package course_map;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * A program is a part of a degree; such as a major, minor, general educations
 * 
 * @author Zeru Tadesse
 *
 */
public class Program {
	private HashSet<Cluster> clusters;
	private String name;

	/**
	 * Program Constructor with a name and a set of clusters (each contain possible
	 * classes)
	 * 
	 * @param name     Name of Program (ie: CS Major)
	 * @param clusters Collection of clusters
	 */
	public Program(String name, HashSet<Cluster> clusters) {
		this.name = name;
		this.clusters = clusters;
	}

	/**
	 * Gets detailed information of Program as a string
	 */
	public String toString() {
		String s = name.split("/")[1].split("\\.")[0];

		String s1 = s.split("\\_")[0].toUpperCase();
		String s2 = s.split("\\_")[1].substring(0, 1).toUpperCase();
		String s3 = s.split("\\_")[1].substring(1, s.split("\\_")[1].length());

		s = s1 + " " + s2 + s3;
		return s;
	}

	/**
	 * Gets information of Program as a string
	 * 
	 * @param verbose Boolean for detail if true
	 * @return
	 */
	public String toString(boolean verbose) {
		String s = "Program: " + this.name + "\n";

		for (Cluster c : clusters) {
			s += c.toString(verbose) + "\n";
		}
		return s;
	}

	/**
	 * Gets the collection of clusters in the program
	 * 
	 * @return a HashSet of clusters
	 */
	public HashSet<Cluster> getClusters() {
		return this.clusters;
	}

	/**
	 * Number of classes in the entire program
	 * 
	 * @return class count
	 */
	public int classCount() {
		int count = 0;
		for (Cluster c : this.clusters) {
			count += c.getClassCount();
		}
		return count;
	}

	/**
	 * Gets the total credits in the program TODO implement Cluster.getCreditCount()
	 * 
	 * @return the number of credits in the program
	 */
	public int creditCount() {
		int count = 0;
		for (Cluster c : this.clusters) {
			count += c.getCreditCount();
		}
		return count;
	}

	/**
	 * Sample export to String
	 * 
	 * @return String ready for file transfer
	 */
	public String export() {
		String text = "[PROGRAM]\n";

		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		clusters.addAll(this.clusters);
		for (int i = 0; i < clusters.size(); i++) {
			text += "[CLUSTER]";
			text += clusters.get(i).getName() + "," + clusters.get(i).getRule() + "\n";

			ArrayList<Course> courses = new ArrayList<Course>();
			courses.addAll(clusters.get(i).getCourses());
			for (int co = 0; co < courses.size(); co++) {
				text += "[COURSE]";
				text += courses.get(co).toString(true);

			}

		}
		return text;
	}
}
