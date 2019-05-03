package graph;
import course_map.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CourseGraph implements Graph {

  private HashMap<Course, HashSet<Course>> graph = new HashMap<Course, HashSet<Course>>();
  private HashMap<Course, HashSet<Course>> inDegrees = new HashMap<Course, HashSet<Course>>();
  private int numEdges = 0;

  @Override
  public void addNode(Course id) {
    if (!graph.containsKey(id)) {
      graph.put(id, new HashSet<Course>());
      inDegrees.put(id, new HashSet<Course>());
    }
  }

  @Override
  public void addEdge(Course from, Course to) {
    if (!from.equals(to)) {
      if (!graph.containsKey(from)) {
        addNode(from);
      }
      if (!graph.containsKey(to)) {
        addNode(to);
      }

      if (!graph.get(from).contains(to)) {
        graph.get(from).add(to);
        inDegrees.get(to).add(from);
        numEdges++;
      }
    }
  }

  @Override
  public boolean hasEdge(Course from, Course to) {
    if (graph.size() > 0) {
      return graph.get(from).contains(to);
    }
    return false;
  }

  @Override
  public void removeNode(Course id) {
    if (graph.get(id) != null) {
      for (Course cur : inDegrees.get(id)) {
        if (graph.get(cur) != null) {
          removeEdge(cur, id);
        }
      }
      numEdges = numEdges - graph.get(id).size();
      graph.remove(id);
      inDegrees.remove(id);
    }
  }

  @Override
  public void removeEdge(Course from, Course to) {
    if (hasEdge(from, to)) {
      graph.get(from).remove(to);
      numEdges--;
    }
  }

  @Override
  public Set<Course> neighbors(Course id) {
    return Collections.unmodifiableSet(graph.get(id));
  }

  @Override
  public Set<Course> allNodes() {
    return Collections.unmodifiableMap(graph).keySet();
  }

  @Override
  public int numNodes() {
    return graph.size();
  }

  @Override
  public int numEdges() {
    return numEdges;
  }
}
