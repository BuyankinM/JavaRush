package com.javarush.games.snake;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";

    public int x, y;
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;

        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        GameObject p1 = snakeParts.get(0);
        GameObject p2 = snakeParts.get(1);

        if ((p1.y == p2.y && (direction == Direction.LEFT || direction == Direction.RIGHT)) ||
                (p1.x == p2.x && (direction == Direction.UP || direction == Direction.DOWN))) {
            return;
        }

        if (Math.abs(this.direction.ordinal() - direction.ordinal()) != 2) {
            this.direction = direction;
        }
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (newHead.x < 0 || newHead.x >= SnakeGame.WIDTH
                || newHead.y < 0 || newHead.y >= SnakeGame.HEIGHT
                || checkCollision(newHead)) {

            isAlive = false;
            return;
        }

        snakeParts.add(0, newHead);

        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject part : snakeParts) {
            if (gameObject.x == part.x && gameObject.y == part.y) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }

    public GameObject createNewHead() {
        GameObject head = snakeParts.get(0);
        int newX = head.x, newY = head.y;
        switch (direction) {
            case LEFT: {
                newX--;
                break;
            }
            case RIGHT: {
                newX++;
                break;
            }
            case UP: {
                newY--;
                break;
            }
            case DOWN: {
                newY++;
                break;
            }
        }
        return new GameObject(newX, newY);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public void draw(Game game) {
        Color color = (isAlive) ? Color.GREEN : Color.RED;
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);
            if (i == 0) {
                game.setCellValueEx(part.x, part.y, Color.NONE, HEAD_SIGN, color, 75);
            } else {
                game.setCellValueEx(part.x, part.y, Color.NONE, BODY_SIGN, color, 75);
            }
        }
    }
}
