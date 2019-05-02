import java.util.ArrayList;
import java.util.HashSet;

//IE: Choose one(or more) from this list
/**
 * A collection of courses containing a rule for fulfilling the requirement IE:
 * GENED Cluster 1, CS Systems elective...
 * 
 * @author Zeru Tadesse
 *
 */
public class Cluster {
	private String name;

	private Rule rule;
	private HashSet<Course> class_list;

	/**
	 * Default constructor, invalid name, must take all, no classes added
	 */
	public Cluster() {
		this("NoClusterName", Rule.TAKE_ALL, null);
	}

	/**
	 * Simple constructor for a given name, with rule take one, and an empty course
	 * collection
	 * 
	 * @param name Name of the Cluster (IE: CS Elective, CS Calculus, Cluster 5.1
	 *             Wellness Domain..etc
	 */
	public Cluster(String name) {
		this(name, Rule.TAKE_ONE, new HashSet<Course>());
	}

	/**
	 * Complete constructor
	 * 
	 * @param name    Name of cluster.
	 * @param rule    Rule for constructor.
	 * @param classes Classes to add in collection.
	 */
	public Cluster(String name, Rule rule, HashSet<Course> classes) {
		this.name = name;
		this.rule = rule;
		if (classes == null) {
			classes = new HashSet<Course>();
		}
		this.class_list = classes;
	}

	/**
	 * Add a course to the cluster
	 * 
	 * @param course course to add.
	 */
	public void add(Course course) {
		this.class_list.add(course);
	}

	/**
	 * Get the name of the cluster
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the cluster. IE: CS,CIS
	 * 
	 * @param name new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the Rule Enum, IE: Take one, take all
	 * 
	 * @return
	 */
	public Rule getRule() {
		return rule;
	}

	/**
	 * Set the rule enum, IE: Take one, take two
	 * 
	 * @param rule
	 */
	public void setRule(Rule rule) {
		this.rule = rule;
	}

	/**
	 * Get the collection of courses in the cluster
	 * 
	 * @return HashSet of courses
	 */
	public HashSet<Course> getCourses() {
		return this.class_list;
	}

	/**
	 * String representation of the cluster
	 * 
	 * @param verbose T: full detail F: summary
	 * @return String
	 */
	public String toString(boolean verbose) {
		String s = "Cluster: " + this.name + "\tRule: " + this.rule + "\tSize: " + this.class_list.size() + "\n";
		for (Course c : this.class_list) {
			s += c.toString(verbose);
		}
		return s;
	}

	/**
	 * Default toString, shows full detail
	 */
	public String toString() {
		return toString(true);
	}
}
