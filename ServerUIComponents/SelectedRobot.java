package Assignment2.ServerUIComponents;

import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import Assignment2.Robot;

@SuppressWarnings("serial")
public class SelectedRobot extends JLabel {

    List<Vector<Robot>> robots;

    public SelectedRobot(List<Vector<Robot>> _robots) {
        this.setText("No Robot Selected");
        robots = _robots;
    }

        // Returns true if the request was successful.
    public void update(String Name) {
        for (Vector<Robot> r : robots) {
            Robot g = r.elementAt(0);

            if (Name.equals(g.getName())) {
                this.setText("Robot Info -->" + "Name : " + g.getName() + ". Dimension : " + g.getSize() + ".");
            }
        }
    }

}
