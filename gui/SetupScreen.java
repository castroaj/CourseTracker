package gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import course_map.Cluster;
import course_map.Program;
import course_map.Semester;
import main.Generator;
import main.ZPlanner;

public class SetupScreen {

	private ZPlanner planner;
	private String name;
	private int year;
	private Semester semester;
	private boolean nameEntered;
	private boolean yearEntered;
	private boolean semesterEntered;

	JFrame newStudentScreen;
	
	JPanel mainPanel;
	JPanel topPanel;
	JPanel leftSidePanel;
	JPanel currentProgramsPanel;
	JPanel availableProgramsPanel;
	JPanel bottomPanel;
	
	JTextField nameField;
	
	public SetupScreen(String title, ZPlanner planner)
	{
		this.planner = planner;
		
		
		newStudentScreen = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBounds(200, 200, 800, 400);
		newStudentScreen.add(mainPanel);
		createTopPanel();
		createLeftSidePanel();
		createCurrentProgramsPanel();
		createAvailableProgramsPanel();
		createBottomPanel();
	
		newStudentScreen.setSize(800, 400);

		newStudentScreen.setResizable(false);
		newStudentScreen.setLocation(200, 200);

		newStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newStudentScreen.setTitle(title);
		newStudentScreen.setVisible(true);
	}
	
	private void createTopPanel()
	{
		topPanel = new JPanel();
		
		JLabel titleLabel = new JLabel("Welcome to CourseTracker");
		JLabel titleLabel2 = new JLabel("Please fill out the following information:");
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(JLabel.LEFT);
		titleLabel2.setFont(new Font("Monospaced", Font.BOLD, 14));

		
		JLabel nameLabel = new JLabel("Enter your first name: ");
		nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		nameField = new JTextField(8);
		nameField.addKeyListener(new NameFieldKeyListener());
		
		JLabel yearLabel = new JLabel("What year at JMU are you: ");
		yearLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		String[] years = {"--", "Freshman", "Sophmore", "Junior", "Senior"};
		JComboBox<String> yearBox = new JComboBox<String>(years);
		yearBox.addActionListener(new DropdownYearActionListener());
		
		JLabel semesterLabel = new JLabel("What semester: ");
		semesterLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		String[] semesters = {"--", "Fall", "Spring"};
		JComboBox<String> semestersBox = new JComboBox<String>(semesters);
		semestersBox.addActionListener(new DropdownSemesterActionListener());
		
		topPanel.add(nameLabel);
		topPanel.add(nameField);	
		topPanel.add(yearLabel);
		topPanel.add(yearBox);
		topPanel.add(semesterLabel);
		topPanel.add(semestersBox);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
	}
	
	private void createLeftSidePanel()
	{
		leftSidePanel = new JPanel();
		leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.PAGE_AXIS));
		
		JButton addButton = new JButton("Add Program");
		JButton addGenEdButton = new JButton("Add Gen Ed Program");
		addGenEdButton.addActionListener(new GenEdButtonActionListener());
		
		leftSidePanel.add(addButton);
		leftSidePanel.add(addGenEdButton);
		
		mainPanel.add(leftSidePanel, BorderLayout.CENTER);
	}
	
	private void createCurrentProgramsPanel()
	{
		currentProgramsPanel = new JPanel();
		
		Program program = Generator.loadProgram("resources/gen_ed.zagp");
		DefaultListModel<Program> listModel = new DefaultListModel<Program>();
		JList<Program> currentProgramsList = new JList<Program>(listModel);
		listModel.addElement(program);

		currentProgramsPanel.add(currentProgramsList);
		
		mainPanel.add(currentProgramsPanel, BorderLayout.EAST);
	}
	
	private void createAvailableProgramsPanel()
	{
		availableProgramsPanel = new JPanel();
		
		
		mainPanel.add(availableProgramsPanel, BorderLayout.WEST);
	}
	
	private void createBottomPanel()
	{
		bottomPanel = new JPanel();
		
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ContinueButtonActionListener());
		JButton resetButton = new JButton("Reset");
		// TODO action listener
		
		bottomPanel.add(resetButton);
		bottomPanel.add(continueButton);
		
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	
	private class NameFieldKeyListener implements KeyListener {

		
		@Override
		public void keyTyped(KeyEvent e) {
			// Not used
		}

		@Override
		public void keyPressed(KeyEvent e) {
			JTextField field = (JTextField) e.getComponent();
			nameEntered = true;
			
			if (field.getText() == "")
			{
				nameEntered = false;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Not used
		}
	}
	
	private class DropdownYearActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> box = (JComboBox<String>)e.getSource();
			
			if (box.getSelectedIndex() == 0)
			{
				yearEntered = false;
			}
			if (box.getSelectedIndex() == 1)
			{
				year = 1;
				yearEntered = true;
			}
			if (box.getSelectedIndex() == 2)
			{
				year = 2;
				yearEntered = true;
			}
			if (box.getSelectedIndex() == 3)
			{
				year = 3;
				yearEntered = true;
			}
			if (box.getSelectedIndex() == 4)
			{
				year = 4;
				yearEntered = true;
			}
		}
	}
	
	private class DropdownSemesterActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> box = (JComboBox<String>) e.getSource();
			
			if (box.getSelectedIndex() == 0)
			{
				semesterEntered = false;
			}
			if (box.getSelectedIndex() == 1)
			{
				switch (year)
				{
					case 1:
						semester = Semester.FR_FA;
						break;
					case 2:
						semester = Semester.SO_FA;
						break;
					case 3:
						semester = Semester.JU_FA;
						break;
					case 4:
						semester = Semester.SE_FA;
						break;
				}
				semesterEntered = true;
			}
			if (box.getSelectedIndex() == 2)
			{
				switch (year)
				{
					case 1:
						semester = Semester.FR_SP;
						break;
					case 2:
						semester = Semester.SO_SP;
						break;
					case 3:
						semester = Semester.JU_SP;
						break;
					case 4:
						semester = Semester.SE_SP;
						break;
				}
				semesterEntered = true;
			}
			System.out.println(semester.toString());
			
		}
		
	}
	
	private class GenEdButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			if (button.isEnabled())
			{
				Program program = Generator.loadProgram("resources/gen_ed.zagp");
				HashSet<Cluster> clusters = program.getClusters();
				planner.addProgram(program);
				planner.addClusters(clusters);
				button.setEnabled(false);
			}
			
		}
	
	}
	
	private class ContinueButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			if (button.isEnabled() && yearEntered && nameEntered)
			{
				name = nameField.getText();
				newStudentScreen.dispose();
				
				planner.setName(name);
				planner.setSemester(semester);
				PreferencesSetupScreen pss = new PreferencesSetupScreen("Preferences", planner);
			}
		}
		
	}
}
