import java.util.HashSet;

public class ZGenerator {

	public static ZMajor makeCSMajor() {

		HashSet<Cluster> cs_clusters = new HashSet<Cluster>();
		// Major core
		Course cs101 = new Course(Subject.CS, "101", true, true);
		Cluster core = new Cluster("CS Core", Rule.TAKE_ALL, new HashSet<Course>());
		// co/re.add(CS159)
		core.add(cs101);

		// Major electives
		Cluster electives = new Cluster("CS Elective", Rule.TAKE_THREE, new HashSet<Course>());
		// electives.add(CS444)

		// Major Other
		Cluster intro_programming = new Cluster("CS Intro to Programming", Rule.TAKE_ONE, new HashSet<Course>());
		Cluster intro_discrete = new Cluster("CS Intro to Discrete Math", Rule.TAKE_ONE, new HashSet<Course>());
		Cluster calculus_sequence = new Cluster("CS Calculus", Rule.TAKE_ONE, new HashSet<Course>());
		Cluster statistics = new Cluster("Statistics", Rule.TAKE_ONE, new HashSet<Course>());
		Cluster systems_elective = new Cluster("CS Systems Elective", Rule.TAKE_ONE, new HashSet<Course>());
		cs_clusters.add(core);
		cs_clusters.add(electives);
		cs_clusters.add(intro_programming);
		cs_clusters.add(intro_discrete);
		cs_clusters.add(calculus_sequence);
		cs_clusters.add(statistics);
		cs_clusters.add(systems_elective);
		return new ZMajor("Computer Science", cs_clusters);
		// intro_programming.add(CS149)
	}

	// C1CT,C1HC,,C1W,C2HQC,Q2VPA,C2L,C4AE,C4GE,C5W,C5SD
	public static void makeGenED() {
		// Default is take one with new hashset
		Cluster C1CT = new Cluster("Critical Thinking");
		Cluster C1HC = new Cluster("Human Communication");
		Cluster C1W = new Cluster("Writing");
		Cluster C2HQC = new Cluster("Human Questions and Contexts");
		Cluster C2VPA = new Cluster("Visual and Performing Arts");
		Cluster C2L = new Cluster("Literature");
		Cluster C3QR = new Cluster("Quantitative Reasoning");
		Cluster C3PP = new Cluster("Physical Principles");
		Cluster C3NS = new Cluster("Natural Systems");
		Cluster C4AE = new Cluster("The American Experience");
		Cluster C4GE = new Cluster("The Global Experience");
		Cluster C5W = new Cluster("Wellness Domain");
		Cluster C5SD = new Cluster("Sociocultural Domain");
	}

	public static void makeCISMinor() {

	}
}
