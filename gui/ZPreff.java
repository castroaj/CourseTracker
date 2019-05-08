package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import course_map.Cluster;
import course_map.Course;
import course_map.Program;
import main.Generator;
import main.Planner;

public class ZPreff extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree tree;
	DefaultMutableTreeNode root;

	JPanel MainPanel;

	JPanel LeftPanel;

	JPanel InfoPanel;
	JTextField ClassName;
	JCheckBox hasTaken;
	JSlider Preference;
	JTextField Discription;

	public ZPreff(String title, Planner planner) {

		root = new DefaultMutableTreeNode(planner.toString());
		planner.getPrograms().stream().forEach(p2 -> root.add(getProgramNodes(p2)));
		tree = new JTree(root);
		tree.putClientProperty("JTree.lineStyle", "Horizontal");

		createGUI();
		add(MainPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTree Example");
		this.pack();
		this.setSize(900, 500);
		this.setResizable(false);
		this.setVisible(true);

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
		LeftPanel = new JPanel();
		LeftPanel.setBorder(BorderFactory.createTitledBorder("Programs"));
		GridBagLayout gbLeftPanel = new GridBagLayout();
		GridBagConstraints gbcLeftPanel = new GridBagConstraints();
		LeftPanel.setLayout(gbLeftPanel);

		gbcLeftPanel.gridx = 0;
		gbcLeftPanel.gridy = 0;
		gbcLeftPanel.gridwidth = 11;
		gbcLeftPanel.gridheight = 20;
		gbcLeftPanel.fill = GridBagConstraints.BOTH;
		gbcLeftPanel.weightx = 1;
		gbcLeftPanel.weighty = 1;
		gbcLeftPanel.anchor = GridBagConstraints.NORTH;
		gbLeftPanel.setConstraints(tree, gbcLeftPanel);
		LeftPanel.add(tree);

	}

	public void setUpRightPanel() {
		InfoPanel = new JPanel();
		InfoPanel.setBorder(BorderFactory.createTitledBorder("Information"));
		GridBagLayout gbInfoPanel = new GridBagLayout();
		GridBagConstraints gbcInfoPanel = new GridBagConstraints();
		InfoPanel.setLayout(gbInfoPanel);

		hasTaken = new JCheckBox("Taken");
		gbcInfoPanel.gridx = 1;
		gbcInfoPanel.gridy = 5;
		gbcInfoPanel.gridwidth = 1;
		gbcInfoPanel.gridheight = 1;
		gbcInfoPanel.fill = GridBagConstraints.BOTH;
		gbcInfoPanel.weightx = 1;
		gbcInfoPanel.weighty = 0;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(hasTaken, gbcInfoPanel);
		InfoPanel.add(hasTaken);

		Preference = new JSlider();
		Preference.setValue(0);
		Preference.setMinimum(0);
		Preference.setMaximum(10);
		gbcInfoPanel.gridx = 1;
		gbcInfoPanel.gridy = 17;
		gbcInfoPanel.gridwidth = 7;
		gbcInfoPanel.gridheight = 1;
		gbcInfoPanel.fill = GridBagConstraints.BOTH;
		gbcInfoPanel.weightx = 1;
		gbcInfoPanel.weighty = 0;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(Preference, gbcInfoPanel);
		InfoPanel.add(Preference);

		Discription = new JTextField();
		Discription.setHorizontalAlignment(JTextField.LEFT);
		Discription.setEditable(false);
		gbcInfoPanel.gridx = 1;
		gbcInfoPanel.gridy = 7;
		gbcInfoPanel.gridwidth = 7;
		gbcInfoPanel.gridheight = 9;
		gbcInfoPanel.fill = GridBagConstraints.BOTH;
		gbcInfoPanel.weightx = 1;
		gbcInfoPanel.weighty = 1;
		gbcInfoPanel.anchor = GridBagConstraints.NORTH;
		gbInfoPanel.setConstraints(Discription, gbcInfoPanel);

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

		// System.out.println(c.toString(true));
		InfoPanel.setBorder(BorderFactory.createTitledBorder(c.toString()));
		hasTaken.setSelected(c.isTaken());
		Preference.setValue(c.getPreference());
		Discription.setText(c.getDescription());

	}

	public void createGUI() {

		MainPanel = new JPanel();
		MainPanel.setBorder(BorderFactory.createTitledBorder("Preferences"));
		GridBagLayout gbMainPanel = new GridBagLayout();
		GridBagConstraints gbcMainPanel = new GridBagConstraints();
		MainPanel.setLayout(gbMainPanel);
		gbcMainPanel.gridx = 0;
		gbcMainPanel.gridy = 0;
		gbcMainPanel.gridwidth = 11;
		gbcMainPanel.gridheight = 20;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		setUpLeftPanel();
		setUpRightPanel();
		JScrollPane scpLeftPanel = new JScrollPane(LeftPanel);
		gbMainPanel.setConstraints(scpLeftPanel, gbcMainPanel);
		MainPanel.add(scpLeftPanel);

		InfoPanel.add(Discription);
		gbcMainPanel.gridx = 11;
		gbcMainPanel.gridy = 0;
		gbcMainPanel.gridwidth = 9;
		gbcMainPanel.gridheight = 20;
		gbcMainPanel.fill = GridBagConstraints.BOTH;
		gbcMainPanel.weightx = 1;
		gbcMainPanel.weighty = 1;
		gbcMainPanel.anchor = GridBagConstraints.NORTH;
		gbMainPanel.setConstraints(InfoPanel, gbcMainPanel);
		MainPanel.add(InfoPanel);

	}
}