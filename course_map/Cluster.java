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

	/**
	 * Get the total number of classes needed for this cluster
	 * 
	 * @return number of classes.
	 */
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

	/**
	 * Evaluate the class in class_list that should be taken based on the following:
	 * 
	 * 1.Finds the highest (personally) rated class. 
	 * 2. If there is a tie it will
	 * pick the one offered in the most semesters 
	 * 3. If there is another tie it will pick the first course found
	 * 
	 * @return highest priority class that has not been taken
	 */
	public Course getPreferedClass() {
		int highestRatedScore = -1;
		Course tempCourse = new Course(null, "nocourse", false, false);
		for (Course c : class_list) {
			if (c.isTaken() == false) {
				if (c.getPreference() == highestRatedScore) {
					if (c.getOfferedCount() > tempCourse.getOfferedCount()) {
						if (c.getClassID().equals("coursenotfoundindatabase")) {
						} else {
							tempCourse = c;
							highestRatedScore = c.getPreference();
							// c.takeClass();
						}
					}

				}
				if (c.getPreference() > highestRatedScore) {
					if (c.getClassID().equals("coursenotfoundindatabase")) {
					} else {
						tempCourse = c;
						highestRatedScore = c.getPreference();
						// c.takeClass();
					}
				}
			}
		}
		return tempCourse;
	}

	/**
	 * TODO: generate method, might not be possible due to variance Gets the number
	 * of credits
	 * 
	 * @return
	 */
	public int getCreditCount() {
		return 0;
	}

	/**
	 * String representation of the cluster
	 * 
	 * @param verbose T: full detail F: summary
	 * @return String
	 */
	public String toString(boolean verbose) {

		String s = String.format("Cluster: %-25s\tRule: %-10s\tSize: %2d\n", this.name, this.rule,
				this.class_list.size());
		try {
			s += String.format("\t\tPreferred Class: %s %-3s\n", getPreferedClass().getSubject().toString(),
					getPreferedClass().getClassID());
		} catch (NullPointerException e) {
			s += "No Preffered Class\n";
		}
		if (verbose) {
			int count = 0;
			for (Course c : this.class_list) {
				// if (count < getClassCount()) //This condition only prints as many as the
				// cluster needs
				s += c.toString(verbose);
				count++;
			}
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
