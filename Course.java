import java.util.HashSet;
import java.util.Set;

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
		this.subject = subject;
		this.classID = classID;
		this.offeredFall = fall;
		this.offeredSpring = spring;
		this.isElective = isElective;
		this.requiredFor = new HashSet<Course>();
		this.myCoreqs = new HashSet<Course>();
		this.credits = 3;
	}
	public Course(Subject subject, String classID, boolean fall, boolean spring, int credits) {
		this.subject = subject;
		this.classID = classID;
		this.offeredFall = fall;
		this.offeredSpring = spring;
		this.isElective = isElective;
		this.requiredFor = new HashSet<Course>();
		this.myCoreqs = new HashSet<Course>();
		this.credits = credits;
	}
	
	public Course(Subject sub, String classID, HashSet<Course> prereqs, HashSet<Course> coreqs, boolean fall,
			boolean spring, boolean isElective) {
		this.subject = sub;
		this.classID = classID;
		this.requiredFor = prereqs;
		this.myCoreqs = coreqs;
		this.offeredFall = fall;
		this.offeredSpring = spring;
		this.isElective = isElective;
		this.credits = 3;

	}
	
	public void addPostreq(Course newPostreq) {
		if (newPostreq != null)
			requiredFor.add(newPostreq);
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

		String s = "\t" + this.subject + this.classID + " (" + this.credits + ") Offered in ";
		if (offeredFall) {
			s += "FALL";
		}
		if (offeredFall && offeredSpring) {
			s += " and ";
		}
		if (offeredSpring) {
			s += "SPRING";
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
		}
		return s + "\n";
	}

	public String toStringSimple() {
		String s =  this.subject + this.classID + "(" + this.credits +")";
		return s;
	}
}
