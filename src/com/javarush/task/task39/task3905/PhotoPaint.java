package com.javarush.task.task39.task3905;

import java.util.LinkedList;
import java.util.Queue;

public class PhotoPaint {
    private class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        int h = image.length;
        int w = image[0].length;
        if (desiredColor == null
                || x < 0 || x >= w
                || y < 0 || y >= h
                || image[y][x] == desiredColor) {
            return false;
        }

        LinkedList<Point> queue = new LinkedList<>();
        queue.addFirst(new Point(x, y));
        Color curColor = image[y][x];

        while (!queue.isEmpty()) {
            Point p = queue.pollLast();
            if (p.x < 0 || p.x >= w
                    || p.y < 0 || p.y >= h
                    || image[p.y][p.x] != curColor) {
                continue;
            }
            image[p.y][p.x] = desiredColor;
            queue.addFirst(new Point(x + 1, y));
            queue.addFirst(new Point(x - 1, y));
            queue.addFirst(new Point(x, y + 1));
            queue.addFirst(new Point(x, y - 1));
        }
        return true;
    }
}
