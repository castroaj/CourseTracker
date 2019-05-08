package gui;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
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
	JPanel treePanel;
	JPanel infoPanel;
	JTextArea info;
	JPanel mainPanel;
	JSlider slider;
	JCheckBox taken;

	public ZPreff(String title, Planner planner) {
		root = new DefaultMutableTreeNode(planner.toString());
		planner.getPrograms().stream().forEach(p2 -> root.add(getProgramNodes(p2)));
		tree = new JTree(root);
		tree.putClientProperty("JTree.lineStyle", "Horizontal");
		
		mainPanel = new JPanel();
		setUpTreePanel();
		setUpInfoPanel();
		add(mainPanel);
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

	public void setUpTreePanel() {
		JScrollPane scroll = new JScrollPane(tree);
		scroll.setSize(300,800);
		mainPanel.add(scroll);

	}

	public void setUpInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout(4, 1));
		slider = new JSlider();
		slider.setMinimum(0);
		slider.setMaximum(10);
		slider.setValue(5);
		taken = new JCheckBox();
		
		infoPanel.add(slider);
		info = new JTextArea();
		info.setSize(200,400);
		infoPanel.add(info);
		infoPanel.add(taken);
		infoPanel.setSize(200,800);
		mainPanel.add(infoPanel);

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
		
		//System.out.println(c.toString(true));
		taken.setSelected(c.isTaken());
		slider.setValue(c.getPreference());
		info.setText(c.getDescription());
		
	}
}