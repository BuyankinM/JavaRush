package com.javarush.task.task35.task3513;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public void resetGame() {
        model.score = 0;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ESCAPE) resetGame();
        else if (keyCode == KeyEvent.VK_Z) model.rollback();
        else if (keyCode == KeyEvent.VK_R) model.randomMove();
        else if (keyCode == KeyEvent.VK_A) model.autoMove();
        else if (!model.canMove()) view.isGameLost = true;
        else if (keyCode == KeyEvent.VK_LEFT) model.left();
        else if (keyCode == KeyEvent.VK_RIGHT) model.right();
        else if (keyCode == KeyEvent.VK_UP) model.up();
        else if (keyCode == KeyEvent.VK_DOWN) model.down();

        if (model.maxTile==WINNING_TILE) view.isGameWon = true;

        view.repaint();
    }

    public View getView() {
        return this.view;
    }
}
