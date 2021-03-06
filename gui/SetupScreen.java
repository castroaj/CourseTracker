package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import course_map.Cluster;
import course_map.Program;
import course_map.Semester;
import main.Generator;
import main.Planner;

public class SetupScreen {

	private Planner planner;
	private String name;
	private int year;
	private Semester semester;
	private boolean nameEntered;
	private boolean yearEntered;
	private boolean semesterEntered;

	JFrame setupScreen;

	JPanel mainPanel;
	JPanel topPanel;
	JPanel currentProgramsPanel;
	JPanel availableProgramsPanel;
	JPanel bottomPanel;

	JTextField nameField;
	JButton continueButton;

	DefaultListModel<Program> currentListModel;
	DefaultListModel<Program> availableListModel;
	JList<Program> currentProgramsList;
	JList<Program> availableProgramsList;
	JScrollPane currentProgramsSP;
	JScrollPane availableProgramsSP;

	ArrayList<Program> currentlySelectedPrograms; // used to check containment

	public SetupScreen(String title, Planner planner) {
		this.planner = planner;
		semester = Semester.FR_FA;
		year = 1;

		currentlySelectedPrograms = new ArrayList<Program>();

		setupScreen = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		setupScreen.add(mainPanel);
		createTopPanel();
		createCurrentProgramsPanel();
		createAvailableProgramsPanel();
		createBottomPanel();

		setupScreen.setSize(850, 400);

		setupScreen.setResizable(false);
		setupScreen.setLocation(200, 200);

		setupScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupScreen.setTitle(title);
		setupScreen.setVisible(true);
	}

	private void createTopPanel() {
		topPanel = new JPanel();

		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
		nameField = new JTextField(8);
		nameField.addKeyListener(new NameFieldKeyListener());

		JLabel yearLabel = new JLabel("   Year: ");
		yearLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
		String[] years = { "--", "Freshman", "Sophmore", "Junior", "Senior" };
		JComboBox<String> yearBox = new JComboBox<String>(years);
		yearBox.addActionListener(new DropdownYearActionListener());

		JLabel semesterLabel = new JLabel("   Semester: ");
		semesterLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
		String[] semesters = { "--", "Fall", "Spring" };
		JComboBox<String> semestersBox = new JComboBox<String>(semesters);
		semestersBox.addActionListener(new DropdownSemesterActionListener());

		topPanel.add(nameLabel);
		topPanel.add(nameField);
		topPanel.add(yearLabel);
		topPanel.add(yearBox);
		topPanel.add(semesterLabel);
		topPanel.add(semestersBox);

		topPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.black, 1)));

		mainPanel.add(topPanel, BorderLayout.NORTH);
	}

	private void createCurrentProgramsPanel() {
		currentProgramsPanel = new JPanel();
		currentProgramsPanel.setLayout(new BoxLayout(currentProgramsPanel, BoxLayout.PAGE_AXIS));

		JLabel currentProgramsLabel = new JLabel("My Programs:");
		currentProgramsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		currentProgramsLabel.setFont(new Font("Monospaced", Font.BOLD, 20));

		currentListModel = new DefaultListModel<Program>();
		currentListModel.setSize(0);
		currentProgramsList = new JList<Program>(currentListModel);

		currentProgramsSP = new JScrollPane(currentProgramsList);

		currentProgramsList.setFixedCellHeight(40);
		currentProgramsList.setFixedCellWidth(200);
		currentProgramsList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		currentProgramsList.setFont(new Font("Monospaced", Font.PLAIN, 18));

		currentProgramsPanel.add(currentProgramsLabel);
		currentProgramsPanel.add(currentProgramsSP);

		currentProgramsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		mainPanel.add(currentProgramsPanel, BorderLayout.WEST);
	}

	private void createAvailableProgramsPanel() {
		// PROGRAM ADDITION
		Program csMajorProgram = Generator.loadProgram("resources/cs_major.zagp");
		Program cisMinorProgram = Generator.loadProgram("resources/cis_minor.zagp");
		Program csMinorProgram = Generator.loadProgram("resources/cs_minor.zagp");

		availableProgramsPanel = new JPanel();
		availableProgramsPanel.setLayout(new BoxLayout(availableProgramsPanel, BoxLayout.PAGE_AXIS));

		JLabel availableProgramsLabel = new JLabel("Available Programs:");
		availableProgramsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		availableProgramsLabel.setFont(new Font("Monospaced", Font.BOLD, 20));

		availableListModel = new DefaultListModel<Program>();
		availableProgramsList = new JList<Program>(availableListModel);
		availableProgramsList.setFont(new Font("Monospaced", Font.PLAIN, 18));

		availableProgramsSP = new JScrollPane(availableProgramsList);

		// availableProgramsList.addListSelectionListener(new JListSelectionListener());
		availableProgramsList.setFixedCellHeight(40);
		availableProgramsList.setFixedCellWidth(200);
		availableProgramsList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// PROGRAM ADDITION TO LIST
		availableListModel.addElement(csMajorProgram);
		availableListModel.addElement(cisMinorProgram);
		availableListModel.addElement(csMinorProgram);

		availableProgramsPanel.add(availableProgramsLabel);
		availableProgramsPanel.add(availableProgramsSP);

		availableProgramsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		mainPanel.add(availableProgramsPanel, BorderLayout.CENTER);
	}

	private void createBottomPanel() {
		bottomPanel = new JPanel();

		JButton addButton = new JButton("Add Selected Program");
		addButton.addActionListener(new AddButtonActionListener());
		addButton.setToolTipText("Add selected program to your programs list");
		addButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

		JButton addGenEdButton = new JButton("Add Gen Ed Program");
		addGenEdButton.addActionListener(new AddButtonActionListener());
		addGenEdButton.setToolTipText("Add the General Education Program to your program list");
		addGenEdButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

		JLabel spacingLabel = new JLabel("            ");
		spacingLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
		spacingLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		JButton backButton = new JButton("Return to Home Page");
		backButton.addActionListener(new NavigationButtons());
		backButton.setToolTipText("Returns you to the start screen");
		backButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

		continueButton = new JButton("Continue");
		continueButton.addActionListener(new NavigationButtons());
		continueButton.setEnabled(false);
		continueButton.setToolTipText("Continue to the preference screen");
		continueButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new NavigationButtons());
		resetButton.setToolTipText("Clear the name, year, semester, and programs list");
		resetButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

		bottomPanel.add(addButton);
		bottomPanel.add(addGenEdButton);
		bottomPanel.add(spacingLabel);
		bottomPanel.add(backButton);
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

			if (field.getText() == "") {
				nameEntered = false;
			}

			if (nameEntered && yearEntered && semesterEntered && currentlySelectedPrograms.size() > 0) {
				continueButton.setEnabled(true);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Not used
		}
	}

	private class DropdownYearActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> box = (JComboBox<String>) e.getSource();

			if (box.getSelectedIndex() == 0) {
				yearEntered = false;
			}
			if (box.getSelectedIndex() == 1) {
				year = 1;
				yearEntered = true;
			}
			if (box.getSelectedIndex() == 2) {
				year = 2;
				yearEntered = true;
			}
			if (box.getSelectedIndex() == 3) {
				year = 3;
				yearEntered = true;
			}
			if (box.getSelectedIndex() == 4) {
				year = 4;
				yearEntered = true;
			}

			if (nameEntered && yearEntered && semesterEntered && currentlySelectedPrograms.size() > 0) {
				continueButton.setEnabled(true);
			}
		}
	}

	private class DropdownSemesterActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> box = (JComboBox<String>) e.getSource();

			if (box.getSelectedIndex() == 0) {
				semesterEntered = false;
			}
			if (box.getSelectedIndex() == 1) {
				switch (year) {
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
			if (box.getSelectedIndex() == 2) {
				switch (year) {
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

			if (nameEntered && yearEntered && semesterEntered && currentlySelectedPrograms.size() > 0) {
				continueButton.setEnabled(true);
			}
		}

	}

	private class AddButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();

			switch (e.getActionCommand()) {
			case "Add Gen Ed Program":
				Program genEd = Generator.loadProgram("resources/gen_ed.zagp");
				HashSet<Cluster> clusters = genEd.getClusters();
				planner.addProgram(genEd);
				planner.addClusters(clusters);
				button.setEnabled(false);
				currentListModel.add(currentListModel.getSize(), genEd);
				currentlySelectedPrograms.add(genEd);
				break;
			case "Add Selected Program":
				Program curProgram = availableProgramsList.getSelectedValue();
				if (availableProgramsList.getSelectedValue() != null) {
					if (!currentlySelectedPrograms.contains(curProgram) && !(currentlySelectedPrograms.size() > 10)) {
						HashSet<Cluster> curProgramClusters = curProgram.getClusters();
						planner.addProgram(curProgram);
						planner.addClusters(curProgramClusters);
						currentListModel.add(currentListModel.getSize(), curProgram);
						currentlySelectedPrograms.add(curProgram);

						if (nameEntered && yearEntered && semesterEntered && currentlySelectedPrograms.size() > 0) {
							continueButton.setEnabled(true);
						}
					}
				}
				break;
			}
		}
	}

	private class NavigationButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			switch (e.getActionCommand()) {
			case "Reset":
				new SetupScreen("Setup", new Planner());
				setupScreen.dispose();
				break;
			case "Continue":
				if (button.isEnabled() && yearEntered && nameEntered && semesterEntered
						&& currentlySelectedPrograms.size() > 0) {
					name = nameField.getText();
					setupScreen.dispose();

					planner.setName(name);
					planner.setSemester(semester);
					
					new ZPreff("Preferences", planner, true);
				}
				break;
			case "Return to Home Page":
				 new StartScreen("Start");
				setupScreen.dispose();
				break;
			}
		}
	}

}
