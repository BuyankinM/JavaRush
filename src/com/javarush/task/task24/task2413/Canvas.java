package com.javarush.task.task24.task2413;

public class Canvas {
    private int width, height;
    private char[][] matrix;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public void setPoint(double x, double y, char c) {
        int xi = (int) Math.round(x);
        int yi = (int) Math.round(y);
        if (xi < 0 || yi < 0 || xi >= matrix.length || yi >= matrix[0].length) return;
        matrix[yi][xi] = c;
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    setPoint(x + j, y + i, c);
                }
            }
        }
    }

    public void clear() {
        matrix = new char[height + 2][width + 2];
    }

    public void print() {
        for (int h = 0; h < matrix.length; h++) {
            for (int w = 0; w < matrix[0].length; w++) {
                System.out.print(matrix[h][w]);
            }
            System.out.println();
        }
    }

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new char[height + 2][width + 2];
    }
}
