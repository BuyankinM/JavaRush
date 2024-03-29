package com.javarush.task.task24.task2413;

public abstract class BaseObject {

    protected double x, y, radius;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    boolean intersects(BaseObject o) {
        double dist = Math.sqrt(
                Math.pow(o.getX() - this.x, 2)
                        + Math.pow(o.getY() - this.y, 2)
        );
        return dist <= (this.radius > o.getRadius() ? this.radius : o.getRadius());
    }

    abstract void draw(Canvas canvas);

    abstract void move();

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}
