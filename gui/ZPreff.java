package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import course_map.Cluster;
import course_map.Course;
import course_map.Program;
import main.Generator;
import main.Planner;

public class ZPreff {

	private JTree tree;
	DefaultMutableTreeNode root;

	JFrame preferenceScreen;

	JPanel MainPanel;
	JPanel InfoPanel;

	JPanel TreePanel;
	JPanel MenuPanel;

	JLabel CourseLabel;
	JCheckBox hasTaken;
	JSlider prefSlider;
	JTextArea CourseDiscription;
	JButton OkButton;

	private final Color DARK_PURPLE = new Color(69, 0, 132);
	private final Color GOLD = new Color(203, 182, 119);

	public ZPreff(String title, Planner planner) {

		preferenceScreen = new JFrame();
		root = new DefaultMutableTreeNode(planner.toString());
		planner.getPrograms().stream().forEach(p2 -> root.add(getProgramNodes(p2)));
		tree = new JTree(root);
		tree.putClientProperty("JTree.lineStyle", "Horizontal");

		makeGUI();
		preferenceScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preferenceScreen.setTitle(title);
		preferenceScreen.pack();
		preferenceScreen.setResizable(false);
		preferenceScreen.setSize(725, 700);
		preferenceScreen.setVisible(true);
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				try {
					String s = e.toString().split(",")[3].split("\\]")[0].substring(1).split("]")[0];
					updateInfoPanel(Generator.findCourse(s.split(" ")[0], s.split(" ")[1]));

				} catch (ArrayIndexOutOfBoundsException x) {

				}
			}
		});
	}

	public void setUpLeftPanel() {
		TreePanel = new JPanel();
		TreePanel.setPreferredSize(new Dimension(350, 800));
		
		TreePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "Programs",
				TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, new Font("Monospaced", Font.PLAIN, 16),
				Color.BLACK));
		
		GridBagLayout gbTreePanel = new GridBagLayout();
		GridBagConstraints gbcTreePanel = new GridBagConstraints();
		TreePanel.setLayout(gbTreePanel);

		JScrollPane scptree = new JScrollPane(tree);
		gbcTreePanel.gridx = 0;
		gbcTreePanel.gridy = 0;
		gbcTreePanel.gridwidth = GridBagConstraints.REMAINDER;
		gbcTreePanel.gridheight = GridBagConstraints.REMAINDER;
		;
		gbcTreePanel.fill = GridBagConstraints.BOTH;
		gbcTreePanel.weightx = 1;
		gbcTreePanel.weighty = 1;
		gbcTreePanel.anchor = GridBagConstraints.NORTH;
		gbTreePanel.setConstraints(scptree, gbcTreePanel);
		TreePanel.add(scptree);
	}

	public void setUpRightPanel() {
		InfoPanel = new JPanel();
		InfoPanel.setPreferredSize(new Dimension(350, 800));
		InfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "Info",
				TitledBorder.LEFT, TitledBorder.DEFAULT_JUSTIFICATION, new Font("Monospaced", Font.PLAIN, 16),
				Color.BLACK));
		GridBagLayout gbInfoPanel = new GridBagLayout();
		GridBagConstraints gbcInfoPanel = new GridBagConstraints();
		InfoPanel.setLayout(gbInfoPanel);

		CourseLabel = new JLabel("Course Name");
		CourseLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		gbcInfoPanel.gridx = 0;
		gbcInfoPanel.gridy = 0;
		gbcInfoPanel.gridwidth = 8;
		gbcInfoPanel.gridheight = 1;
		gbcInfoPanel.fill = GridBagConstraints.NONE;
		gbcInfoPanel.weightx = 0;
		gbcInfoPanel.weighty = 0;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(CourseLabel, gbcInfoPanel);
		InfoPanel.add(CourseLabel);

		hasTaken = new JCheckBox("Taken  ");
		hasTaken.setSelected(true);
		gbcInfoPanel.gridx = 1;
		gbcInfoPanel.gridy = 18;
		gbcInfoPanel.gridwidth = 1;
		gbcInfoPanel.gridheight = 1;
		gbcInfoPanel.fill = GridBagConstraints.NONE;
		gbcInfoPanel.weightx = 1;
		gbcInfoPanel.weighty = 0;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(hasTaken, gbcInfoPanel);
		InfoPanel.add(hasTaken);

		prefSlider = new JSlider();
		prefSlider.setValue(2);
		prefSlider.setMinimum(0);
		prefSlider.setMaximum(10);

		gbcInfoPanel.gridx = 3;
		gbcInfoPanel.gridy = 18;
		gbcInfoPanel.gridwidth = 5;
		gbcInfoPanel.gridheight = 1;
		gbcInfoPanel.fill = GridBagConstraints.NONE;
		gbcInfoPanel.weightx = 1;
		gbcInfoPanel.weighty = 0;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(prefSlider, gbcInfoPanel);
		InfoPanel.add(prefSlider);

		CourseDiscription = new JTextArea(2, 10);
		CourseDiscription.setWrapStyleWord(true);
		CourseDiscription.setLineWrap(true);
		gbcInfoPanel.gridx = 0;
		gbcInfoPanel.gridy = 2;
		gbcInfoPanel.gridwidth = 8;
		gbcInfoPanel.gridheight = 16;
		gbcInfoPanel.fill = GridBagConstraints.BOTH;
		gbcInfoPanel.weightx = 1;
		gbcInfoPanel.weighty = 1;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(CourseDiscription, gbcInfoPanel);
		InfoPanel.add(CourseDiscription);

		OkButton = new JButton("OK");
		gbcInfoPanel.gridx = 7;
		gbcInfoPanel.gridy = 20;
		gbcInfoPanel.gridwidth = 1;
		gbcInfoPanel.gridheight = 1;
		gbcInfoPanel.fill = GridBagConstraints.BOTH;
		gbcInfoPanel.weightx = 1;
		gbcInfoPanel.weighty = 0;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(OkButton, gbcInfoPanel);
		InfoPanel.add(OkButton);
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

	public void updateInfoPanel(Course c) {
		String shortTitle = c.getTitle();
		int len = new String("Network Applications Development").length();
		shortTitle = shortTitle.length() > len ? shortTitle.substring(0, len) + "..." : shortTitle;
		
		InfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), c.toString(),
				TitledBorder.LEFT, TitledBorder.DEFAULT_JUSTIFICATION, new Font("Monospaced", Font.PLAIN, 16),
				Color.BLACK));
		
		hasTaken.setSelected(c.isTaken());
		prefSlider.setValue(c.getPreference());
		CourseLabel.setText(shortTitle);
		CourseLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		CourseDiscription.setText(c.getDescription());

	}

	public void makeGUI() {

		MainPanel = new JPanel();
		MainPanel.setBorder(BorderFactory.createTitledBorder(""));
		GridBagLayout gbMainPanel = new GridBagLayout();
		GridBagConstraints gbcMainPanel = new GridBagConstraints();
		//MainPanel.setLayout(gbMainPanel);

		setUpLeftPanel();
		setUpRightPanel();

//		gbcMainPanel.gridx = 0;
//		gbcMainPanel.gridy = 0;
//		gbcMainPanel.gridwidth = 1;
//		gbcMainPanel.gridheight = 20;
//		gbcMainPanel.fill = GridBagConstraints.BOTH;
//		gbcMainPanel.weightx = 1;
//		gbcMainPanel.weighty = 1;
//		gbcMainPanel.anchor = GridBagConstraints.WEST;
//		gbMainPanel.setConstraints(TreePanel, gbcMainPanel);
//
//		gbcMainPanel.gridx = 1;
//		gbcMainPanel.gridy = 0;
//		gbcMainPanel.gridwidth = 8;
//		gbcMainPanel.gridheight = 20;
//		gbcMainPanel.fill = GridBagConstraints.VERTICAL;
//		gbcMainPanel.weightx = 1;
//		gbcMainPanel.weighty = 1;
//		gbcMainPanel.anchor = GridBagConstraints.CENTER;
//		gbMainPanel.setConstraints(InfoPanel, gbcMainPanel);
//		MainPanel.add(InfoPanel);
		
		MainPanel.setLayout(new BorderLayout());
		
		MainPanel.add(TreePanel, BorderLayout.WEST);
		MainPanel.add(InfoPanel, BorderLayout.EAST);

		preferenceScreen.add(MainPanel);
	}
}