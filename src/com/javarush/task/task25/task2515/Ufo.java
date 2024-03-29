package com.javarush.task.task25.task2515;

import java.util.List;

public class Ufo extends BaseObject {
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Ufo(double x, double y) {
        super(x, y, 3);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'U');
    }

    @Override
    public void move() {
        double dx = Math.random() * 2 - 1; //-1..1
        double dy = Math.random() * 2 - 1; //-1..1
        x += dx;
        y += dy;

        checkBorders(radius, Space.game.getWidth() - radius, radius, Space.game.getHeight() / 2);

        if (Math.random() * 10 < 1) {
            fire();
        }
    }

    public void fire() {
        List<Bomb> bombs = Space.game.getBombs();
        bombs.add(new Bomb(x, y + radius));
    }
}
