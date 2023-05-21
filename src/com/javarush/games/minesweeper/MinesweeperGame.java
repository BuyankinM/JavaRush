package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {

    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    private int countMinesOnField;
    private int countFlags;
    private boolean isGameStopped;


    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
        } else {
            openTile(x, y);
        }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x, y, "");

                boolean isMine = getRandomNumber(10) == 0;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.AQUAMARINE);
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "Game over!", Color.YELLOW, 10);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;

        setScore(score);
        createGame();
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "YOU WIN!", Color.YELLOW, 14);
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                GameObject gameObject = gameField[y][x];
                if (gameObject.isMine) {
                    continue;
                }

                for (GameObject go : getNeighbors(gameObject)) {
                    if (go.isMine) {
                        gameObject.countMineNeighbors++;
                    }
                }
            }
        }
    }

    private void openTile(int x, int y) {
        if (isGameStopped) return;

        GameObject gameObject = gameField[y][x];
        if (gameObject.isOpen || gameObject.isFlag) return;

        gameObject.isOpen = true;
        setCellColor(x, y, Color.GREENYELLOW);
        countClosedTiles--;

        if (gameObject.isMine) {
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        } else {
            if (countClosedTiles == countMinesOnField) win();

            score += 5;
            setScore(score);

            if (gameObject.countMineNeighbors == 0) {
                for (GameObject neighbor : getNeighbors(gameObject)) {
                    if (!neighbor.isOpen) openTile(neighbor.x, neighbor.y);
                }
                setCellValue(x, y, "");
            } else {
                setCellNumber(x, y, gameObject.countMineNeighbors);
            }
        }
    }

    private void markTile(int x, int y) {
        GameObject gameObject = gameField[y][x];
        if (gameObject.isOpen) return;
        if (countFlags == 0 && !gameObject.isFlag) return;

        if (!gameObject.isFlag) {
            gameObject.isFlag = true;
            countFlags--;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.YELLOW);
        } else {
            gameObject.isFlag = false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.AQUAMARINE);
        }
    }
}
