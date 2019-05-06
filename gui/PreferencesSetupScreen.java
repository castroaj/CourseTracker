package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
	
	JLabel programLabel;
	JLabel clusterLabel;
	JLabel courseLabel;

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
	
	Course currentCourse;

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
		coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.PAGE_AXIS));
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
		
		programLabel = new JLabel("Programs:");
		programLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
		programLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		programList.setVisibleRowCount(10);
		programList.addListSelectionListener(new ProgramListListener());
		programList.setFixedCellHeight(20);
		programList.setFixedCellWidth(200);
		programListScroller = new JScrollPane(programList);
		programListScroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.BLACK, 2)));
		
		ArrayList<Program> programs = planner.getPrograms();
		
		for (Program p : programs)
		{
			programListModel.addElement(p);
		}
		
		programListScroller.setBackground(preferencesScreen.getBackground());
		
		pcPanel.add(programLabel);
		pcPanel.add(programListScroller);
	}
	
	public void clusterListSetUp()
	{
		clusterListModel = new DefaultListModel<Cluster>();
		clusterList = new JList<Cluster>(clusterListModel);
		
		clusterLabel = new JLabel("Clusters:");
		clusterLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
		clusterLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		clusterList.setVisibleRowCount(10);
		clusterList.addListSelectionListener(new ClusterListListener());
		clusterList.setFixedCellHeight(20);
		clusterList.setFixedCellWidth(200);
		clusterListScroller = new JScrollPane(clusterList);
		clusterListScroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.BLACK, 2)));

		clusterListScroller.setBackground(preferencesScreen.getBackground());
		
		pcPanel.add(clusterLabel);
		pcPanel.add(clusterListScroller);
	}
	
	

	public void courseListSetUp() {
		courseListModel = new DefaultListModel<Course>();
		courseList = new JList<Course>(courseListModel);
		
		courseLabel = new JLabel("Courses:");
		courseLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
		courseLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		courseList.setVisibleRowCount(10);
		courseList.addListSelectionListener(new CourseListListener());
		courseList.setFixedCellHeight(20);
		courseList.setFixedCellWidth(100);
		courseListScroller = new JScrollPane(courseList);
		courseListScroller.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.BLACK, 2)));
		
		courseListScroller.setBackground(preferencesScreen.getBackground());
		
		coursePanel.add(courseLabel);
		coursePanel.add(courseListScroller);
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
		mainPanel.add(coursePanel, BorderLayout.CENTER);
		mainPanel.add(infoPanel, BorderLayout.EAST);

		preferencesScreen.add(mainPanel);
	}
	
	private class ProgramListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Program p = programList.getSelectedValue();
			clusterListModel.clear();
				
			HashSet<Cluster> clusters = p.getClusters();
			
			for (Cluster curCluster : clusters)
			{
				clusterListModel.addElement(curCluster);
			}
			
		}	
	}
	
	private class ClusterListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Cluster c = clusterList.getSelectedValue();
			courseListModel.clear();
			
			HashSet<Course> courses = c.getCourses();
			
			for (Course curCourse : courses)
			{
				courseListModel.addElement(curCourse);
			}
			
		}
		
	}
	
	private class CourseListListener implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (courseListModel.size() > 0)
			{
				if (courseList.getSelectedValue().getSubject() != null)
				{
					currentCourse = courseList.getSelectedValue();
					courseName.setText("Course: " + currentCourse.getSubject() + " " + currentCourse.getClassID());
					courseTaken.setSelected(currentCourse.isTaken());
					courseDescription.setText(currentCourse.getDescription());
				}
			}
		}
	}
}
