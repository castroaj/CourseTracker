package gui;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import temp.*;
import course_map.*;
import main.ZPlanner;

public class StartScreen {
	
	// New student selection
	private boolean nothingSelected = true;
	private boolean yesSelected = false;
	private boolean noSelected = false;

	// GUI Components
	JFrame startScreen;
	JPanel mainPanel;

	// Menu Bar
	JPanel menuPanel;
	JMenuItem load;

	// Main Screen
	JPanel title;
	JPanel logo;
	JPanel options;
	JPanel creators;

	// Main screen options
	JPanel newStudentOption;
	JPanel startOption;

	public StartScreen(String title) {
		startScreen = new JFrame();
		mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		startScreen.add(mainPanel);
		createMenuBar();
		createStartScreen();
		createCreatorsPanel();

		mainPanel.setBackground(Color.gray);
		startScreen.setSize(550, 350);
		startScreen.setResizable(false);
		startScreen.setLocation(200, 200);
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setTitle(title);
		startScreen.setVisible(true);
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JMenu menu = new JMenu("File");

		load = new JMenuItem("Load Plan (Not finished)");

		menu.add(load);
		menu.setBackground(Color.BLACK);

		menuBar.add(menu);
		menuPanel.add(menuBar);

		mainPanel.add(menuPanel);
	}

	private void createStartScreen() {
		title = new JPanel();
		logo = new JPanel();
		options = new JPanel();
		newStudentOption = new JPanel();
		startOption = new JPanel();

		options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

		// Title
		JLabel titleLabel = new JLabel("Course Tracker");
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
		title.add(titleLabel);

		// Logo
		ImageIcon jmuIcon = createImageIcon("jmuLogo.jpg", "JMU icon");
		Image image = jmuIcon.getImage();
		image = image.getScaledInstance(200, 80, java.awt.Image.SCALE_SMOOTH);
		jmuIcon = new ImageIcon(image);

		JLabel logoLabel = new JLabel(jmuIcon);

		logo.add(logoLabel);

		// Option
		JLabel newStudentQuestion = new JLabel("Are you a new student or currently enrolled?");

		newStudentQuestion.setFont(new Font("Monospaced", Font.PLAIN, 14));

		// YesOrNoDropdown
		String[] yesOrNo = { "--", "New student", "Currently enrolled" };
		JComboBox<String> yesOrNoDropdown = new JComboBox<String>(yesOrNo);
		yesOrNoDropdown.setBounds(new Rectangle(100, 100, 100, 100));

		yesOrNoDropdown.addActionListener(new NewStudentSelectionOptionActionListener());

		// StartButton
		JButton startButton = new JButton("Get started");
		startButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
		startButton.addActionListener(new GetStartedButtonActionListener());

		// Add newStudent option components
		newStudentOption.add(newStudentQuestion);
		newStudentOption.add(yesOrNoDropdown);

		// Add start button component
		startOption.add(startButton);

		// Add each panel to options panel
		options.add(newStudentOption);
		options.add(startOption);

		// Add each sub panel to the main panel
		mainPanel.add(title);
		mainPanel.add(logo);
		mainPanel.add(options);

	}

	private void createCreatorsPanel() {
		creators = new JPanel();

		JLabel creatorsLabel = new JLabel("Created by: Alex Castro, Zeru Tadesse, Garrett Christian");
		creatorsLabel.setFont(new Font("Monospaced", Font.ITALIC, 12));

		creators.add(creatorsLabel);
		mainPanel.add(creators);
	}

	private class NewStudentSelectionOptionActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox box = (JComboBox) e.getSource();
			if (box.getSelectedIndex() == 0) {
				nothingSelected = true;

				yesSelected = false;
				noSelected = false;
			}
			
			if (box.getSelectedIndex() == 1) {
				yesSelected = true;

				nothingSelected = false;
				noSelected = false;
			}
			
			if (box.getSelectedIndex() == 2) {
				noSelected = true;

				yesSelected = false;
				nothingSelected = false;
			}

		}
	}

	private class GetStartedButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.isEnabled() && !nothingSelected) {

				if (yesSelected) {
					NewStudentScreen nss = new NewStudentScreen("New Student");
					startScreen.dispose();

				}

				if (noSelected) {
					OlderStudentScreen oss = new OlderStudentScreen("Older Student");
					startScreen.dispose();
				}

			}
		}

	}

	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
