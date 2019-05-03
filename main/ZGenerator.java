package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import graph.*;
import gui.*;
import main.*;
import temp.*;
import course_map.*;
public class ZGenerator {

	private static Course tempCourse = new Course(Subject.CS, "000", false, false);
	private static Course cs101 = new Course(Subject.CS, "101", true, true);
	private static Course cs149 = new Course(Subject.CS, "149", true, true);
	private static Course cs159 = new Course(Subject.CS, "159", true, true);
	private static Course cs227 = new Course(Subject.CS, "227", true, true);
	private static Course cs240 = new Course(Subject.CS, "240", true, true);
	private static Course cs260 = new Course(Subject.CS, "260", true, true);
	private static Course cs261 = new Course(Subject.CS, "261", true, true);
	private static Course cs280 = new Course(Subject.CS, "280", true, true, 1);
	private static Course cs327 = new Course(Subject.CS, "327", true, true);
	private static Course cs330 = new Course(Subject.CS, "330", true, true);
	private static Course cs345 = new Course(Subject.CS, "345", true, true);
	private static Course cs347 = new Course(Subject.CS, "347", true, false);
	private static Course cs349 = new Course(Subject.CS, "349", true, true);
	private static Course cs354 = new Course(Subject.CS, "354", true, false);
	private static Course cs361 = new Course(Subject.CS, "361", true, true);
	private static Course cs430 = new Course(Subject.CS, "430", false, true);
	private static Course cs432 = new Course(Subject.CS, "432", true, false);
	private static Course cs442 = new Course(Subject.CS, "442", false, true);
	private static Course cs444 = new Course(Subject.CS, "444", false, true);
	private static Course cs446 = new Course(Subject.CS, "446", false, true);
	private static Course cs447 = new Course(Subject.CS, "447", true, false);
	private static Course cs448 = new Course(Subject.CS, "448", true, false);
	private static Course cs449 = new Course(Subject.CS, "449", true, false);
	private static Course cs450 = new Course(Subject.CS, "450", true, false);
	private static Course cs452 = new Course(Subject.CS, "452", false, true);
	private static Course cs456 = new Course(Subject.CS, "456", false, true);
	private static Course cs457 = new Course(Subject.CS, "457", true, false);
	private static Course cs458 = new Course(Subject.CS, "458", false, true);
	private static Course cs461 = new Course(Subject.CS, "461", false, true);
	private static Course cs462 = new Course(Subject.CS, "462", false, true);
	private static Course cs470 = new Course(Subject.CS, "470", false, true);
	private static Course cs474 = new Course(Subject.CS, "474", false, true);
	private static Course cs475 = new Course(Subject.CS, "475", false, true);
	private static Course cs476 = new Course(Subject.CS, "476", false, true);
	private static Course cs480 = new Course(Subject.CS, "480", true, true);
	private static Course cs482 = new Course(Subject.CS, "482", false, true);
	private static Course cs488 = new Course(Subject.CS, "442", true, true);

	private static Course math235 = new Course(Subject.MATH, "235", true, true, 4);
	private static Course stat200 = new Course(Subject.STAT, "200", true, true);

	public void createCSClasses() {
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
	}

	public static Program makeCSMajor() {

		HashSet<Cluster> cs_clusters = new HashSet<Cluster>();
		Cluster core = new Cluster("CS Core", Rule.TAKE_ALL, new HashSet<Course>());
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
		electives.add(cs101);
		electives.add(cs480);
		electives.add(cs475);
		electives.add(cs476);
		electives.add(cs330);
		electives.add(cs442);
		electives.add(cs448);
		electives.add(cs449);
		electives.add(cs347);
		electives.add(cs446);
		electives.add(cs447);
		electives.add(cs349);
		electives.add(cs354);
		electives.add(cs444);
		electives.add(cs482);
		electives.add(cs488);
		electives.add(cs457);
		electives.add(cs458);
		electives.add(cs452);
		electives.add(cs461);
		electives.add(cs462);
		// Major Other
		Cluster intro_programming = new Cluster("CS Intro to Programming", Rule.TAKE_ONE, new HashSet<Course>());
		intro_programming.add(cs149);
		Cluster intro_discrete = new Cluster("CS Intro to Discrete Math", Rule.TAKE_ONE, new HashSet<Course>());
		// math227
		intro_discrete.add(cs227);
		Cluster calculus_sequence = new Cluster("CS Calculus", Rule.TAKE_ONE, new HashSet<Course>());
		calculus_sequence.add(math235);
		// calculus_sequence.add(tempCourse);

		Cluster statistics = new Cluster("Statistics", Rule.TAKE_ONE, new HashSet<Course>());
		statistics.add(stat200);

		Cluster systems_elective = new Cluster("CS Systems Elective", Rule.TAKE_ONE, new HashSet<Course>());
		systems_elective.add(cs432);
		systems_elective.add(cs450);
		systems_elective.add(cs456);
		systems_elective.add(cs470);

		cs_clusters.add(core);
		cs_clusters.add(electives);
		cs_clusters.add(intro_programming);
		cs_clusters.add(intro_discrete);
		cs_clusters.add(calculus_sequence);
		cs_clusters.add(statistics);
		cs_clusters.add(systems_elective);
		return new Program("Computer Science", cs_clusters);
		// intro_programming.add(CS149)
	}

	public static Program makeGenED() throws IOException {

		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = Files.newBufferedReader(Paths.get("gen_ed.zagp"))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		String text = sb.toString();
		HashSet<Cluster> clusters = new HashSet<Cluster>();
		String[] clusterText = text.split("CLUSTER");
		for (int i = 1; i < clusterText.length; i++) {
			Cluster currentCluster = new Cluster(clusterText[i].split(",")[0]);
			Rule currentRule = Enum.valueOf(Rule.class, clusterText[i].split(",")[1].split("=")[0]);
			currentCluster.setRule(currentRule);
			String[] classes = clusterText[i].split("=")[1].split(";");
			for (int j = 0; j < classes.length; j++) {
				String priority = classes[j].split(",")[0];
				String className = classes[j].split(",")[1].split(" ")[0];
				String classID = classes[j].split(",")[1].split(" ")[1];
				Course currentCourse = new Course(Enum.valueOf(Subject.class, className), classID, true, true, 3);
				currentCluster.add(currentCourse);
			}
			clusters.add(currentCluster);
		}
		
		return new Program("Gen Ed", clusters);
	}

	public static void makeCISMinor() {

	}
}
