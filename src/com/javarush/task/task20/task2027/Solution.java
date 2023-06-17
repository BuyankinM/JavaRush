package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'z', 'r', 'r', 'h'},
                {'p', 'o', 'r', 'e', 'j', 'j'}
        };
        List<Word> words = detectAllWords(crossword, "rr");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
        for (Word w : words) {
            System.out.println(w);
        }
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        int rows = crossword.length;
        int cols = crossword[0].length;

        List<Word> findWords = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                char c = (char) crossword[i][j];

                for (String word : words) {
                    char let = word.charAt(0);
                    if (c != let) continue;

                    // forward-row
                    int endCol = j + 1;
                    int count = 1;
                    while (endCol < cols && count < word.length()) {
                        char nextLet = (char) crossword[i][endCol];
                        if (nextLet != word.charAt(count)) break;
                        endCol++;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(endCol - 1, i);
                        findWords.add(w);
                    }

                    // backward-row
                    endCol = j - 1;
                    count = 1;
                    while (endCol >= 0 && count < word.length()) {
                        char nextLet = (char) crossword[i][endCol];
                        if (nextLet != word.charAt(count)) break;
                        endCol--;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(endCol + 1, i);
                        findWords.add(w);
                    }

                    // forward-col
                    int endRow = i + 1;
                    count = 1;
                    while (endRow < rows && count < word.length()) {
                        char nextLet = (char) crossword[endRow][j];
                        if (nextLet != word.charAt(count)) break;
                        endRow++;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(j, endRow - 1);
                        findWords.add(w);
                    }

                    // backward-col
                    endRow = i - 1;
                    count = 1;
                    while (endRow >= 0 && count < word.length()) {
                        char nextLet = (char) crossword[endRow][j];
                        if (nextLet != word.charAt(count)) break;
                        endRow--;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(j, endRow + 1);
                        findWords.add(w);
                    }

                    // forward-diagonal
                    endRow = i + 1;
                    endCol = j + 1;
                    count = 1;
                    while (endRow < rows && endCol < cols && count < word.length()) {
                        char nextLet = (char) crossword[endRow][endCol];
                        if (nextLet != word.charAt(count)) break;
                        endRow++;
                        endCol++;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(endCol - 1, endRow - 1);
                        findWords.add(w);
                    }

                    // backward-diagonal
                    endRow = i - 1;
                    endCol = j - 1;
                    count = 1;
                    while (endRow >= 0 && endCol >= 0 && count < word.length()) {
                        char nextLet = (char) crossword[endRow][endCol];
                        if (nextLet != word.charAt(count)) break;
                        endRow--;
                        endCol--;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(endCol + 1, endRow + 1);
                        findWords.add(w);
                    }

                    // forward-diagonal-2
                    endRow = i - 1;
                    endCol = j + 1;
                    count = 1;
                    while (endRow >= 0 && endCol < cols && count < word.length()) {
                        char nextLet = (char) crossword[endRow][endCol];
                        if (nextLet != word.charAt(count)) break;
                        endRow--;
                        endCol++;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(endCol - 1, endRow + 1);
                        findWords.add(w);
                    }

                    // backward-diagonal-2
                    endRow = i + 1;
                    endCol = j - 1;
                    count = 1;
                    while (endRow < rows && endCol >= 0 && count < word.length()) {
                        char nextLet = (char) crossword[endRow][endCol];
                        if (nextLet != word.charAt(count)) break;
                        endRow++;
                        endCol--;
                        count++;
                    }
                    if (count == word.length()) {
                        Word w = new Word(word);
                        w.setStartPoint(j, i);
                        w.setEndPoint(endCol + 1, endRow - 1);
                        findWords.add(w);
                    }

                }
            }
        }
        return findWords;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
