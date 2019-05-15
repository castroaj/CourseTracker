package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import course_map.Cluster;
import course_map.Course;
import course_map.Program;
import main.Generator;
import main.Planner;

public class PlannerScreen {

	final int screenHeight = 800;
	final int screenWidth = 1400;

	final int descriptionHeight = 23;
	final int descriptionWidth = 30;

	final int treePanelHeight = 400;
	final int treePanelWidth = 350;

	JFrame plannerScreen;
	Planner planner;

	private JTree tree;
	DefaultMutableTreeNode root;

	JPanel mainPanel;
	JPanel menuPanel;
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel reqViewer;
	JPanel clusterPanel;
	JPanel clusterButtons;
	JPanel courseSearch;
	JPanel bottomPanel;
	JPanel treePanel;

	JMenuBar menu;

	JMenu file;
	JMenuItem loadPlanner;
	JMenuItem savePlanner;

	JMenu apps;
	JMenuItem preferences;
	// TODO

	JMenu shortcuts;
	JMenuItem closePlanner;
	JMenuItem forceQuit;

	JMenu help;
	JMenuItem wiki;

	JMenu other;
	JMenuItem aboutCreators;
	JMenuItem aboutProgram;

	JTextArea description;
	JLabel clusterLabel;

	JList<Cluster> clusterViewer;
	DefaultListModel<Cluster> clusterModel;

	HashSet<Cluster> allClusters;
	HashSet<Cluster> remainingClusters;
	HashSet<Cluster> completedClusters;

	JScrollPane descriptionScroller;

	String plannerText;

	public PlannerScreen(String title, Planner oldPlanner) {
		planner = oldPlanner;
		plannerScreen = new JFrame();
		allClusters = planner.getAllClusters();
		remainingClusters = new HashSet<Cluster>();
		completedClusters = new HashSet<Cluster>();

		for (Cluster c : allClusters) {
			if (c.getIsComplete()) {
				completedClusters.add(c);
			} else {
				remainingClusters.add(c);
			}
		}

		plannerText = planner.toString(false);
		ArrayList<Program> programs = planner.getPrograms();

		plannerText += "Currently Enrolled Programs:\n";
		for (Program p : programs) {
			plannerText += p.getName() + "\n";
		}
		plannerText += "=======================\nClusterInfo:\n\n";
		plannerText += "Completed Clusters: "+ completedClusters.size() +"\nRemaining Clusters: " + remainingClusters.size()+ "\n";
		
		plannerText += "=======================\nAll Enrolled Clusters:\n\n";
		for (Cluster c : allClusters) {
			plannerText += c.getName() + "\n";
		}
		plannerText += "=======================\nCompleted Clusters:\n\n";
		for (Cluster c : completedClusters) {
			plannerText += c.getName() + "\n";
		}
		plannerText += "=======================\nRemaining Clusters:\n\n";
		for (Cluster c : remainingClusters) {
			plannerText += c.getName() + "\n";
		}

		makeGUI();

		plannerScreen.setSize(screenWidth, screenHeight);
		plannerScreen.setResizable(true);
		plannerScreen.setLocation(10, 10);
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

		setupTree();
		setupMenu();
		setupReqViewer();

	}

	private void setupMenu() {
		menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		menu = new JMenuBar();
		file = new JMenu("File");
		file.setFont(new Font("Monospaced", Font.PLAIN, 12));
		apps = new JMenu("Applications");
		apps.setFont(new Font("Monospaced", Font.PLAIN, 12));
		shortcuts = new JMenu("Shortcuts");
		shortcuts.setFont(new Font("Monospaced", Font.PLAIN, 12));
		help = new JMenu("Help");
		help.setFont(new Font("Monospaced", Font.PLAIN, 12));
		other = new JMenu("Other");
		other.setFont(new Font("Monospaced", Font.PLAIN, 12));

		// File
		loadPlanner = new JMenuItem("Load a Planner");
		loadPlanner.addActionListener(new MenuActionListener());
		loadPlanner.setFont(new Font("Monospaced", Font.PLAIN, 12));
		savePlanner = new JMenuItem("Save Planner");
		savePlanner.addActionListener(new MenuActionListener());
		savePlanner.setFont(new Font("Monospaced", Font.PLAIN, 12));
		KeyStroke savePlannerKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		savePlanner.setAccelerator(savePlannerKeyStroke);

		// Applications
		preferences = new JMenuItem("Course Preferences");
		preferences.addActionListener(new MenuActionListener());
		preferences.setFont(new Font("Monospaced", Font.PLAIN, 12));
		KeyStroke preferencesKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
		preferences.setAccelerator(preferencesKeyStroke);

		// Shortcuts
		closePlanner = new JMenuItem("Close Planner");
		closePlanner.setFont(new Font("Monospaced", Font.PLAIN, 12));
		closePlanner.addActionListener(new MenuActionListener());
		KeyStroke closePlannerKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
		closePlanner.setAccelerator(closePlannerKeyStroke);

		forceQuit = new JMenuItem("Force Quit");
		forceQuit.addActionListener(new MenuActionListener());
		forceQuit.setFont(new Font("Monospaced", Font.PLAIN, 12));
		KeyStroke forceQuitKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
		forceQuit.setAccelerator(forceQuitKeyStroke);

		// Help
		wiki = new JMenuItem("Open Program Wiki");
		wiki.addActionListener(new MenuActionListener());
		wiki.setFont(new Font("Monospaced", Font.PLAIN, 12));
		KeyStroke wikiKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK);
		wiki.setAccelerator(wikiKeyStroke);

		// Other
		aboutProgram = new JMenuItem("About Program");
		aboutProgram.addActionListener(new MenuActionListener());
		aboutProgram.setFont(new Font("Monospaced", Font.PLAIN, 12));

		aboutCreators = new JMenuItem("Creators");
		aboutCreators.addActionListener(new MenuActionListener());
		aboutCreators.setFont(new Font("Monospaced", Font.PLAIN, 12));

		file.add(loadPlanner);
		file.addSeparator();
		file.add(savePlanner);

		apps.add(preferences);

		shortcuts.add(closePlanner);
		shortcuts.add(forceQuit);

		help.add(wiki);

		other.add(aboutProgram);
		other.add(aboutCreators);

		menu.add(file);
		menu.add(apps);
		menu.add(shortcuts);
		menu.add(help);
		menu.add(other);
		menuPanel.add(menu);
		mainPanel.add(menuPanel, BorderLayout.NORTH);
	}

	private void setupTree() {
		root = new DefaultMutableTreeNode(planner.toString());
		planner.getPrograms().stream().forEach(p2 -> root.add(getProgramNodes(p2)));
		tree = new JTree(root);
		tree.setFont(new Font("Monospaced", Font.PLAIN, 12));
		tree.putClientProperty("JTree.lineStyle", "Horizontal");
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println(e.getSource().toString());
				String[] source = e.getSource().toString().split(",");
				int level = source.length;
				switch (level) {

				case 1:
					description.setText(plannerText);

					descriptionScroller.setBorder(
							BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
									planner.getName() + "'s Planner", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
									new Font("Monospaced", Font.ITALIC, 22), Color.BLACK));
					descriptionScroller.getVerticalScrollBar().setValue(1);
					;
					break;
				case 2:
					String progName = source[1].split("\\]")[0].substring(1);
					Program p = planner.findProgram(progName);
					description.setText(p.toString(true));

					descriptionScroller.setBorder(
							BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
									"Information for " + progName, TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
									new Font("Monospaced", Font.ITALIC, 22), Color.BLACK));

					break;
				case 3:
					Cluster c = planner.findCluster(e.getSource().toString().split(",")[2].split("\\]")[0]);
					description.setText(c.toString(false));

					descriptionScroller.setBorder(
							BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
									"Information for " + c.getName(), TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
									new Font("Monospaced", Font.ITALIC, 16), Color.BLACK));
					break;
				case 4:
					String s = e.toString().split(",")[3].split("\\]")[0].substring(1).split("]")[0];
					Course currentCourse = planner.findCourse(Generator.findCourse(s.split(" ")[0], s.split(" ")[1]));
					description.setText(currentCourse.getDescription() + "\n\nCurrent Preference Rating (1-10): "
							+ currentCourse.getPreference());

					descriptionScroller.setBorder(
							BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
									"Information for " + currentCourse.toString(), TitledBorder.LEFT,
									TitledBorder.ABOVE_TOP, new Font("Monospaced", Font.ITALIC, 16), Color.BLACK));

					break;
				}
			}
		});
	}

	private DefaultMutableTreeNode getProgramNodes(Program p) {
		DefaultMutableTreeNode programNode = new DefaultMutableTreeNode(p.toString());
		p.getClusters().stream().forEach((c2) -> programNode.add(getClusterNodes(c2)));
		return programNode;
	}

	private DefaultMutableTreeNode getClusterNodes(Cluster c) {
		DefaultMutableTreeNode clusterNode = new DefaultMutableTreeNode(c.toString());
		c.getCourses().stream().forEach((c2) -> clusterNode.add(getCourseNodes(c2)));
		return clusterNode;
	}

	private DefaultMutableTreeNode getCourseNodes(Course c) {
		return new DefaultMutableTreeNode(c.toString());
	}

	private void setupReqViewer() {
		reqViewer = new JPanel();

		reqViewer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
						"Requirement Viewer", TitledBorder.LEFT, TitledBorder.DEFAULT_JUSTIFICATION,
						new Font("Monospaced", Font.BOLD, 30), Color.BLACK),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		treePanel = new JPanel();
		clusterPanel = new JPanel();
		clusterButtons = new JPanel();

		clusterModel = new DefaultListModel<Cluster>();
		clusterLabel = new JLabel(" All Clusters");
		JPanel clusterLabelPanel = new JPanel();
		clusterLabel.setFont(new Font("Monospaced", Font.ITALIC, 22));

		for (Cluster c : allClusters) {
			clusterModel.addElement(c);
		}

		clusterViewer = new JList<Cluster>(clusterModel);
		description = new JTextArea(descriptionHeight, descriptionWidth);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setText(plannerText);
		description.setEditable(false);
		description.setBackground(plannerScreen.getBackground());

		JButton allClustersButton = new JButton("All");
		JButton completedClustersButton = new JButton("Completed");
		JButton remainingClustersButton = new JButton("Remaining");

		allClustersButton.addActionListener(new ButtonActionListener());
		allClustersButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		completedClustersButton.addActionListener(new ButtonActionListener());
		completedClustersButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		remainingClustersButton.addActionListener(new ButtonActionListener());
		remainingClustersButton.setFont(new Font("Monospaced", Font.PLAIN, 10));

		clusterViewer.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createLineBorder(Color.GRAY, 2)));

		clusterViewer.setBackground(plannerScreen.getBackground());
		clusterViewer.setFont(new Font("Monospaced", Font.PLAIN, 12));
		clusterViewer.setSelectionBackground(plannerScreen.getBackground());
		clusterViewer.setSelectionForeground(Color.black);
		clusterViewer.setFixedCellWidth(300);
		JScrollPane clusterScroller = new JScrollPane(clusterViewer);

		clusterButtons.add(allClustersButton);
		clusterButtons.add(completedClustersButton);
		clusterButtons.add(remainingClustersButton);

		clusterPanel.setLayout(new BoxLayout(clusterPanel, BoxLayout.PAGE_AXIS));
		clusterPanel.add(clusterScroller);
		clusterPanel.add(clusterButtons);

		treePanel.setLayout(new BoxLayout(clusterPanel, BoxLayout.PAGE_AXIS));
		treePanel.setPreferredSize(new Dimension(treePanelWidth, treePanelHeight));

		treePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				"Programs", TitledBorder.LEFT, TitledBorder.DEFAULT_JUSTIFICATION,
				new Font("Monospaced", Font.ITALIC, 24), Color.BLACK));

		GridBagLayout gbTreePanel = new GridBagLayout();
		GridBagConstraints gbcTreePanel = new GridBagConstraints();
		treePanel.setLayout(gbTreePanel);

		JScrollPane scptree = new JScrollPane(tree);

		scptree.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(5, 5, 5, 5, plannerScreen.getBackground()),
				BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)));

		gbcTreePanel.gridx = 0;
		gbcTreePanel.gridy = 0;
		gbcTreePanel.gridwidth = GridBagConstraints.REMAINDER;
		gbcTreePanel.gridheight = GridBagConstraints.REMAINDER;
		gbcTreePanel.fill = GridBagConstraints.BOTH;
		gbcTreePanel.weightx = 1;
		gbcTreePanel.weighty = 1;
		gbcTreePanel.anchor = GridBagConstraints.NORTH;
		gbTreePanel.setConstraints(scptree, gbcTreePanel);

		descriptionScroller = new JScrollPane(description);

		descriptionScroller.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED), planner.getName() + "'s Planner",
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Monospaced", Font.ITALIC, 22), Color.BLACK));
		descriptionScroller.setBackground(plannerScreen.getBackground());

		clusterLabelPanel.setLayout(new BorderLayout());

		clusterLabelPanel.add(clusterLabel, BorderLayout.WEST);

		treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.PAGE_AXIS));
		treePanel.add(scptree);
		treePanel.add(clusterLabelPanel);
		treePanel.add(clusterPanel);

		reqViewer.add(treePanel);
		reqViewer.add(descriptionScroller);

		rightPanel.add(reqViewer, BorderLayout.SOUTH);
	}

	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String command = e.getActionCommand();

			switch (command) {
			case "All":
				clusterModel.clear();
				for (Cluster cur : allClusters) {
					clusterModel.addElement(cur);
				}
				clusterViewer.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
						BorderFactory.createLineBorder(Color.GRAY, 2)));
				clusterViewer.setBackground(plannerScreen.getBackground());
				clusterLabel.setText(" All Clusters");
				break;
			case "Completed":
				clusterModel.clear();
				for (Cluster cur : completedClusters) {
					clusterModel.addElement(cur);
				}
				clusterViewer.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
						BorderFactory.createLineBorder(Color.GRAY, 2)));
				clusterViewer.setBackground(plannerScreen.getBackground());
				clusterLabel.setText(" Completed Clusters");
				break;
			case "Remaining":
				clusterModel.clear();
				for (Cluster cur : remainingClusters) {
					clusterModel.addElement(cur);
				}
				clusterViewer.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
						BorderFactory.createLineBorder(Color.GRAY, 2)));
				clusterLabel.setText(" Remaining Clusters");
				break;
			}
		}

	}

	private class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			switch (command) {
			
			case "Load a Planner":
				String newPlanner = null;
				JFileChooser loadFile = new JFileChooser();
				int loadChoice = loadFile.showOpenDialog(plannerScreen);
				if (loadChoice != JFileChooser.APPROVE_OPTION) {
					break;
				}
				try {
					FileInputStream fileIn = new FileInputStream(loadFile.getSelectedFile());
					ObjectInputStream in = new ObjectInputStream(fileIn);
					newPlanner = (String) in.readObject();
					in.close();
					fileIn.close();
				} catch (IOException er) {
					System.err.println("FileInput Failed");
				} catch (ClassNotFoundException er) {
					System.err.println("Class doesn't exist");
				}
				//new PlannerScreen(newPlanner.getName() + "'s planner", newPlanner);
				System.out.println(newPlanner);
				plannerScreen.dispose();

				break;
				
				
				
			case "Save Planner":
				JFileChooser savefc = new JFileChooser();
				//savefc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt", "TEXT FILES"));
				int saveChoice = savefc.showSaveDialog(new JFrame());
				
				saveFile(saveChoice, savefc);
				
				break;
				
				
				
			case "Course Preferences":
				new ZPreff("Preferences", planner, false);
				break;

			case "Force Quit":
				plannerScreen.dispose();
				System.exit(0);
				break;
				
				

			case "Close Planner":
				int choice = JOptionPane.showConfirmDialog(new JFrame(), "Do you wish to save any changes?",
						"Confirm close", JOptionPane.YES_NO_CANCEL_OPTION);
				switch (choice) {
				case 0:
					JFileChooser fc = new JFileChooser();
					//fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt", "TEXT FILES"));
					int newSaveChoice = fc.showSaveDialog(plannerScreen);
					
					saveFile(newSaveChoice, fc);
					
					plannerScreen.dispose();
					System.exit(0);
					break;

				case 1:
					plannerScreen.dispose();
					System.exit(0);
					break;

				}
				break;
				

			case "Open Program Wiki":
				try {
					URI uri = new URI("https://github.com/castroaj/CourseTracker/wiki#how-to-use");
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
				
				

			case "Creators":
				JOptionPane.showMessageDialog(new JFrame(),
						"This application was created by: \nAlex Castro\nZeru Tadesse\nGarrett Christian",
						"About Creators", 1);
				break;
				
				
			case "About Program":
				break;
			}
			
		}

		private void saveFile(int newSaveChoice, JFileChooser fc)
		{
			if (newSaveChoice != JFileChooser.APPROVE_OPTION) {
				System.err.println("Failure");
				return;
			}
			try {
				File selectedFile = fc.getSelectedFile();
				FileOutputStream fileOut = new FileOutputStream(selectedFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				Planner copyPlanner = new Planner(planner);
				out.writeObject(copyPlanner);
				out.close();
				fileOut.close();
				System.out.println("Serialized data is saved");
			} catch (IOException error) {
				System.err.println("IO Exception");
			}
		}
	}
	
	

}
