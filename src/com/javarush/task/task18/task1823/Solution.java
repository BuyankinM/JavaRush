package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name;

        while (true) {
            name = bufferedReader.readLine();
            if ("exit".equals(name)) break;
            else new ReadThread(name).start();
        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            try (FileInputStream fis = new FileInputStream(fileName)) {
                while (fis.available() > 0) treeMap.merge(fis.read(), 1, Integer::sum);
            } catch (IOException e) {
            }

            int maxCount = Collections.max(treeMap.values());
            for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                if (entry.getValue() == maxCount) {
                    resultMap.put(fileName, entry.getKey());
                    break;
                }
            }
        }
    }
}
