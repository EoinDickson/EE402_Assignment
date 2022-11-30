package Assignment2.ServerUIComponents;

import java.awt.*;
import java.util.List;
import java.util.Vector;
import Assignment2.Robot;

@SuppressWarnings("serial")
public class Map extends Canvas {

    int width, height;
    List<Vector<Robot>> robots;

    public Map(int width, int height, List<Vector<Robot>> _robots) {
        this.setSize(width, height);
        this.width = width;
        this.height = height;
        this.robots = _robots;
        this.update();
    }

        // Updates the repaint.
    public void update() {
        this.repaint();
    }

    // Paints the robots.
    public void paint(Graphics g) {
        drawGrid(g);

        for (Vector<Robot> q : robots) {
            int i = 0;
            
            for (Robot r : q) {
                int x = r.getX() + (int)(.5* width);
                int y = r.getY() + (int)(.5* width);
                int size = r.getSize();
                int velocity = r.getVelocity();
                int x2 = (int)(50*Math.sin(Math.toRadians(r.getDirection())));
                int y2 = -(int)(50*Math.cos(Math.toRadians(r.getDirection())));
                System.out.println("x : "+ x + " . x2 = " + x2);
                System.out.println("y : "+ y + " . y2 = " + y2);


                Color c = Color.LIGHT_GRAY;
                if (i == 1) {
                    c = Color.GRAY;
                } else if (i == 2) {
                    c = Color.DARK_GRAY;
                    g.setColor(Color.GRAY);
                    g.drawLine(x+(int)(.5*size),y+(int)(.5*size),x+x2+(int)(.5*size) ,y+y2+(int)(.5*size));
                    g.setColor(Color.BLACK);
                    String str = Integer.toString(velocity);
                    g.drawString(str, x + size - (str.length() / 2), y + size);

                }
                
                g.setColor(c);
                g.drawOval(x, y, size, size);
                g.fillOval(x, y, size, size);
                i++;
            }

        }
    }

    // Draws the grid.
    public void drawGrid(Graphics g){
        for(int i  = 0; i < width;){
            g.setColor(Color.GRAY);
            g.drawLine(0, i, width, i);
            g.drawLine(i, 0, i, height);
            i += 50;
        }

    }
}
