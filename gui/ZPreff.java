package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

	private JTree tree;
	DefaultMutableTreeNode root;
	JPanel treePanel;
	JPanel infoPanel;

	public ZPreff(String title, Planner planner) {
		root = new DefaultMutableTreeNode(planner.toString());
		planner.getPrograms().stream().forEach(p2 -> root.add(getProgramNodes(p2)));
		tree = new JTree(root);
		tree.putClientProperty("JTree.lineStyle", "Horizontal");
		add(tree);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTree Example");
		//this.pack();
		this.setSize(350, 800);
		this.setVisible(true);
				tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				try {
					String s = e.toString().split(",")[3].split("\\]")[0].substring(1).split("]")[0];
					System.out.println(Generator.findCourse(s.split(" ")[0], s.split(" ")[1]).toString(true));
				} catch (ArrayIndexOutOfBoundsException x) {

				}
			}
		});
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