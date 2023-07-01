package com.javarush.task.task24.task2413;

public class Ball extends BaseObject {
    private double speed;
    private double direction;
    private double dx, dy;
    private boolean isFrozen;

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    @Override
    void move() {
        if (isFrozen) return;
        x += dx;
        y += dy;
    }

    @Override
    void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'O');
    }

    void start() {
        isFrozen = false;
    }

    void setDirection(double direction) {
        this.direction = direction;
        double angle = Math.toRadians(direction);
        dx = Math.cos(angle) * speed;
        dy = -Math.sin(angle) * speed;
    }

    void checkRebound(int minx, int maxx, int miny, int maxy) {
    }

    public Ball(double x, double y, double speed, double direction) {
        super(x, y, 1);
        this.speed = speed;
        this.direction = direction;
        this.isFrozen = true;
    }
}
