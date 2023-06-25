package com.javarush.task.task23.task2312;

import java.util.ArrayList;

public class Room {

    private int width, height;
    private Snake snake;
    private Mouse mouse;
    public static Room game;

    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void run() {
    }

    public void print() {
        int[][] matrix = new int[height][width];

        ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>(snake.getSections());
        for (SnakeSection snakeSection : sections) {
            matrix[snakeSection.getY()][snakeSection.getX()] = 1;
        }
        matrix[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;
        matrix[mouse.getY()][mouse.getX()] = 3;

        String[] symbols = {".", "x", "X", "^", "*"};
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(symbols[matrix[y][x]]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void createMouse() {
        int x = (int) (Math.random() * getWidth());
        int y = (int) (Math.random() * getHeight());
        mouse = new Mouse(x, y);
    }

    public void eatMouse() {
        createMouse();
    }

    public void sleep() throws InterruptedException {
        int duration = 0;
        int l = snake.getSections().size();

        if (l <= 11) duration = 500 - 20 * (l - 1);
        else if (l <= 15) duration = 300 - 25 * (l - 11);
        else duration = 200;

        Thread.sleep(duration);
    }

    public static void main(String[] args) {
        game = new Room(20, 20, new Snake(5, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
        game.print();
    }
}
