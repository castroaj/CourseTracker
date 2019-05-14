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
import java.net.URI;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import course_map.Cluster;
import course_map.Course;
import course_map.Program;
import main.Generator;
import main.Planner;

public class PlannerScreen {

	JFrame plannerScreen;
	public static Planner planner;
	
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
	//TODO
	
	JMenu help;
	JMenuItem wiki;
	
	JMenu other;
	JMenuItem aboutCreators;
	JMenuItem aboutProgram;
	
	JTextArea description;
	
	JList<Cluster> clusterViewer;
	JList<Course> courseViewer;
	DefaultListModel<Cluster> clusterModel;
	DefaultListModel<Course> courseModel;
	
	HashSet<Cluster> allClusters;
	HashSet<Cluster> remainingClusters;
	HashSet<Cluster> completedClusters;
		
	public PlannerScreen(String title, Planner oldPlanner)
	{
		planner = oldPlanner;
		plannerScreen = new JFrame();
		allClusters = planner.getAllClusters();
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
				remainingClusters.add(c);
			}
		}
		
		makeGUI();
		
		plannerScreen.setSize(1400, 800);
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
					//String pName = e.getSource().toString().split("\\]")[0].split("\\[")[2];
					description.setText(planner.toString());
					break;
				case 2:
					String progName = source[1].split("\\]")[0].substring(1);
					Program p = planner.findProgram(progName);
					description.setText(p.toString(true));
					break;
				case 3:
					Cluster c = planner.findCluster(e.getSource().toString().split(",")[2].split("\\]")[0]);
					description.setText(c.toString(false));
					break;
				case 4:
					String s = e.toString().split(",")[3].split("\\]")[0].substring(1).split("]")[0];
					Course currentCourse = planner.findCourse(Generator.findCourse(s.split(" ")[0], s.split(" ")[1]));
					description.setText(currentCourse.getDescription());
					break;

				}

			}
		});

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
		treePanel = new JPanel();
		clusterPanel = new JPanel();
		clusterButtons = new JPanel();
		clusterModel = new DefaultListModel<Cluster>();
		for (Cluster c : allClusters)
		{
			clusterModel.addElement(c);
		}
		
		clusterViewer = new JList<Cluster>(clusterModel);
		description = new JTextArea(30, 30);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		
		JButton allClustersButton = new JButton("All Clusters");
		JButton completedClustersButton = new JButton("Completed Clusters");
		JButton remainingClustersButton = new JButton("Remaining Clusters");
		
		allClustersButton.addActionListener(new ButtonActionListener());
		allClustersButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		completedClustersButton.addActionListener(new ButtonActionListener());
		completedClustersButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		remainingClustersButton.addActionListener(new ButtonActionListener());
		remainingClustersButton.setFont(new Font("Monospaced", Font.PLAIN, 10));
		
		clusterViewer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"All Clusters", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
				new Font("Monospaced", Font.PLAIN, 22), Color.BLACK));
		
		clusterViewer.setBackground(plannerScreen.getBackground());
		clusterViewer.setFont(new Font("Monospaced", Font.PLAIN, 14));
		clusterViewer.setFixedCellWidth(300);
		JScrollPane clusterScroller = new JScrollPane(clusterViewer);
		
		clusterButtons.add(allClustersButton);
		clusterButtons.add(completedClustersButton);
		clusterButtons.add(remainingClustersButton);
		
		clusterPanel.setLayout(new BoxLayout(clusterPanel, BoxLayout.PAGE_AXIS));
		clusterPanel.add(clusterScroller);
		clusterPanel.add(clusterButtons);
		
		description.setEditable(false);
		description.setBackground(plannerScreen.getBackground());
		
		treePanel.setLayout(new BoxLayout(clusterPanel, BoxLayout.PAGE_AXIS));
		treePanel.setPreferredSize(new Dimension(450, 600));
		treePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				"Programs", TitledBorder.LEFT, TitledBorder.DEFAULT_JUSTIFICATION,
				new Font("Monospaced", Font.PLAIN, 22), Color.BLACK));

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

		
		description.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				"Information", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
				new Font("Monospaced", Font.PLAIN, 22), Color.BLACK));
		
		
		treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.PAGE_AXIS));
		treePanel.add(scptree);
		treePanel.add(clusterPanel);
		
		reqViewer.add(treePanel);
		reqViewer.add(description);
		
		rightPanel.add(reqViewer, BorderLayout.SOUTH);
	}
	
	private class ButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
				
			String command = e.getActionCommand();
			
			switch (command)
			{
			case "All Clusters":
				clusterModel.clear();
				for (Cluster cur : allClusters)
				{
					clusterModel.addElement(cur);
				}
				clusterViewer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
						"All Clusters", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
						new Font("Monospaced", Font.PLAIN, 22), Color.BLACK));
				clusterViewer.setBackground(plannerScreen.getBackground());
				break;
			case "Completed Clusters":
				clusterModel.clear();
				for (Cluster cur : completedClusters)
				{
					clusterModel.addElement(cur);
				}
				if (clusterModel.size() == 0)
				{
					clusterModel.addElement(null);
				}
				clusterViewer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
						"Completed Clusters", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
						new Font("Monospaced", Font.PLAIN, 22), Color.BLACK));
				clusterViewer.setBackground(plannerScreen.getBackground());
				break;
			case "Remaining Clusters":
				clusterModel.clear();
				for (Cluster cur : remainingClusters)
				{
					clusterModel.addElement(cur);
				}
				clusterViewer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
						"Remaining Clusters", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,
						new Font("Monospaced", Font.PLAIN, 22), Color.BLACK));
				clusterViewer.setBackground(plannerScreen.getBackground());
				break;
			}
		}
		
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
	
	public DefaultMutableTreeNode getProgramNodes(Program p) {
		DefaultMutableTreeNode programNode = new DefaultMutableTreeNode(p.toString());
		p.getClusters().stream().forEach((c2) -> programNode.add(getClusterNodes(c2)));
		return programNode;
	}

	public DefaultMutableTreeNode getClusterNodes(Cluster c) {
		DefaultMutableTreeNode clusterNode = new DefaultMutableTreeNode(c.toString());
		c.getCourses().stream().forEach((c2) -> clusterNode.add(getCourseNodes(c2)));
		return clusterNode;
	}

	public DefaultMutableTreeNode getCourseNodes(Course c) {
		return new DefaultMutableTreeNode(c.toString());
	}
	
	
}
