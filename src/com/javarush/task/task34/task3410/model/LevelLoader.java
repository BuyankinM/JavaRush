package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private final Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        if (level > 60)
            level = level % 60;

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        try (BufferedReader br = new BufferedReader(new FileReader(levels.toString()))) {
            String levLine, yLine, levelLine;
            while (br.ready()) {
                br.readLine(); // border
                levLine = br.readLine();
                br.readLine(); // tech. info
                br.readLine(); // X-line
                yLine = br.readLine();

                for (int i = 0; i < 3; i++)
                    br.readLine(); // tech. info

                int curLevel = Integer.parseInt(levLine.substring(6));
                int height = Integer.parseInt(yLine.substring(8));

                if (curLevel < level) {
                    // skip level
                    for (int i = 0; i < height; i++) br.readLine();
                } else {
                    // read level
                    int cellSize = Model.FIELD_CELL_SIZE;
                    int curX, curY;
                    for (int i = 0; i < height; i++) {
                        levelLine = br.readLine();
                        for (int j = 0; j < levelLine.length(); j++) {
                            curX = cellSize / 2 + j * cellSize;
                            curY = cellSize / 2 + i * cellSize;

                            char c = (char) levelLine.charAt(j);
                            switch (c) {
                                case 'X':
                                    walls.add(new Wall(curX, curY));
                                    break;
                                case '*':
                                    boxes.add(new Box(curX, curY));
                                    break;
                                case '.':
                                    homes.add(new Home(curX, curY));
                                    break;
                                case '&':
                                    homes.add(new Home(curX, curY));
                                    boxes.add(new Box(curX, curY));
                                    break;
                                case '@':
                                    player = new Player(curX, curY);
                            }
                        }
                    }
                    break; //stop reading
                }
                br.readLine(); // empty line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
