package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.Arrays;
import java.util.Collections;

public class Game2048 extends Game {

    private static final int SIDE = 4;

    private int[][] gameField;

    private boolean isGameStopped = false;

    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);

        createGame();

        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (!isGameStopped && !canUserMove()) {
            gameOver();
            return;
        }

        if (!isGameStopped) {
            if (key == Key.LEFT) moveLeft();
            else if (key == Key.RIGHT) moveRight();
            else if (key == Key.UP) moveUp();
            else if (key == Key.DOWN) moveDown();
            else return;
        } else if (key == Key.SPACE) {
            isGameStopped = false;
            createGame();
        } else {
            return;
        }

        drawScene();
    }

    private void createGame() {
        score = 0;
        setScore(score);

        gameField = new int[SIDE][SIDE];

        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private void createNewNumber() {
        if (getMaxTileValue() == 2048) win();

        int x, y;
        while (true) {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
            if (gameField[x][y] == 0) break;
        }
        gameField[x][y] = getRandomNumber(10) == 9 ? 4 : 2;
    }

    private Color getColorByValue(int value) {
        Color resColor = Color.WHITE;

        if (value == 2) resColor = Color.BLUE;
        else if (value == 4) resColor = Color.GREEN;
        else if (value == 8) resColor = Color.RED;
        else if (value == 16) resColor = Color.ORANGE;
        else if (value == 32) resColor = Color.YELLOW;
        else if (value == 64) resColor = Color.MAROON;
        else if (value == 128) resColor = Color.MAGENTA;
        else if (value == 256) resColor = Color.SEASHELL;
        else if (value == 512) resColor = Color.BURLYWOOD;
        else if (value == 1024) resColor = Color.AQUA;
        else if (value == 2048) resColor = Color.DARKCYAN;

        return resColor;
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color cellColor = getColorByValue(value);
        String cellText = (value > 0) ? String.valueOf(value) : "";
        setCellValueEx(x, y, cellColor, cellText);
    }

    private boolean compressRow(int[] row) {
        boolean isCompressed = false;
        boolean isFindNextSwap = false;

        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] > 0) continue;

            isFindNextSwap = false;
            for (int j = i + 1; j < row.length; j++) {
                if (row[j] == 0) continue;

                isFindNextSwap = true;
                isCompressed = true;

                int tmp = row[i];
                row[i] = row[j];
                row[j] = tmp;
                break;
            }
            if (!isFindNextSwap) break;
        }
        return isCompressed;
    }

    private boolean mergeRow(int[] row) {
        boolean isMerged = false;
        int i = 0;

        while (i < row.length - 1) {
            if (row[i] == row[i + 1] && row[i] > 0) {
                score += 2 * row[i];
                setScore(score);

                row[i] *= 2;
                row[i + 1] = 0;
                i++;

                isMerged = true;
            }
            i++;
        }
        return isMerged;
    }

    private void moveLeft() {
        boolean isChanged = false;
        for (int i = 0; i < SIDE; i++) {
            int[] row = gameField[i];
            if (compressRow(row) | mergeRow(row)) {
                isChanged = true;
                compressRow(row);
            }
        }
        if (isChanged) createNewNumber();
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();

        moveLeft();

        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();

        moveLeft();

        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();

        moveLeft();

        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] rotField = new int[SIDE][SIDE];
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                rotField[x][y] = gameField[SIDE - y - 1][x];
            }
        }
        gameField = rotField;
    }

    private int getMaxTileValue() {
        int maxVal = Integer.MIN_VALUE;
        for (int[] row : gameField) {
            for (int x : row) {
                if (x > maxVal) maxVal = x;
            }
        }
        return maxVal;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "You win!", Color.YELLOW, 75);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.YELLOW, "Game over!", Color.RED, 75);
    }

    private boolean canUserMove() {
        boolean hasZeros = false;
        boolean hasMove = false;

        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                int val = gameField[x][y];
                if (!hasZeros && val == 0) hasZeros = true;
                if (!hasMove && (x > 0 && val == gameField[x - 1][y]
                        || y > 0 && val == gameField[x][y - 1])) hasMove = true;
            }
        }
        return hasZeros || hasMove;
    }
}
