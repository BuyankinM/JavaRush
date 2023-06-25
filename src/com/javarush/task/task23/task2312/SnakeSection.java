package com.javarush.task.task23.task2312;

public class SnakeSection {
    private int x, y;

    public SnakeSection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x + 1000 * y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SnakeSection snakeSection = (SnakeSection) obj;
        return x == snakeSection.x && y == snakeSection.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
