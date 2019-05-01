
public class App {
	public static void main(String[] args)
	{
		CourseGraph graph = new CourseGraph();
		Course cs159 = new Course(Subject.CS, 159);
		graph.addNode(cs159);
		System.out.println(graph.numNodes());
		
	}
}
