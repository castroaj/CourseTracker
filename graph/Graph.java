package graph;

import java.util.Set;

import course_map.Course;

public interface Graph {

	void addNode(Course id);

	void addEdge(Course from, Course to);

	boolean hasEdge(Course from, Course to);

	void removeNode(Course id);

	void removeEdge(Course from, Course to);

	Set<Course> outDegree(Course id);

	Set<Course> allNodes();

	int numNodes();

	int numEdges();

}