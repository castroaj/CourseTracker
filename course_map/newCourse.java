package course_map;

import java.util.HashSet;

public class newCourse {

	private final Subject subject;
	private final String classID;
	private String title;
	private String description;
	private int credits;
	private int preference;
	private boolean isInPlanner;

	public newCourse(String subject, String classID, String title, String description, int credits) {
		this.subject = Enum.valueOf(Subject.class, subject);
		this.classID = classID;
		this.title = title;
		this.description = description;
		this.credits = credits;
	}

}
