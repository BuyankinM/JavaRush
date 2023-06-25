package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {

    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fr = new BufferedReader(new FileReader(br.readLine()))) {
            String line;
            while ((line = fr.readLine()) != null) {
                for (String w : line.split(" ")) {
                    words.add(w);
                }
            }
        }

        HashMap<String, ArrayList<StringBuilder>> map = new HashMap<>();
        for (String w : words) {
            char[] arr = w.toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);
            StringBuilder curSb = new StringBuilder(w);

            if (!map.containsKey(sorted)) {
                ArrayList<StringBuilder> list = new ArrayList<>();
                list.add(curSb);
                map.put(sorted, list);
            } else {
                ArrayList<StringBuilder> list = map.get(sorted);
                for (StringBuilder sb : list) {
                    if (curSb.toString().contentEquals(sb.reverse())) {
                        result.add(new Pair(curSb.toString(), curSb.reverse().toString()));
                        map.remove(sorted);
                        break;
                    }
                }
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}
