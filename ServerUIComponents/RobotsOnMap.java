package Assignment2.ServerUIComponents;

import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import Assignment2.Robot;

public class RobotsOnMap extends JLabel {

    List<Vector<Robot>> robots;

    public RobotsOnMap(List<Vector<Robot>> _robots) {
        robots = _robots;
        this.setText("ALL Robots On Map");
    }

        // Updates the display of the robots off map
    public void update() {
        for (Vector<Robot> r : robots) {
            if (Math.abs(r.elementAt(r.size() - 1).getX()) > 375 || Math.abs(r.elementAt(r.size() - 1).getY()) > 375) {
                this.setText("Robots off map");
            } else {
                this.setText("ALL Robots On Map");
            }
        }
    }

}
