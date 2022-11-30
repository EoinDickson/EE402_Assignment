package Assignment2.ServerUIComponents;

import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import Assignment2.Robot;

@SuppressWarnings("serial")
public class DangerLabel extends JLabel {

  List<Vector<Robot>> robots;
  int dangerMargin = 10;

  public DangerLabel(List<Vector<Robot>> _robots) {
    this.setText("No Danger");
    robots = _robots;
    this.update();
  }

    // Updates the collision detection.
  public void update() {
    String[] d = collisionDetection();
    if (d != null) {
      this.setText("DANGER BETWEEN " + d[0] + " and " + d[1]);
    } else {
      this.setText("No Danger");
    }
  }

    // Computes the collision detection for all robots.
  public String[] collisionDetection() {
    for (Vector<Robot> r : robots) {
      for (Vector<Robot> t : robots) {
        if (r != t) {
          Robot one = r.elementAt(r.size()-1);
          Robot two = t.elementAt(t.size()-1);

          double clearance = one.getSize() + two.getSize() + (Math.abs(one.getVelocity()) + Math.abs(two.getVelocity())) * dangerMargin;
          double distance = Math.sqrt(Math.pow((two.getX() - one.getX()), 2) + Math.pow((two.getY() - one.getY()), 2));
          if (clearance > distance) {
            String[] out = { one.getName(), two.getName() };
            return out;
          }
        }
      }
    }
    return null;
  }

    // Sets the danger margin.
  public void setDangerMargin(int num){
    dangerMargin = num;
  }
}
