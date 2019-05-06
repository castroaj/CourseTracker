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
	private int preference;
	private String description;

	/**
	 * Course constructor with default of 3 credits and prefrence of 5 (no pref)
	 * 
	 * @param subject Class subject
	 * @param classID Class number
	 * @param fall    Offered in fall (t/f)
	 * @param spring  Offered in spring (t/f)
	 * 
	 */
	public Course(Subject subject, String classID, boolean fall, boolean spring) {
		this(subject, classID, fall, spring, 3, 5, "No discription");
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
		this(subject, classID, fall, spring, credits, 5, "no discription");
	}

	/**
	 * Course constructor with explicit credits
	 * 
	 * @param subject   Class subject
	 * @param classID   Class number
	 * @param fall      Offered in fall (t/f)
	 * @param spring    Offered in spring (t/f)
	 * @param credits   Number of credits
	 * @param prefrence Ranking of preference 0-10
	 */
	public Course(Subject subject, String classID, boolean fall, boolean spring, int credits, int prefrence,
			String description) {
		this.subject = subject;
		this.classID = classID;
		this.offeredFall = fall;
		this.offeredSpring = spring;
		this.requiredFor = new HashSet<Course>();
		this.myCoreqs = new HashSet<Course>();
		this.credits = credits;
		this.preference = prefrence;
		this.classTaken = false;
		this.description = description;
	}

	/**
	 * Add outgoing node for classes that requrie this one
	 * 
	 * @param newPostreq this class is a prereq for newPostReq
	 */
	public void addPostreq(Course newPostreq) {
		if (newPostreq != null) {
			requiredFor.add(newPostreq);
			this.preference++;
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
	 * Get the preference for this class (0: Don't include, 10 Must include)
	 * 
	 * @return
	 */
	public int getPreference() {
		return this.preference;
	}

	/**
	 * Change the preference of a class 0=least preferred 10 = most preferred
	 * 
	 * @param preference
	 */
	public void setPrefrence(int preference) {
		this.preference = preference;
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
		return String.format("%s %s", this.subject, this.classID);
	}

	/**
	 * To string with or without detail
	 * 
	 * @param verbose boolean for detail.
	 * @return String format of the course.
	 */
	public String toString(boolean verbose) {
		if (verbose) {
			String s = "\t[" + String.format("%02d", this.preference) + "]" + this.subject + this.classID + " ("
					+ this.credits + ") Offered in ";
			s = String.format("\t[%02d]%-5s%-5s (%d) Offered:  ", this.preference, this.subject, this.classID,
					this.credits);
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
	 * Boolean condition if class has been taken already
	 * 
	 * @return
	 */
	public boolean isTaken() {
		return this.classTaken;
	}

	/**
	 * Change the status of the class to taken = true
	 */
	public void takeClass() {
		this.classTaken = true;
	}

	/**
	 * Gets the number of time a course is offered
	 * @return
	 */
	public int getOfferedCount() {
		int x = 0;
		if (offeredFall)
			x++;
		if (offeredSpring)
			x++;
		return x;
	}

	/**
	 * Get the simplified version of to String
	 * 
	 * @return The Subject and Class id IE: CS101
	 */
	public String toStringSimple() {
		return String.format("%-5s %-3s", this.subject, this.classID);
	}

	public String getDescription() {
		return this.description;
	}
}
