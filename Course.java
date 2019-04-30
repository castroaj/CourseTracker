import java.util.HashSet;
import java.util.Set;

public class Course {

	private HashSet<Course> prereqs;
	private HashSet<Course> postreqs;
	private HashSet<Course> coreqs;
	private final Subject sub;
	private final int ID;

	public Course(Subject sub, int id) {
		this.sub = sub;
		this.ID = id;
	}
	
	public Course(Subject sub, int id, HashSet<Course> prereqs, HashSet<Course> postreqs, HashSet<Course> coreqs) {
		this(Subject.CS,101);
		this.prereqs = prereqs;
		this.postreqs = postreqs;
		this.coreqs = coreqs;
	}
}
