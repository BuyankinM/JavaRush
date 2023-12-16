package com.javarush.task.task25.task2515;

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height][width];
    }

    public void setPoint(double x, double y, char c) {
        int xx = Math.round((float) x);
        int yy = Math.round((float) y);
        if (xx >= 0 && xx < matrix[0].length
                && yy >= 0 && yy < matrix.length) {
            matrix[yy][xx] = c;
        }
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int h = 0; h < matrix.length; h++) {
            for (int w = 0; w < matrix[0].length; w++) {
                if (matrix[h][w] != 0) {
                    setPoint(x + w, y + h, c);
                }
            }
        }
    }

    public void clear() {
        for (int h = 0; h < matrix.length; h++) {
            for (int w = 0; w < matrix[0].length; w++) {
                matrix[h][w] = ' ';
            }
        }
    }

    public void print() {
        for (int h = 0; h < matrix.length; h++) {
            for (int w = 0; w < matrix[0].length; w++) {
                System.out.print(matrix[h][w]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
