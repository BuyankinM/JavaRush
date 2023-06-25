package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/* 
Составить цепочку слов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String[] words;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fr = new BufferedReader(new FileReader(br.readLine()));) {
            words = fr.readLine().split(" ");
        }
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        if (words.length == 0) return sb;

        HashMap<Character, Integer> counter = new HashMap<>();
        for (String w : words) {
            char start = Character.toLowerCase(w.charAt(0));
            char end = Character.toLowerCase(w.charAt(w.length() - 1));
            counter.merge(start, 1, Integer::sum);
            counter.merge(end, -1, Integer::sum);
        }

        boolean isCycle = true;
        for (Map.Entry<Character, Integer> pair : counter.entrySet()) {
            if (pair.getValue() != 0) {
                isCycle = false;
                break;
            }
        }

        HashSet<String> set = new HashSet<>();
        while (set.size() < words.length) {
            for (String w : words) {
                if (set.contains(w)) continue;
                char start = Character.toLowerCase(w.charAt(0));
                char end = w.charAt(w.length() - 1);

                if (sb.length() == 0) {
                    if (isCycle || counter.get(start) == 1 || counter.get(end) == -1) {
                        sb.append(w);
                    } else {
                        continue;
                    }
                } else if (Character.toLowerCase(sb.charAt(0)) == end) {
                    sb.insert(0, " ");
                    sb.insert(0, w);
                } else if (start == sb.charAt(sb.length() - 1)) {
                    sb.append(" ");
                    sb.append(w);
                } else {
                    continue;
                }
                set.add(w);
            }
        }
        return sb;
    }
}
