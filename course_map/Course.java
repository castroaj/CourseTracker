package course_map;
import java.util.HashSet;

/**
 * Course object ie: CS101, CS149, ECON200..etc Every course object contains a
 * collection of its coreqs and outgoing nodes
 * 
 * @author Alex Castro, Zeru Tadesse
 *
 */
public class Course {

	private HashSet<Course> requiredFor;
	private HashSet<Course> myCoreqs;
	private final Subject subject;
	private final String classID;
	private boolean classTaken;
	private int credits;
	private boolean offeredFall;
	private boolean offeredSpring;
	private boolean isElective;
	private int priority;

	/**
	 * Course constructor with default of 3 credits
	 * 
	 * @param subject Class subject
	 * @param classID Class number
	 * @param fall    Offered in fall (t/f)
	 * @param spring  Offered in spring (t/f)
	 */
	public Course(Subject subject, String classID, boolean fall, boolean spring) {
		this(subject, classID, fall, spring, 3);
	}

	/**
	 * Course constructor with explicit credits
	 * 
	 * @param subject Class subject
	 * @param classID Class number
	 * @param fall    Offered in fall (t/f)
	 * @param spring  Offered in spring (t/f)
	 * @param credits Number of credits
	 */
	public Course(Subject subject, String classID, boolean fall, boolean spring, int credits) {
		this.subject = subject;
		this.classID = classID;
		this.offeredFall = fall;
		this.offeredSpring = spring;
		this.requiredFor = new HashSet<Course>();
		this.myCoreqs = new HashSet<Course>();
		this.credits = credits;
		this.priority = 0;
	}

	/**
	 * Add outgoing node for classes that requrie this one
	 * 
	 * @param newPostreq this class is a prereq for newPostReq
	 */
	public void addPostreq(Course newPostreq) {
		if (newPostreq != null) {
			requiredFor.add(newPostreq);
			this.priority++;
		}
	}

	/**
	 * Add coreq node, for classes that can be taken together
	 * 
	 * @param newCoreq
	 */
	public void addCoreq(Course newCoreq) {
		if (newCoreq != null)
			myCoreqs.add(newCoreq);
	}

	/**
	 * Get HashSet of PostReq / RequiredFor classes
	 * 
	 * @return Returns a collection of courses.
	 */
	public HashSet<Course> getRequiredFor() {
		return requiredFor;
	}

	/**
	 * Get HashSet of Coreqs
	 * 
	 * @return Returns a collection of courses.
	 */
	public HashSet<Course> getCoreqs() {
		return myCoreqs;
	}

	/**
	 * Get Subject type, ie CS CIS ECON.
	 * 
	 * @return Subject enum.
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Get the class ID or number.
	 * 
	 * @return class ID as String.
	 */
	public String getClassID() {
		return classID;
	}

	/**
	 * Evaluates equals
	 * 
	 * @param o other course
	 * @return if courses are equivalent
	 */
	public boolean equals(Course o) {
		return this.subject.equals(o.subject) && this.classID == o.classID;
	}

	/**
	 * Default to string with full detail of course
	 */
	public String toString() {
		return toString(true);
	}

	/**
	 * To string with or without detail
	 * 
	 * @param verbose boolean for detail.
	 * @return String format of the course.
	 */
	public String toString(boolean verbose) {
		if (verbose) {
			String s = "\t[" + String.format("%02d", this.priority) + "]" + this.subject + this.classID + " ("
					+ this.credits + ") Offered in ";
			s = String.format("\t[%02d]%-5s%-5s (%d) Offered:  ", this.priority,this.subject,this.classID,this.credits);
			if (offeredFall) {
				s += "Fa";
			}
			if (offeredFall && offeredSpring) {
				s += "-";
			}
			if (offeredSpring) {
				s += "Sp";
			}
			if (!offeredFall || !offeredSpring) {
				s += "\t";
			}
			if (requiredFor.size() > 0) {
				s += " \tRequired for: ";
				for (Course c : requiredFor) {
					s += c.toStringSimple();
					if (requiredFor.size() > 1)
						s += ", ";

				}
				if (requiredFor.size() > 1) {
					s = s.substring(0, s.length() - 2);

				}

			}
			return s + "\n";
		} else {
			return "\t" + toStringSimple() + "\n";
		}

	}

	/**
	 * Get the simplified version of to String
	 * 
	 * @return The Subject and Class id IE: CS101
	 */
	public String toStringSimple() {
		String s = this.subject + this.classID;
		return s;
	}
}
