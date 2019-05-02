import java.util.HashSet;
import java.util.Set;

/**
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

//	public Course(Subject subject, String classID, boolean fall, boolean spring, boolean isElective) {
//		this.subject = subject;
//		this.classID = classID;
//		this.offeredFall = fall;
//		this.offeredSpring = spring;
//		this.isElective = isElective;
//		this.requiredFor = new HashSet<Course>();
//		this.myCoreqs = new HashSet<Course>();
//		this.credits = 3;
//	}

	public Course(Subject subject, String classID, boolean fall, boolean spring) {

		this(subject, classID, fall, spring, 3);
	}

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

	public void addPostreq(Course newPostreq) {
		if (newPostreq != null) {
			requiredFor.add(newPostreq);
			this.priority++;
		}
	}

	public void addCoreq(Course newCoreq) {
		if (newCoreq != null)
			myCoreqs.add(newCoreq);
	}

	public HashSet<Course> getRequiredFor() {
		return requiredFor;
	}

	public HashSet<Course> getCoreqs() {
		return myCoreqs;
	}

	public Subject getSubject() {
		return subject;
	}

	public String getClassID() {
		return classID;
	}

	public boolean equals(Course o) {
		return this.subject.equals(o.subject) && this.classID == o.classID;
	}

	public String toString() {
		return toString(true);
	}

	public String toString(boolean verbose) {
		if (verbose) {
			String s = "\t[" + String.format("%02d", this.priority) + "]" + this.subject + this.classID + " ("
					+ this.credits + ") Offered in ";
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

	public String toStringSimple() {
		String s = this.subject + this.classID;
		return s;
	}
}
