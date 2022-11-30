package Assignment2;

import java.io.Serializable;
import java.lang.Math;
import java.time.LocalTime;

public class Robot implements Serializable, Cloneable {
    private String name;
    private int x;
    private int y;
    private int size;
    private int velocity;
    private int direction;// 0-359
    private LocalTime time = LocalTime.now();

    public Robot(int _size, String _name) {
        name = _name;
        size = _size;
        x = 0;
        y = 0;
        velocity = 1;
        direction = 0;
    }

    public void update() {
        x += (int) (velocity * Math.sin(Math.toRadians(direction)) * 10);
        y -= (int) (velocity * Math.cos(Math.toRadians(direction)) * 10);
        time = LocalTime.now();
    }

    public void forward() {
        velocity = Math.abs(velocity);
    }

    public void backward() {
        velocity = -Math.abs(velocity);
        
    }

    public void right() {
        direction += 5;
    }

    public void left() {
        direction -= 5;
    }
    public void velocity(int _velocity){
        velocity = _velocity;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getDirection() {
        return direction;
    }

    public Robot clone() throws CloneNotSupportedException {
        return (Robot) super.clone();
    }

    public void currentPos() {
        x += (int) (velocity * Math.cos(direction) * 10);
        y += (int) (velocity * Math.sin(direction) * 10);
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime(){
        return time;
    }
}
