package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import course_map.Cluster;
import course_map.Course;
import course_map.Program;
import course_map.Subject;
import main.Planner;

public class PreferencesSetupScreen {

	private Planner planner;

	JFrame preferencesScreen;

	JPanel mainBorder;
	JPanel midSplit;

	JPanel rightBorder;
	JPanel topRight;
	JPanel centerRight;
	JPanel bottomRight;

	JScrollPane listScroller;

	JList<Course> courseList;
	DefaultListModel<Course> courseListModel;

	public PreferencesSetupScreen(String title, Planner planner) {
		this.planner = planner;
		preferencesScreen = new JFrame();

		preferencesScreen.setSize(400, 600);
		preferencesScreen.setResizable(false);
		preferencesScreen.setLocation(200, 100);

		preferencesScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preferencesScreen.setTitle(title);
		preferencesScreen.setVisible(true);

		listSetUp();
		courseInfoSetup(null); 
		borderSetUp();

		System.out.println(planner.toString());
	}

	public void listSetUp() {
		courseListModel = new DefaultListModel<Course>();

		courseList = new JList<Course>(courseListModel);

		// courseList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		courseList.setVisibleRowCount(-1);
		listScroller = new JScrollPane(courseList);
		listScroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createLineBorder(Color.BLACK)));

		// TODO: (ALEX) I added this to put all of the clusters into the JList, needs to be
		// organized
		ArrayList<Cluster> clusters = planner.getListAllClusters();
		for (Cluster curCluster : clusters) {
			HashSet<Course> courses = curCluster.getCourses();
			for (Course curCourse : courses) {
				if (!(curCourse.getSubject() == null)) {
					courseListModel.addElement(curCourse);
				}
			}
		}
	}

	public void courseInfoSetup(Course c) {
		if (c == null) {
			c = new Course(Subject.JMU, "000", false, false, 0, 0, "Place Holder");
		}
		rightBorder = new JPanel(new GridLayout(3, 1));
		topRight = new JPanel(new GridLayout(2, 1));
		centerRight = new JPanel(new BorderLayout());
		bottomRight = new JPanel(new BorderLayout());

		centerRight.add(BorderLayout.NORTH, new JLabel("Description"));

		JLabel courseName = new JLabel("Course: " + c.getSubject() + " " + c.getClassID());
		JCheckBox courseTaken = new JCheckBox("Taken: ");
		JSlider coursePrefrence = new JSlider();
		JTextArea courseDiscription = new JTextArea(c.getDiscription());
		coursePrefrence.setMinimum(0);
		coursePrefrence.setMaximum(10);
		coursePrefrence.setValue(c.getPreference());

		centerRight.add(BorderLayout.CENTER, courseDiscription);
		topRight.add(courseName);
		topRight.add(courseTaken);
		bottomRight.add(coursePrefrence);
		courseDiscription.setLineWrap(true);

		rightBorder.add(topRight);
		rightBorder.add(centerRight);
		rightBorder.add(bottomRight);

	}

	public void borderSetUp() {
		mainBorder = new JPanel(new BorderLayout());
		midSplit = new JPanel(new GridLayout(1, 2));

		midSplit.add(listScroller);
		midSplit.add(rightBorder);

		mainBorder.add(BorderLayout.NORTH, new JLabel("PREFERENCES"));
		mainBorder.add(BorderLayout.CENTER, midSplit);

		preferencesScreen.add(mainBorder);
	}
}
