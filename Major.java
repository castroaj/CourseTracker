import java.util.ArrayList;
import java.util.HashSet;

public class Major {

	private HashSet<Course> requiredCourses;
	private Subject subject;
	private int courseCount;
	
	public Major(Subject name)
	{
		this.subject = name;
		this.requiredCourses = new HashSet<Course>();
	}
	
	public void addCourse(Course newCourse)
	{
		requiredCourses.add(newCourse);
		courseCount++;
	}
	
	public Subject getSubject()
	{
		return subject;
	}
	
	public int getCourseCount()
	{
		return courseCount;
	}
	
	public HashSet<Course> getRequiredCourses()
	{
		return requiredCourses;
	}
	
	public ArrayList<Course> getListOfRequiredCourses()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		list.addAll(requiredCourses);
		return list;
	}
	
	
}
