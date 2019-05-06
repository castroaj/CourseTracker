package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import course_map.Cluster;
import course_map.Course;
import course_map.Program;
import course_map.Subject;
import main.Planner;

public class PreferencesSetupScreen {

	private Planner planner;

	JFrame preferencesScreen;

	JPanel mainPanel;
	JPanel pcPanel;
	JPanel coursePanel;
	JPanel infoPanel;
	
	
	JPanel topRight;
	JPanel centerRight;
	JPanel bottomRight;

	JLabel courseName;
	JCheckBox courseTaken;
	JSlider coursePreference;
	JTextArea courseDescription;
	
	JScrollPane programListScroller;
	JScrollPane	clusterListScroller;
	JScrollPane courseListScroller;
		
	JList<Program> programList;
	JList<Cluster> clusterList;
	JList<Course> courseList;
	
	DefaultListModel<Program> programListModel;
	DefaultListModel<Cluster> clusterListModel;
	DefaultListModel<Course> courseListModel;

	public PreferencesSetupScreen(String title, Planner planner) {
		this.planner = planner;
		preferencesScreen = new JFrame();

		preferencesScreen.setSize(600, 700);
		preferencesScreen.setResizable(false);
		preferencesScreen.setLocation(200, 100);

		preferencesScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preferencesScreen.setTitle(title);
		preferencesScreen.setVisible(true);

		pcPanel = new JPanel();
		pcPanel.setLayout(new BoxLayout(pcPanel, BoxLayout.PAGE_AXIS));
		coursePanel = new JPanel();
		infoPanel = new JPanel(new GridLayout(3, 1));
		
		programListSetUp();
		clusterListSetUp();
		courseListSetUp();
		courseInfoSetup(); 
		panelSetUp();
		
		System.out.println(planner.toString());
		
	}
	
	public void programListSetUp()
	{
		programListModel = new DefaultListModel<Program>();
		programList = new JList<Program>(programListModel);
		
		programList.setVisibleRowCount(10);
		programListScroller = new JScrollPane(programList);
		programListScroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createLineBorder(Color.BLACK)));
		
		pcPanel.add(programListScroller);
	}
	
	public void clusterListSetUp()
	{
		clusterListModel = new DefaultListModel<Cluster>();
		clusterList = new JList<Cluster>(clusterListModel);
		
		clusterList.setVisibleRowCount(10);
		clusterListScroller = new JScrollPane(clusterList);
		clusterListScroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createLineBorder(Color.BLACK)));
	
//		ArrayList<Cluster> clusters = planner.getListAllClusters();
//		
//		for (Cluster c : clusters)
//		{
//			//clusterListModel.addElement(c);
//		}

		pcPanel.add(clusterListScroller);
	}
	
	

	public void courseListSetUp() {
		courseListModel = new DefaultListModel<Course>();
		courseList = new JList<Course>(courseListModel);
		
		courseList.setVisibleRowCount(10);
		courseList.addListSelectionListener(new CourseListListener());
		courseListScroller = new JScrollPane(courseList);
		courseListScroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
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
		//coursePanel.add(courseListScroller);
	}

	public void courseInfoSetup() {
		topRight = new JPanel(new GridLayout(2, 1));
		centerRight = new JPanel(new BorderLayout());
		bottomRight = new JPanel(new BorderLayout());

		centerRight.add(BorderLayout.NORTH, new JLabel("Description"));

		courseName = new JLabel("Course: ");
		courseTaken = new JCheckBox("Taken: ");
		coursePreference = new JSlider();
	    courseDescription = new JTextArea();
		coursePreference.setMinimum(0);
		coursePreference.setMaximum(10);

		centerRight.add(BorderLayout.CENTER, courseDescription);
		topRight.add(courseName);
		topRight.add(courseTaken);
		bottomRight.add(coursePreference);
		courseDescription.setLineWrap(true);

		infoPanel.add(topRight);
		infoPanel.add(centerRight);
		infoPanel.add(bottomRight);

	}

	public void panelSetUp() {
		mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(pcPanel, BorderLayout.WEST);
		mainPanel.add(courseListScroller, BorderLayout.CENTER);
		mainPanel.add(infoPanel, BorderLayout.EAST);

		preferencesScreen.add(mainPanel);
	}
	
	
	
	
	private class CourseListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			Course c = courseList.getSelectedValue();
			courseName.setText("Course: " + c.getSubject() + " " + c.getClassID());	
			courseTaken.setSelected(c.isTaken());
			courseDescription.setText(c.getDescription());
		}
	}
}
