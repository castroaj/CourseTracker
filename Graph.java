import java.util.Set;

public interface Graph {

  void addNode(Course id);
  void addEdge(Course from, Course to);
  boolean hasEdge(Course from, Course to);
  void removeNode(Course id);
  void removeEdge(Course from, Course to);
  Set<Course> neighbors(Course id);
  Set<Course> allNodes();
  int numNodes();
  int numEdges();

}