import java.util.HashSet;

//IE: Choose one(or more) from this list
public class Cluster {
	private String name;

	private Rule rule;
	private HashSet<Course> class_list;

	public Cluster() {
		this("NoClusterName", Rule.TAKE_ALL, null);
	}

	public Cluster(String name, Rule rule, HashSet<Course> classes) {
		this.name = name;
		this.rule = rule;
		if (classes == null) {
			classes = new HashSet<Course>();
		}
		this.class_list = classes;
	}

	public void add(Course course) {
		this.class_list.add(course);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

}
