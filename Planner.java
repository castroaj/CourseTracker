import java.util.HashSet;

public class Planner {

	private Major major;
	private String name;
	private int year;
	private HashSet<Course> coursesTaken;
	private int credits;
	private CourseGraph courseGraph;

	public Planner(Major major) {
		this(major, "NoName", 0, 0, new HashSet<Course>());
//		this.major = major;
//		this.name = "";
//		this.credits = 0;
//		this.year = 0;
//		coursesTaken = new HashSet<Course>();
//		courseGraph = new CourseGraph();

	}

	public Planner(Major major, String name, int credits, int year, HashSet<Course> coursesTaken) {
		this.major = major;
		this.name = name;
		this.credits = credits;
		this.coursesTaken = coursesTaken;
		this.year = year;
		courseGraph = new CourseGraph();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setMajor(Major newMajor) {
		this.major = newMajor;
	}

	public Major getMajorObject() {
		return this.major;
	}

	public Subject getMajorSubject() {
		return this.major.getSubject();
	}

}
