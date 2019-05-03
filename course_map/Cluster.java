package course_map;

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

	public int getClassCount() {
		int count = 0;
		if (this.rule == Rule.TAKE_ONE)
			count = 1;
		if (this.rule == Rule.TAKE_TWO)
			count = 2;
		if (this.rule == Rule.TAKE_THREE)
			count = 3;
		if (this.rule == Rule.TAKE_ALL)
			count = this.class_list.size();
		return count;
	}

	public int getCreditCount() {
		return 0; // TODO: not really doable but maybe
	}

	/**
	 * String representation of the cluster
	 * 
	 * @param verbose T: full detail F: summary
	 * @return String
	 */
	public String toString(boolean verbose) {

		String s = String.format("Cluster: %-25s\tRule: %-15s\tSize: %2d\n", this.name, this.rule,
				this.class_list.size());
		if (verbose) {
			for (Course c : this.class_list) {
				s += c.toString(verbose);
			}
		}
		return s;

	}

	/**
	 * Default toString, shows full detail
	 */
	@Override
	public String toString() {
		return toString(true);
	}
}
