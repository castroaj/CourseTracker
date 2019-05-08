package gui;
import java.awt.FlowLayout;

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
		

		plannerScreen.setSize(400, 200);
		plannerScreen.setResizable(false);
		plannerScreen.setLocation(200, 200);
		plannerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plannerScreen.setTitle(title);
		plannerScreen.setVisible(true); 
	}
	
	public void makeGUI() {
		mainPanel = new JPanel();
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
		savePlanner = new JMenuItem("Save Planner");
		preferences = new JMenuItem("Course Preferences");
		wiki = new JMenuItem("Open Program wiki");
		aboutCreators = new JMenuItem("About creators");
		aboutProgram = new JMenuItem("About Program");
		
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
		mainPanel.add(menuPanel);
	}
	
	
	
}
