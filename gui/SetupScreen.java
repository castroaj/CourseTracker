package gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import course_map.Semester;
import main.ZPlanner;
import temp.*;
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
		String[] years = {"--", "Freshman", "Sophmore", "Junior", "Senior", "Senior+"};
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
		
		
		//topPanel.add(continueButton);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
	}
	
	private void createLeftSidePanel()
	{
		leftSidePanel = new JPanel();
		leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.PAGE_AXIS));
		
		JButton addMajorButton = new JButton("Add Major");
		JButton addMinorButton = new JButton("Add Minor");
		JButton addGenEdButton = new JButton("Add Gen Ed Program");
		
		leftSidePanel.add(addMajorButton);
		leftSidePanel.add(addMinorButton);
		leftSidePanel.add(addGenEdButton);
		
		mainPanel.add(leftSidePanel, BorderLayout.EAST);
	}
	
	private void createCurrentProgramsPanel()
	{
		currentProgramsPanel = new JPanel();
		
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ContinueButtonActionListener());
		
		
		mainPanel.add(currentProgramsPanel, BorderLayout.CENTER);
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
			if (box.getSelectedIndex() == 5)
			{
				year = 5;
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
				
				semesterEntered = true;
			}
			if (box.getSelectedIndex() == 2)
			{
				semesterEntered = true;
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
			}
		}
		
	}
}
