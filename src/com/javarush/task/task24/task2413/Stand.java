package com.javarush.task.task24.task2413;

public class Stand extends BaseObject {
    private double speed, direction;

    @Override
    void draw(Canvas canvas) {

    }

    @Override
    void move() {
        x += direction * speed;
    }

    public void moveLeft() {
        direction = -1;
    }

    public void moveRight() {
        direction = 1;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public Stand(double x, double y) {
        super(x, y, 3);
        speed = 1;
        direction = 0;
    }
}
