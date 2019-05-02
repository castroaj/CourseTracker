import java.util.HashSet;

public class ZGenerator {

	public static ZMajor makeCSMajor() {

		HashSet<Cluster> cs_clusters = new HashSet<Cluster>();
		// Major core
		// Course cs101 = new Course(Subject.CS, "101", true, true);
		Course cs101 = new Course(Subject.CS, "101", true, true, true);
		Course cs149 = new Course(Subject.CS, "149", true, true, false);
		Course cs159 = new Course(Subject.CS, "159", true, true, false);
		Course cs227 = new Course(Subject.CS, "227", true, true, false);
		Course cs240 = new Course(Subject.CS, "240", true, true, false);
		Course cs260 = new Course(Subject.CS, "260", true, true, false);
		Course cs261 = new Course(Subject.CS, "261", true, true, false);
		Course cs280 = new Course(Subject.CS, "280", true, true, true);
		Course cs327 = new Course(Subject.CS, "327", true, true, false);
		Course cs330 = new Course(Subject.CS, "330", true, true, true);
		Course cs345 = new Course(Subject.CS, "345", true, true, false);
		Course cs347 = new Course(Subject.CS, "347", true, false, true);
		Course cs349 = new Course(Subject.CS, "349", true, true, true);
		Course cs354 = new Course(Subject.CS, "354", true, false, true);
		Course cs361 = new Course(Subject.CS, "361", true, true, false);
		Course cs430 = new Course(Subject.CS, "430", false, true, true);
		Course cs432 = new Course(Subject.CS, "432", true, false, true);
		Course cs442 = new Course(Subject.CS, "442", false, true, true);
		Course cs444 = new Course(Subject.CS, "444", false, true, true);
		Course cs446 = new Course(Subject.CS, "446", false, true, true);
		Course cs447 = new Course(Subject.CS, "447", true, false, true);
		Course cs450 = new Course(Subject.CS, "450", true, false, true);
		Course cs452 = new Course(Subject.CS, "452", false, true, true);
		Course cs456 = new Course(Subject.CS, "456", false, true, true);
		Course cs457 = new Course(Subject.CS, "457", true, false, true);
		Course cs458 = new Course(Subject.CS, "458", false, true, true);
		Course cs461 = new Course(Subject.CS, "461", false, true, true);
		Course cs462 = new Course(Subject.CS, "462", false, true, true);
		Course cs470 = new Course(Subject.CS, "470", false, true, true);
		Course cs474 = new Course(Subject.CS, "474", false, true, false);
		Course cs475 = new Course(Subject.CS, "475", false, true, true);
		Course cs476 = new Course(Subject.CS, "476", false, true, true);
		Course cs480 = new Course(Subject.CS, "480", true, true, true);
		Course cs482 = new Course(Subject.CS, "482", false, true, true);
		Course cs488 = new Course(Subject.CS, "442", true, true, true);

		// Add requirements
		cs149.addPostreq(cs159);

		cs159.addPostreq(cs227);
		cs159.addPostreq(cs240);
		cs159.addPostreq(cs261);
		cs159.addPostreq(cs345);
		cs159.addPostreq(cs474);
		cs159.addPostreq(cs480);
		cs159.addCoreq(cs227);

		cs227.addPostreq(cs240);
		cs227.addPostreq(cs327);

		cs240.addPostreq(cs327);
		cs240.addPostreq(cs349);
		cs240.addPostreq(cs361);
		cs240.addPostreq(cs354);
		cs240.addPostreq(cs430);
		cs240.addPostreq(cs444);
		cs240.addPostreq(cs446);
		cs240.addPostreq(cs452);
		cs240.addPostreq(cs482);
		cs240.addPostreq(cs488);

		cs261.addPostreq(cs361);
		cs261.addPostreq(cs430);
		cs261.addPostreq(cs482);
		cs261.addPostreq(cs488);

		cs327.addPostreq(cs432);
		cs327.addPostreq(cs452);

		cs345.addPostreq(cs347);
		cs345.addPostreq(cs446);
		cs345.addPostreq(cs447);
		cs345.addPostreq(cs474);

		cs361.addPostreq(cs432);
		cs361.addPostreq(cs450);
		cs361.addPostreq(cs456);
		cs361.addPostreq(cs457);
		cs361.addPostreq(cs458);
		cs361.addPostreq(cs461);
		cs361.addPostreq(cs462);
		cs361.addPostreq(cs470);
		cs361.addCoreq(cs457);

		cs457.addPostreq(cs458);

		cs474.addPostreq(cs475);
		cs474.addPostreq(cs476);

		Cluster core = new Cluster("CS Core", Rule.TAKE_ALL, new HashSet<Course>());
		// co/re.add(CS159)
		core.add(cs159);
		core.add(cs240);
		core.add(cs260);
		core.add(cs261);
		core.add(cs327);
		core.add(cs345);
		core.add(cs361);
		core.add(cs430);
		core.add(cs474);
		
		

		// Major electives
		Cluster electives = new Cluster("CS Elective", Rule.TAKE_THREE, new HashSet<Course>());
		// electives.add(CS444)
		electives.add(cs444);
		// Major Other
		Cluster intro_programming = new Cluster("CS Intro to Programming", Rule.TAKE_ONE, new HashSet<Course>());
		//cs139
		intro_programming.add(cs149);
		Cluster intro_discrete = new Cluster("CS Intro to Discrete Math", Rule.TAKE_ONE, new HashSet<Course>());
		//math227
		intro_discrete.add(cs227);
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
