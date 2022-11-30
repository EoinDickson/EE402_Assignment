package Assignment2;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener, ChangeListener, WindowListener {

      private JButton right, left, forward, backward, stop;
      private JPanel buttonGrid, velocityGrid;
      private JSlider velocity;
      private JLabel velocityLabel, connectionStatus, connectionTime;
      private Robot robot;

      // Client GUI client.
      public ClientGUI(Robot _robot) {
            super("RobotClient");

            // Robot
            robot = _robot;

            // Button Grid
            this.buttonGrid = new JPanel();
            this.buttonGrid.setLayout(new GridLayout(4, 3));

            // Create Buttons
            this.forward = new JButton("Forward");
            this.buttonGrid.add(forward);
            this.forward.addActionListener(this);

            this.backward = new JButton("Backward");
            this.buttonGrid.add(backward);
            this.backward.addActionListener(this);

            this.left = new JButton("Rotate Left");
            this.buttonGrid.add(left);
            this.left.addActionListener(this);

            this.right = new JButton("Rotate Right");
            this.buttonGrid.add(right);
            this.right.addActionListener(this);

            this.stop = new JButton("Stop");//Extra Feature
            this.buttonGrid.add(stop);
            this.stop.addActionListener(this);

            // Add buttonGrid to the JFrame
            this.add(buttonGrid, BorderLayout.SOUTH);

            // Velocity Grid
            this.velocityGrid = new JPanel();
            this.velocityGrid.setLayout(new GridLayout(4, 1));

            // Add to VelocityGrid
            this.connectionStatus = new JLabel("connected");
            this.velocityGrid.add(connectionStatus);
            this.connectionTime = new JLabel("No time");
            this.velocityGrid.add(connectionTime);
            this.velocityLabel = new JLabel("Velocity");
            this.velocityLabel.setHorizontalAlignment(JLabel.CENTER);
            this.velocityGrid.add(velocityLabel);
            this.velocity = new JSlider(0, 5,1);
            this.velocity.setPaintTrack(true);
            this.velocity.setMajorTickSpacing(1);
            this.velocity.setPaintTicks(true);
            this.velocity.createStandardLabels(1);
            this.velocity.setPaintLabels(true);
            this.velocityGrid.add(velocity);
            this.velocity.addChangeListener(this);
            this.add(velocityGrid, BorderLayout.NORTH);

            // Window listener
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

            // Performs the action.
      public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(forward)) {
                  System.out.println("Forward Clicked");
                  this.robot.forward();
            } else if (e.getSource().equals(backward)) {
                  System.out.println("Backward Clicked");
                  this.robot.backward();
            } else if (e.getSource().equals(left)) {
                  System.out.println("Left Clicked");
                  this.robot.left();
            } else if (e.getSource().equals(right)) {
                  System.out.println("Right Clicked");
                  this.robot.right();
            } else if (e.getSource().equals(stop)) {
                  this.robot.velocity(0);
            }
      }

      public void stateChanged(ChangeEvent e) {
            if (e.getSource().equals(velocity)) {
                  // JSlider source = (JSlider) e.getSource();

                  // int dAngle = (int) source.getValue();
                  // System.out.println(dAngle);
                  JSlider source = (JSlider) e.getSource();
                  robot.velocity((int) source.getValue());
            }
      }

      // Updates the connection status.
      public void update(LocalTime time, boolean c) {
            if (c == true) {
                  this.connectionTime.setText(time.toString());
                  this.connectionStatus.setText("Connected");
            } else {
                  this.connectionStatus.setText("Not Connected");
            }
            this.repaint();
      }

}