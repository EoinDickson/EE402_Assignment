package Assignment2;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import Assignment2.ServerUIComponents.*;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ServerGUI extends JFrame implements WindowListener, ChangeListener {

  // private List<Queue<Robot>> robots = new ArrayList<Queue<Robot>>();
  private List<Vector<Robot>> robots = new ArrayList<Vector<Robot>>();


  private Map mapCanvas = new Map(750, 750, robots);
  private JPanel northPanel = new JPanel();
  private SelectedRobot jLabel = new SelectedRobot(robots);
  private DangerLabel dangerLabel = new DangerLabel(robots);
  private RobotTable robotTable = new RobotTable(robots);
  private RobotsOnMap robotsOnMap = new RobotsOnMap(robots);
  private JSlider dangerMargin;

  public ServerGUI() {
    super("ServerUI");

    // NORTH
    // NorthPanel
    northPanel.setLayout(new GridLayout(3, 1));
    this.add(northPanel, BorderLayout.NORTH);
    // SelectedRobot
    this.northPanel.add(jLabel, BorderLayout.NORTH);
    // Danger Notification
    this.northPanel.add(dangerLabel, BorderLayout.NORTH);
    //Robots on map
    this.northPanel.add(robotsOnMap,BorderLayout.NORTH);

    // CENTER
    // Create Map
    this.add(mapCanvas, BorderLayout.CENTER);

    // SOUTH
    // Robot Table
    this.add(robotTable, BorderLayout.SOUTH);

    //EAST
    this.dangerMargin = new JSlider(JSlider.VERTICAL,0, 50,10);
    dangerMargin.setBorder(BorderFactory.createTitledBorder("Danger"));

    this.dangerMargin.createStandardLabels(5);
    this.dangerMargin.setPaintTrack(true);
    this.dangerMargin.setMajorTickSpacing(5);
    this.dangerMargin.setPaintTicks(true);
    this.dangerMargin.setPaintLabels(true);
    this.dangerMargin.addChangeListener(this);

    this.add(dangerMargin, BorderLayout.EAST);

    // Add Window Listener
    this.addWindowListener(this);
    this.pack();
    this.setVisible(true);
  }

  public void windowActivated(WindowEvent arg0) {
  }

  public void windowClosed(WindowEvent arg0) {
  }

  public void windowClosing(WindowEvent arg0) {
    System.exit(0);
  }

  public void windowDeactivated(WindowEvent arg0) {
  }

  public void windowDeiconified(WindowEvent arg0) {
  }

  public void windowIconified(WindowEvent arg0) {
  }

  public void windowOpened(WindowEvent arg0) {
  }

  public void update() {

    // Table Update
    robotTable.update();
    // Danger Update
    dangerLabel.update();
    //Update robot on map
    robotsOnMap.update();
    // Update Map
    mapCanvas.update();

    // Table Listener
    robotTable.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        String Name = robotTable.getModel().getValueAt(robotTable.getSelectedRow(), 0).toString();
        jLabel.update(Name);
      }
    });

    this.pack();

  }

  // Add robots to a list.
  public void addRobotsToList(Vector<Robot> _robots) {
    boolean i = false;

    for (Vector<Robot> q : robots) {
      // Robot t = q.peek();
      Robot t = q.elementAt(0);
      // Robot g = _robots.peek();
      Robot g = _robots.elementAt(0);
      if (t.getName().equals(g.getName())) {
        robots.set(robots.indexOf(q), _robots);
        System.out.println("Deleted : " + t.getName());
        i = true;
        break;
      }
    }
    if (i == false)
      robots.add(_robots);
    System.out.println("BEFORE GETGUI");
    update();
  }

  // state changed.
  public void stateChanged(ChangeEvent e) {
    if (e.getSource().equals(dangerMargin)) {
          JSlider source = (JSlider) e.getSource();
          dangerLabel.setDangerMargin((int) source.getValue());
          System.out.print("Danger = " + (int) source.getValue());
    }
}

}