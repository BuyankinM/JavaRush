package com.javarush.task.task25.task2515;

import java.util.List;

public class SpaceShip extends BaseObject {
    //картинка корабля для отрисовки
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };
    private double dx;

    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }

    public void moveLeft() {
        dx = -1;
    }

    public void moveRight() {
        dx = 1;
    }

    @Override
    public void move() {
        x += dx;
        checkBorders(radius, Space.game.getWidth() - radius, radius, Space.game.getHeight() - radius);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'M');
    }

    public void fire() {
        Rocket r1 = new Rocket(getX() - 2, getY());
        Rocket r2 = new Rocket(getX() + 2, getY());

        List<Rocket> rockets = Space.game.getRockets();
        rockets.add(r1);
        rockets.add(r2);
    }
}
