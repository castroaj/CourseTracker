package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import main.Planner;

public class PlannerScreen {

	JFrame plannerScreen;
	Planner planner;
	
	JPanel mainPanel;
	JPanel menuPanel;
	
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
		plannerScreen.add(mainPanel);
		
		setupMenu();
		

	}
	
	public void setupMenu()
	{
		menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		menu = new JMenuBar();
		file = new JMenu("File");
		apps = new JMenu("Applications");
		help = new JMenu("Help");
		other = new JMenu("Other");
		
		loadPlanner = new JMenuItem("Load a Planner");
		loadPlanner.addActionListener(new MenuActionListener());
		savePlanner = new JMenuItem("Save Planner");
		savePlanner.addActionListener(new MenuActionListener());
		preferences = new JMenuItem("Course Preferences");
		preferences.addActionListener(new MenuActionListener());
		wiki = new JMenuItem("Open Program Wiki");
		wiki.addActionListener(new MenuActionListener());
		aboutCreators = new JMenuItem("About creators");
		aboutCreators.addActionListener(new MenuActionListener());
		aboutProgram = new JMenuItem("About Program");
		aboutProgram.addActionListener(new MenuActionListener());
		
		file.add(loadPlanner);
		file.add(savePlanner);
		
		apps.add(preferences);
		
		help.add(wiki);
		
		other.add(aboutCreators);
		other.add(aboutProgram);
		
		menu.add(file);
		menu.add(apps);
		menu.add(help);
		menu.add(other);
		menuPanel.add(menu);
		mainPanel.add(menuPanel, BorderLayout.NORTH);
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
					ZPreff cps = new ZPreff("Preferences", planner, false);
					break;
				case "Open Program Wiki":
					break;
				case "About Creators":
					break;
				case "About Progra":
					break;
			}
			
		}
		
	}
	
	
	
}
