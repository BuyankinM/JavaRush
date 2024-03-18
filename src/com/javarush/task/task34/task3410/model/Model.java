package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 60;
    private LevelLoader levelLoader;

    public Model() {
        try {
            levelLoader = new LevelLoader(Paths.get(getClass().getResource("../res/levels.txt").toURI()));
        } catch (URISyntaxException e) {
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)
                || checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }

        int dx = 0;
        int dy = 0;

        if (direction == Direction.LEFT) dx = -FIELD_CELL_SIZE;
        else if (direction == Direction.RIGHT) dx = FIELD_CELL_SIZE;
        else if (direction == Direction.UP) dy = -FIELD_CELL_SIZE;
        else dy = FIELD_CELL_SIZE;

        player.move(dx, dy);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return gameObjects
                .getWalls()
                .stream()
                .anyMatch(wall -> gameObject.isCollision(wall, direction));
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Box boxCollision = gameObjects
                .getBoxes()
                .stream()
                .filter(box -> player.isCollision(box, direction))
                .findFirst()
                .orElse(null);

        if (boxCollision != null) {
            if (checkWallCollision(boxCollision, direction)
                    || gameObjects
                    .getBoxes()
                    .stream()
                    .anyMatch(box -> !box.equals(boxCollision)
                            && boxCollision.isCollision(box, direction))) {

                // путь заблокирован ящиком и за ним стена или другой ящик
                return true;
            }

            // сдвиг ящик в сторону движения на 1 клетку
            int dx = 0;
            int dy = 0;

            if (direction == Direction.LEFT) dx = -FIELD_CELL_SIZE;
            else if (direction == Direction.RIGHT) dx = FIELD_CELL_SIZE;
            else if (direction == Direction.UP) dy = -FIELD_CELL_SIZE;
            else dy = FIELD_CELL_SIZE;

            boxCollision.move(dx, dy);
        }
        return false;
    }

    public void checkCompletion() {
        if (gameObjects
                .getBoxes()
                .stream()
                .allMatch(box -> gameObjects
                        .getHomes()
                        .stream()
                        .anyMatch(home -> home.getX() == box.getX()
                                && home.getY() == box.getY()))) {

            eventListener.levelCompleted(currentLevel);
        }
    }
}