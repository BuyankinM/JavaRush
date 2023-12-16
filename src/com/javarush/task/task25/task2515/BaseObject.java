package com.javarush.task.task25.task2515;

public abstract class BaseObject {
    protected double x;
    protected double y;
    protected double radius;
    protected boolean isAlive;

    public boolean isAlive() {
        return isAlive;
    }

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

    public void draw(Canvas canvas) {
    }

    public void move() {
    }

    public void die() {
        this.isAlive = false;
    }

    public boolean isIntersect(BaseObject o) {
        double sqDist = Math.pow(x - o.getX(), 2.0) + Math.pow(y - o.getY(), 2.0);
        return sqDist < Math.max(Math.pow(radius, 2.0), Math.pow(o.radius, 2.0));
    }

    public void checkBorders(double minx, double maxx, double miny, double maxy) {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isAlive = true;
    }
}
