import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class StartScreen {
	
	JFrame startScreen;	
	JPanel mainPanel;
	
	// Menu Bar
	JPanel menuPanel;
	JMenuItem load, save;
	
	// Main Screen
	JPanel title;
	JPanel logo;
	JPanel options;
	JPanel creators;
	
	//Main screen options
	JPanel majorOption;
	JPanel newStudentOption;
	
	public StartScreen(String title)
	{
		startScreen = new JFrame();
		
		mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		startScreen.add(mainPanel);
		createMenuBar();
		createStartScreen();
		createCreatorsPanel();

	    startScreen.setSize(500, 350);
	    startScreen.setResizable(false);
	    startScreen.setLocation(200, 200);

	    startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setTitle(title);
		startScreen.setVisible(true);
	}
	
	private void createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
	    JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JMenu menu = new JMenu("File");
	    
	    
	    load = new JMenuItem("Load Plan");
	    save = new JMenuItem("Save Plan");
	    
	    menu.add(load);
	    menu.add(save);
	    menu.setBackground(Color.BLACK);
	    
	    menuBar.add(menu);
	    menuPanel.add(menuBar);

	    mainPanel.add(menuPanel);
	}
	
	
	private void createStartScreen()
	{
		title = new JPanel();
		logo = new JPanel();
		options = new JPanel();
		majorOption = new JPanel();
		newStudentOption = new JPanel();
		
		options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

		
		// Title
		JLabel titleLabel = new JLabel("Course Tracker");
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
		title.add(titleLabel);
		
		//Logo
		JLabel logoLabel = new JLabel("Insert Logo here");
		logo.add(logoLabel);
		
		// Options
		JLabel majorSelectionLabel = new JLabel("Select a major: ");
		JLabel newStudentQuestion = new JLabel("Are you a new Student?  ");
		
		majorSelectionLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
		newStudentQuestion.setFont(new Font("Monospaced", Font.PLAIN, 20));

		String[] majors = { "CS", "CIS" };	
		JComboBox majorsDropdown = new JComboBox(majors);
		majorsDropdown.setBounds(new Rectangle(100, 100 , 100, 100));
			
		String[] yesOrNo = {"Yes", "No"};
		JComboBox yesOrNoDropdown = new JComboBox(yesOrNo);
		yesOrNoDropdown.setBounds(new Rectangle(100, 100 , 100, 100));

		
		majorOption.add(majorSelectionLabel);
		majorOption.add(majorsDropdown);
		
		newStudentOption.add(newStudentQuestion);
		newStudentOption.add(yesOrNoDropdown);
		
		options.add(majorOption);
		options.add(newStudentOption);
		
		mainPanel.add(title);
		mainPanel.add(logo);
		mainPanel.add(options);
		
	}
	
	private void createCreatorsPanel()
	{
		creators = new JPanel();
		
		JLabel creatorsLabel = new JLabel("Created by: Alex Castro, Zeru Tadesse, Garrett Christian");
		creatorsLabel.setFont(new Font("Monospaced", Font.ITALIC, 12));

		
		
		creators.add(creatorsLabel);
		mainPanel.add(creators);
	}
	
	

}
