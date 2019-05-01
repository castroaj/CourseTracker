import java.util.HashSet;
import java.util.Set;

public class Course {

	private HashSet<Course> myPrereqs;
	private HashSet<Course> myPostreqs;
	private HashSet<Course> myCoreqs;
	private final Subject subject;
	private final int classID;

	public Course(Subject subject, int classID) {
		this.subject = subject;
		this.classID = classID;
	}
	
	public Course(Subject sub, int classID, HashSet<Course> prereqs, HashSet<Course> postreqs, HashSet<Course> coreqs) {
		this.subject = sub;
		this.classID = classID;
		this.myPrereqs = prereqs;
		this.myPostreqs = postreqs;
		this.myCoreqs = coreqs;
	}
	
	public void addPrereq(Course newPrereq)
	{
		if (newPrereq != null)
		myPrereqs.add(newPrereq);
	}
	
	public void addPostreq(Course newPostreq)
	{
		if (newPostreq != null)
		myPostreqs.add(newPostreq);
	}
	
	public void addCoreq(Course newCoreq)
	{
		if (newCoreq != null)
		myCoreqs.add(newCoreq);
	}
	
	public HashSet<Course> getPrereqs()
	{
		return myPrereqs;
	}
	
	public HashSet<Course> getPostreqs()
	{
		return myPostreqs;
	}
	
	public HashSet<Course> getCoreqs()
	{
		return myCoreqs;
	}
	
	public Subject getSubject()
	{
		return subject;
	}
	
	public int getClassID()
	{
		return classID;
	}
	
	
}
