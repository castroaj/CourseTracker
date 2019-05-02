import java.awt.Component;
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

public class NewStudentScreen {

	private Planner planner;
	private String name;
	private int year;
	private boolean nameEntered;
	private boolean yearEntered;

	JFrame newStudentScreen;
	
	JPanel mainPanel;
	JPanel titlePanel;
	JPanel namePanel;
	JPanel yearPanel;
	JPanel buttonPanel;
	
		
	public NewStudentScreen(String title, Planner planner)
	{
		this.planner = planner;
		newStudentScreen = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		newStudentScreen.add(mainPanel);
		createNewStudentScreen();
	
		newStudentScreen.setSize(400, 200);
		newStudentScreen.setResizable(false);
		newStudentScreen.setLocation(200, 200);

		newStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newStudentScreen.setTitle(title);
		newStudentScreen.setVisible(true);
	}
	
	private void createNewStudentScreen()
	{
		titlePanel = new JPanel();
		namePanel = new JPanel();
		yearPanel = new JPanel();
		buttonPanel = new JPanel();
		
		JLabel titleLabel = new JLabel("Welcome to CourseTracker");
		JLabel titleLabel2 = new JLabel("Please fill out the following information:");
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(0);
		titleLabel2.setFont(new Font("Monospaced", Font.BOLD, 14));

		
		JLabel nameLabel = new JLabel("Enter your first name: ");
		nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JTextField nameField = new JTextField(15);
		nameField.addKeyListener(new NameFieldKeyListener());
		
		JLabel yearLabel = new JLabel("What year at JMU are you: ");
		yearLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		String[] years = {"--", "Freshman", "Sophmore", "Junior", "Senior", "Senior+"};
		JComboBox<String> yearBox = new JComboBox<String>(years);
		yearBox.addActionListener(new DropdownActionListener());
		
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ContinueButtonActionListener());
		
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		titlePanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		titlePanel.add(titleLabel);
		titlePanel.add(titleLabel2);
		
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		yearPanel.add(yearLabel);
		yearPanel.add(yearBox);
		
		buttonPanel.add(continueButton);
		
		mainPanel.add(titlePanel);
		mainPanel.add(namePanel);
		mainPanel.add(yearPanel);
		mainPanel.add(buttonPanel);
	}
	
	
	private class NameFieldKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// Not used
		}

		@Override
		public void keyPressed(KeyEvent e) {
			JTextField field = (JTextField) e.getComponent();
			name = field.getText();
			nameEntered = true;
			
			if (field.getText() == "")
			{
				nameEntered = false;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println(name);
		}
	}
	
	private class DropdownActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
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
	
	private class ContinueButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			if (button.isEnabled() && yearEntered && nameEntered)
			{
				newStudentScreen.dispose();
				planner.setName(name);
				planner.setYear(year);
				PlannerScreen plannerScreen = new PlannerScreen("Planner", planner);
				
			}
		}
		
	}
}
