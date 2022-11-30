package Assignment2.ServerUIComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import Assignment2.Robot;

public class RobotTable extends JTable {

    mydata myData = new mydata();
    List<Vector<Robot>> robots;

    public RobotTable(List<Vector<Robot>> _robots) {
        robots = _robots;
        this.setModel(myData);
        update();
    }

        // Updates the model.
    public void update() {
        myData.getData();
        this.setModel(myData);
        this.repaint();
    }

    // Gets the column value at the specified coordinates.
    class mydata extends AbstractTableModel {
        private String[] columnName = { "Name", "Coordinates", "Velocity", "Direction" };
        private String[][] data = { { "Name", "Coordinates", "Velocity", "Direction" } };

        public void getData() {
            ArrayList<String[]> list = new ArrayList<String[]>();
            String[] headings = { "Name", "Coordinates", "Velocity", "Direction" };
            list.add(headings);

            for (Vector<Robot> i : robots) {
                Robot r = i.elementAt(i.size()-1);
                list.add(new String[] { r.getName(), "(" + r.getX() + "," + -r.getY() + ")",
                        Integer.toString(r.getVelocity()), Integer.toString(r.getDirection()% 360) });
            }
            data = list.toArray(new String[list.size()][4]);
        }

        @Override
        public int getRowCount() {
            return data.length;

        }

        @Override
        public int getColumnCount() {
            return columnName.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

    }

}
