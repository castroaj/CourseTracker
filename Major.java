import java.util.HashSet;

public class Major {

	private HashSet<Course> requiredCourses;
	private Subject name;
	private int courseCount;
	
	public Major(Subject name)
	{
		this.name = name;
		this.requiredCourses = new HashSet<Course>();
	}
	
	public void addCourse(Course newCourse)
	{
		requiredCourses.add(newCourse);
		courseCount++;
	}
	
	public Subject getName()
	{
		return name;
	}
	
	public int getCourseCount()
	{
		return courseCount;
	}
	
	
}
