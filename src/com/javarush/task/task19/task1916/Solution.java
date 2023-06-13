package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bfr1 = new BufferedReader(new FileReader(br.readLine()));
             BufferedReader bfr2 = new BufferedReader(new FileReader(br.readLine()))) {

            List<String> lines1 = new ArrayList<>();
            while (bfr1.ready()) lines1.add(bfr1.readLine());

            List<String> lines2 = new ArrayList<>();
            while (bfr2.ready()) lines2.add(bfr2.readLine());

            String l1, l2;
            int i = 0, j = 0;
            while (i < lines1.size() || j < lines2.size()) {
                l1 = getLine(lines1, i);
                l2 = getLine(lines2, j);

                if (l1.equals(l2)) {
                    lines.add(new LineItem(Type.SAME, l1));
                    i++;
                    j++;
                } else if (l1.isEmpty()) {
                    while (j < lines2.size()) {
                        lines.add(new LineItem(Type.ADDED, lines2.get(j++)));
                    }
                } else if (l2.isEmpty()) {
                    while (i < lines1.size()) {
                        lines.add(new LineItem(Type.REMOVED, lines1.get(i++)));
                    }
                } else if (l1.equals(getLine(lines2, j + 1))) {
                    lines.add(new LineItem(Type.ADDED, l2));
                    j++;
                } else {
                    lines.add(new LineItem(Type.REMOVED, l1));
                    i++;
                }
            }
        }
    }

    public static String getLine(List<String> linesList, int idx) {
        return idx < linesList.size() ? linesList.get(idx) : "";
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
