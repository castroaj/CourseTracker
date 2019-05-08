package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import course_map.*;

public class Generator {
	private static String courseDatabase;

	public static void loadCourseDatabase(String file) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		courseDatabase = sb.toString().replaceAll("\"", "");
		// System.out.println(courseDatabase);
	}

	public static Course findCourse(String subject, String number) {
		Course course = new Course(null, "CourseNotFound: " + subject + number, false, false);
		String[] list = courseDatabase.split("\n");
		for (int i = 1; i < list.length; i++) {
			String[] fields = list[i].split(",");
			// System.out.println(list[i]);
			if (fields[1].equals(subject) && fields[2].equals(number)) {
				// Subject subject, String classID, boolean fall, boolean spring, int credits,
				// int preference
				Subject newSubject = Enum.valueOf(Subject.class, fields[1]);

				String newClassID = fields[2];
				boolean newFall = fields[5].equals("T") || fields[5].equals("M");
				boolean newSpring = fields[6].equals("T") || fields[6].equals("M");
				int newCredits = Integer.parseInt(fields[4]);
				int newPreference = Integer.parseInt(fields[11]);
				String title = fields[9];
				String discription = fields[10];
				discription = discription.replace(";", ","); //reverts commas (excel delimits with commas)
				// Optional Fields TODO: add prereqs to list
				for (int f = 1; f < fields.length; f++) {

				}
				course = new Course(newSubject, newClassID, newFall, newSpring, newCredits, newPreference, title,
						discription);
				return course;
			}
		}
		return course;
	}

	public static Program loadProgram(String file) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		String fileText = sb.toString();
		fileText = fileText.replaceAll("\"", "");

		String[] lines = fileText.split("\n");
		HashSet<Cluster> clusters = new HashSet<Cluster>();
		for (int i = 1; i < lines.length; i++) {
			String[] fields = lines[i].split(",");
			String newName = fields[0];
			HashSet<Course> newCourses = new HashSet<Course>();
			Rule rule = Enum.valueOf(Rule.class, fields[1]);

			for (int f = 2; f < fields.length; f++) {
				if (fields[f].length() > 2) {
					// System.out.println(fields[f].split(" ")[1]);
					newCourses.add(findCourse(fields[f].split(" ")[0], fields[f].split(" ")[1]));
					// If you get a null error here, uncomment code above (Usually a class with no
					// space ie: CS159, not CS 159)
				}
			}
			Cluster newCluster = new Cluster(newName, rule, newCourses);
			clusters.add(newCluster);

		}

		return new Program(file, clusters);
	}

}
