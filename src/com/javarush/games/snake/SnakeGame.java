package com.javarush.games.snake;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    private static final int GOAL = 28;

    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);

        if (!snake.isAlive) gameOver();
        if (!apple.isAlive) {
            createNewApple();

            score += 5;
            setScore(score);

            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        ;
        if (snake.getLength() > GOAL) win();

        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE) {
            if (isGameStopped) createGame();
        } else if (key == Key.LEFT) snake.setDirection(Direction.LEFT);
        else if (key == Key.RIGHT) snake.setDirection(Direction.RIGHT);
        else if (key == Key.UP) snake.setDirection(Direction.UP);
        else if (key == Key.DOWN) snake.setDirection(Direction.DOWN);
    }

    private void createGame() {
        score = 0;
        setScore(score);

        turnDelay = 300;
        setTurnTimer(turnDelay);

        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();

        isGameStopped = false;

        drawScene();
    }

    private void gameOver() {
        isGameStopped = true;
        stopTurnTimer();
        showMessageDialog(Color.BLACK, "Game over", Color.RED, 60);
    }

    private void win() {
        isGameStopped = true;
        stopTurnTimer();
        showMessageDialog(Color.WHITE, "YOU WIN !!!", Color.GREEN, 60);
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.AQUA, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    private void createNewApple() {
        while (true) {
            int newX = getRandomNumber(WIDTH);
            int newY = getRandomNumber(HEIGHT);
            Apple new_apple = new Apple(newX, newY);
            if (!snake.checkCollision(new_apple)) {
                apple = new_apple;
                break;
            }
        }
    }
}
