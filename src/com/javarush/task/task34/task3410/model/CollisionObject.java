package com.javarush.task.task34.task3410.model;

import java.awt.*;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int newX = this.getX();
        int newY = this.getY();

        switch (direction) {
            case UP:
                newY -= Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                newY += Model.FIELD_CELL_SIZE;
                break;
            case LEFT:
                newX -= Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                newX += Model.FIELD_CELL_SIZE;
                break;
        }

        return newX == gameObject.getX() && newY == gameObject.getY();
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
