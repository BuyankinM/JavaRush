package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score;
    protected int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    protected void resetGameTiles() {
        score = 0;
        maxTile = 0;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }

        addTile();
        addTile();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.isEmpty()) return;

        int idx = (int) (emptyTiles.size() * Math.random());
        int val = (Math.random() < 0.9 ? 2 : 4);

        Tile t = emptyTiles.get(idx);
        t.value = val;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (Tile[] row : gameTiles) {
            for (Tile t : row) {
                if (t.isEmpty()) emptyTiles.add(t);
            }
        }
        return emptyTiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;

        Queue<Integer> zeros = new LinkedList<>();
        for (int i = 0; i < tiles.length; i++) {
            int curValue = tiles[i].value;

            if (curValue == 0) {
                zeros.add(i);
            } else if (!zeros.isEmpty()) {
                tiles[zeros.poll()].value = curValue;
                tiles[i].value = 0;
                zeros.add(i);

                isChanged = true;
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;

        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == 0) break;
            if (tiles[i].value != tiles[i + 1].value) continue;

            int newVal = 2 * tiles[i].value;
            tiles[i].value = newVal;
            tiles[i + 1].value = 0;
            i++;

            if (newVal > maxTile) maxTile = newVal;
            score += newVal;

            isChanged = true;
        }

        if (isChanged) compressTiles(tiles);
        return isChanged;
    }

    public void left() {
        if (isSaveNeeded) saveState(gameTiles);

        boolean needToAdd = false;
        for (Tile[] tiles : gameTiles) {
            if (compressTiles(tiles) | mergeTiles(tiles)) needToAdd = true;
        }
        if (needToAdd) addTile();

        isSaveNeeded = true;
    }

    public void down() {
        saveState(gameTiles);

        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right() {
        saveState(gameTiles);

        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up() {
        saveState(gameTiles);

        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    private void rotate() {
        Tile[][] newGameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int row = 0; row < FIELD_WIDTH; row++) {
            for (int col = 0; col < FIELD_WIDTH; col++) {
                newGameTiles[col][FIELD_WIDTH - row - 1] = gameTiles[row][col];
            }
        }
        gameTiles = newGameTiles;
    }

    public boolean canMove() {
        boolean result = false;

        for (int i = 0; i < 4; i++) {
            if (!result) {
                result = check();
            }
            rotate();
        }

        return result;
    }

    private boolean check() {
        for (int row = 0; row < FIELD_WIDTH; row++) {
            boolean zeroFind = false;

            for (int col = 0; col < FIELD_WIDTH; col++) {
                Tile t = gameTiles[row][col];

                // check compress
                if (t.isEmpty()) zeroFind = true;
                else if (zeroFind) return true;

                // check merge
                if (col < FIELD_WIDTH - 1) {
                    if (!t.isEmpty()
                            && t.value == gameTiles[row][col + 1].value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] newTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(newTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (previousStates.isEmpty()
                || previousScores.isEmpty()) return;

        gameTiles = previousStates.pop();
        score = previousScores.pop();
    }

    public void randomMove() {
        int idxMove = ((int) (Math.random() * 100)) % 4;
        if (idxMove == 0) left();
        else if (idxMove == 1) right();
        else if (idxMove == 2) up();
        else if (idxMove == 3) down();
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> moves = new PriorityQueue<>(4, Collections.reverseOrder());
        moves.offer(getMoveEfficiency(this::left));
        moves.offer(getMoveEfficiency(this::right));
        moves.offer(getMoveEfficiency(this::up));
        moves.offer(getMoveEfficiency(this::down));

        moves.poll().getMove().move();
    }

    public boolean hasBoardChanged() {
        if (previousStates.isEmpty()) return true;
        return sumTiles(previousStates.peek()) != sumTiles(gameTiles);
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency res;

        move.move(); // test move

        // calc efficiency
        if (!hasBoardChanged()) {
            res = new MoveEfficiency(-1, 0, move);
        } else {
            int numEmptyTiles = 0;
            for (Tile[] row : gameTiles)
                for (Tile tile : row)
                    if (tile.isEmpty()) numEmptyTiles++;

            res = new MoveEfficiency(numEmptyTiles, score, move);
        }
        rollback();
        return res;
    }

    private int sumTiles(Tile[][] tiles) {
        int res = 0;
        for (Tile[] row : tiles)
            for (Tile tile : row)
                res += tile.value;

        return res;
    }
}
