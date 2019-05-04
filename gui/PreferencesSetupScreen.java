package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import course_map.Course;
import main.ZPlanner;

public class PreferencesSetupScreen {

  private ZPlanner planner;

  JFrame olderStudentScreen;
  
  JPanel mainBorder;
  JPanel midSplit;
  
  JPanel rightBorder;
  JPanel topRight;
  JPanel centerRight;
  JPanel bottomRight;
  
  
  JList list;
  DefaultListModel model;

  public PreferencesSetupScreen(String title, ZPlanner planner) {
    this.planner = planner;
    olderStudentScreen = new JFrame();

    olderStudentScreen.setSize(400, 600);
    olderStudentScreen.setResizable(false);
    olderStudentScreen.setLocation(200, 100);

    olderStudentScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    olderStudentScreen.setTitle(title);
    olderStudentScreen.setVisible(true);

    listSetUp();
    rightSideSetUp();
    borderSetUp();

    System.out.println(planner.toString());
  }
  
  public void listSetUp() {
    model = new DefaultListModel<String>();
    
    list = new JList<String>(model);
    
    list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    list.setVisibleRowCount(-1);
    JScrollPane listScroller = new JScrollPane(list);
    
    model.add(0, "so");
    model.add(1, "currently");
    model.add(2, "this are strings");
    model.add(3, "but they will be courses");
    model.add(4, "and you'll pick one");
    model.add(5, "and it'll  be shown on the right side");
    model.add(6, "and the action listeners will respond to the new course");
  }

  public void rightSideSetUp() {
    rightBorder = new JPanel(new BorderLayout());
    topRight = new JPanel(new GridLayout(2, 1));
    centerRight = new JPanel(new BorderLayout()); 
    bottomRight = new JPanel(new BorderLayout());
    
    topRight.add(new JLabel("the selected course (ex cs261)"));
    topRight.add(new JCheckBox("taken?"));
    
    centerRight.add(BorderLayout.NORTH, new JLabel("Description"));
    JTextArea text = new JTextArea("Introduction to the operation of modern "
        + "interrupt-driven computer systems. Explores the representation "
        + "of software and information in binary memory, the primary components"
        + " of a CPU, multithreaded programming and basic interactions with an "
        + "Operating System. Prerequisite: Grade of “C-” or better in CS 159.:");
    text.setLineWrap( true );
    centerRight.add(BorderLayout.CENTER, text);
    
    bottomRight.add(BorderLayout.NORTH, new JLabel("Preference:"));
    bottomRight.add(BorderLayout.CENTER, new JSlider());
    
    rightBorder.add(BorderLayout.NORTH, topRight);
    rightBorder.add(BorderLayout.CENTER, centerRight);
    rightBorder.add(BorderLayout.SOUTH, bottomRight);
  }

  
  public void borderSetUp() {
    mainBorder = new JPanel(new BorderLayout());
    midSplit = new JPanel(new GridLayout(1, 2));
    
    midSplit.add(list);
    midSplit.add(rightBorder);
    
    mainBorder.add(BorderLayout.NORTH, new JLabel("PREFERENCES"));
    mainBorder.add(BorderLayout.CENTER, midSplit);

    olderStudentScreen.add(mainBorder);
  }
}
