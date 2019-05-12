package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import course_map.Cluster;
import course_map.Course;
import main.Planner;

public class PlannerScreen {

	JFrame plannerScreen;
	Planner planner;
	
	JPanel mainPanel;
	JPanel menuPanel;
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel reqViewer;
	JPanel courseSearch;
	JPanel bottomPanel;
	
	JMenuBar menu;
	
	JMenu file;
	JMenuItem loadPlanner;
	JMenuItem savePlanner;
	
	JMenu apps;
	JMenuItem preferences;
	//TODO
	
	JMenu help;
	JMenuItem wiki;
	
	JMenu other;
	JMenuItem aboutCreators;
	JMenuItem aboutProgram;
	
	JList<Cluster> clusterViewer;
	JList<Course> courseViewer;
	DefaultListModel<Cluster> clusterModel;
	DefaultListModel<Course> courseModel;
	
	HashSet<Cluster> remainingClusters;
	HashSet<Cluster> completedClusters;
	
	
	public PlannerScreen(String title, Planner planner)
	{
		this.planner = planner;
		plannerScreen = new JFrame();
		
		makeGUI();
		

		plannerScreen.setSize(1000, 800);
		plannerScreen.setResizable(false);
		plannerScreen.setLocation(100, 100);
		plannerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plannerScreen.setTitle(title);
		plannerScreen.setVisible(true); 
	}
	
	public void makeGUI() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		plannerScreen.add(mainPanel);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		leftPanel.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());

		
		setupMenu();
		setupReqViewer();

	}
	
	public void setupMenu()
	{
		menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		menu = new JMenuBar();
		file = new JMenu("File");
		file.setFont(new Font("Monospaced", Font.PLAIN, 12));
		apps = new JMenu("Applications");
		apps.setFont(new Font("Monospaced", Font.PLAIN, 12));
		help = new JMenu("Help");
		help.setFont(new Font("Monospaced", Font.PLAIN, 12));
		other = new JMenu("Other");
		other.setFont(new Font("Monospaced", Font.PLAIN, 12));

		
		loadPlanner = new JMenuItem("Load a Planner");
		loadPlanner.addActionListener(new MenuActionListener());
		loadPlanner.setFont(new Font("Monospaced", Font.PLAIN, 12));
		savePlanner = new JMenuItem("Save Planner");
		savePlanner.addActionListener(new MenuActionListener());
		savePlanner.setFont(new Font("Monospaced", Font.PLAIN, 12));

		preferences = new JMenuItem("Course Preferences");
		preferences.addActionListener(new MenuActionListener());
		preferences.setFont(new Font("Monospaced", Font.PLAIN, 12));

		wiki = new JMenuItem("Open Program Wiki");
		wiki.addActionListener(new MenuActionListener());
		wiki.setFont(new Font("Monospaced", Font.PLAIN, 12));

		aboutProgram = new JMenuItem("About Program");
		aboutProgram.addActionListener(new MenuActionListener());
		aboutProgram.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		aboutCreators = new JMenuItem("Creators");
		aboutCreators.addActionListener(new MenuActionListener());
		aboutCreators.setFont(new Font("Monospaced", Font.PLAIN, 12));

		file.add(loadPlanner);
		file.add(savePlanner);
		
		apps.add(preferences);
		
		help.add(wiki);
		
		other.add(aboutProgram);
		other.add(aboutCreators);

		
		menu.add(file);
		menu.add(apps);
		menu.add(help);
		menu.add(other);
		menuPanel.add(menu);
		mainPanel.add(menuPanel, BorderLayout.NORTH);
	}
	
	private void setupReqViewer()
	{
		reqViewer = new JPanel();
		clusterModel = new DefaultListModel<Cluster>();
		clusterViewer = new JList<Cluster>(clusterModel);
		HashSet<Cluster> allClusters = planner.getAllClusters();
		remainingClusters = new HashSet<Cluster>();
		completedClusters = new HashSet<Cluster>();
		for (Cluster c : allClusters)
		{
			if (c.getIsComplete())
			{
				completedClusters.add(c);
			}
			else
			{
				clusterModel.addElement(c);
				remainingClusters.add(c);
			}
		}
		
		reqViewer.add(clusterViewer);
		rightPanel.add(reqViewer, BorderLayout.SOUTH);
	}
	
	
	private class MenuActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			switch (command)
			{
				case "Load a Planner":
					break;
				case "Save Planner":
					break;
				case "Course Preferences":
					new ZPreff("Preferences", planner, false);
					break;
					
				case "Open Program Wiki":
					try {
						 URI uri= new URI("https://github.com/castroaj/CourseTracker/wiki#how-to-use");
						 java.awt.Desktop.getDesktop().browse(uri);
						 } catch (Exception e1) {
						 e1.printStackTrace();
						 }
					break;
					
				case "Creators":
					JOptionPane.showMessageDialog(new JFrame(), "This application was created by: \nAlex Castro\nZeru Tadesse\nGarrett Christian", "About Creators", 1);
					break;
				case "About Program":
					break;
			}
			
		}
		
	}
	
	
	
}
