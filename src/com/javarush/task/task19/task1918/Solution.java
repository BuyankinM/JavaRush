package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader cons = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader bfr = new BufferedReader(new FileReader(cons.readLine()))) {

            StringBuilder sb = new StringBuilder();
            while (bfr.ready()) sb.append(bfr.readLine());
            String resLine = sb.toString();

            String startTag = String.format("<%s", args[0]);
            String endTag = String.format("</%s>", args[0]);

            int idx = 0;
            ArrayList<int[]> listTags = new ArrayList<>();
            Pattern pattern = Pattern.compile(String.format("%s|%s", startTag, endTag));
            Matcher matcher = pattern.matcher(resLine);
            while (matcher.find()) {
                if (startTag.equals(matcher.group())) {
                    listTags.add(new int[]{matcher.start(), -1});
                    idx++;
                } else {
                    listTags.get(--idx)[1] = matcher.end();
                }

                if (idx == 0) {
                    for (int[] startEnd : listTags) {
                        System.out.println(resLine.substring(startEnd[0], startEnd[1]));
                    }
                    listTags.clear();
                }
            }
        }
    }
}
